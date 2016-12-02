package ca.mcgill.ecse321.FTMS.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.FTMS.model.Employee;
import ca.mcgill.ecse321.FTMS.model.Equipment;
import ca.mcgill.ecse321.FTMS.model.Menu;
import ca.mcgill.ecse321.FTMS.model.OrderManager;
import ca.mcgill.ecse321.FTMS.model.Schedule;
import ca.mcgill.ecse321.FTMS.model.ScheduleRegistration;
import ca.mcgill.ecse321.FTMS.model.StaffManager;
import ca.mcgill.ecse321.FTMS.model.Supply;
import ca.mcgill.ecse321.FTMS.persistence.PersistenceXStreamOrder;
import ca.mcgill.ecse321.FTMS.persistence.PersistenceXStreamSchedule;

/**
 * @author Rony
 *
 */
/**
 * @author Rony
 *
 */
/**
 * @author Rony
 *
 */
/**
 * @author Rony
 *
 */
/**
 * @author Rony
 *
 */
/**
 * @author Rony
 *
 */
public class FTMSController {

	public FTMSController()
	{
	}
	
	public void createEmployee(String staffName, String staffRoles)throws InvalidInputException
	{		String error = "";
		if (staffName.equals(""))
			error= error +"Employee name cannot be empty!";
		if (staffRoles.equals(""))
			error= error + "Employee roles cannot be empty!";
		if (error.length()> 0)
			throw new InvalidInputException(error);
		
		Employee e = new Employee(staffName,staffRoles);
		
		StaffManager sm = StaffManager.getInstance();
		sm.addEmployee(e);
		
		PersistenceXStreamSchedule.saveToXMLwithXStream(sm);
	}
	
	public void createSchedule(String weekday, Date date, Time startTime, Time endTime) throws InvalidInputException
	{
		String error = "";
		
		if (weekday == null || weekday.trim().length() == 0 )
			error = error + "Weekday cannot be empty!";
		if (date == null)
			error = error + "Schedule date cannot be empty!";
		if (startTime == null)
			error = error + "Schedule start time cannot be empty!";
		if (endTime == null)
			error = error + "Schedule end time cannot be empty!";
		if(endTime != null && startTime != null && endTime.getTime()<startTime.getTime())
			error = error + "Schedule end time cannot be before Schedule start time!";
		error = error.trim();
		if (error.length()> 0)
			throw new InvalidInputException(error);
		
		Schedule s = new Schedule(weekday, date, startTime, endTime);
		StaffManager sm = StaffManager.getInstance();
		sm.addSchedule(s);
		PersistenceXStreamSchedule.saveToXMLwithXStream(sm);
		
	}
	public void scheduleRegister(Employee employee, Schedule schedule) throws InvalidInputException
	{
		StaffManager sm = StaffManager.getInstance();
		
		String error = "";
		if (employee == null)
			error = error + "Employee needs to be selected for registration! ";
		else if (!sm.getEmployees().contains(employee))
			error = error + "Employee does not exist! ";
		if (schedule == null)
			error = error + "Schedule needs to be selected for registration!";
		else if (!sm.getSchedules().contains(schedule))
			error = error + "Schedule does not exist!";
		error = error.trim();
		if (error.length()> 0)
			throw new InvalidInputException(error);
		
		ScheduleRegistration sr = new ScheduleRegistration(employee, schedule);
		sm.addScheduleRegistration(sr);
		PersistenceXStreamSchedule.saveToXMLwithXStream(sm);
		
	}

	public void createEquipment(String equipmentName,int equipmentQty)throws InvalidInputException
	{	
		OrderManager om = OrderManager.getInstance();

		String error="";

		if (equipmentQty<0)
			error=error+"Equipment quantity cannot be negative!";
		if (equipmentName.equals(""))
			error += "Enter a name for the equipment! ";
		if (error.length()> 0)
			throw new InvalidInputException(error);
		
		
		Equipment e = new Equipment(equipmentName, equipmentQty);
		
		om.addEquipment(e);
		
		PersistenceXStreamOrder.saveToXMLwithXStream(om);
	}
	
	public void createSupply(String foodName,int foodQty)throws InvalidInputException
	{	
		OrderManager om = OrderManager.getInstance();
		String error="";
		
		if (foodQty<0)
			error = error +"Food quantity cannot be negative!";
		if (foodName.equals(""))
			error += "Enter a name for the supply! ";
		if (error.length()> 0)
			throw new InvalidInputException(error);
		
		Supply s = new Supply(foodName, foodQty);
		
		om.addFoodSupply(s);
		
		PersistenceXStreamOrder.saveToXMLwithXStream(om);
	}
	
	public void createMenu(String menuName, List<Supply> ingredients, double price) throws InvalidInputException
	{
		
		OrderManager om = OrderManager.getInstance();
		String error = "";
		if (menuName.equals(""))
			error += "Enter a name for the dish!. ";
		else if (om.getMenu(menuName) != null)
			error += "This dish name already exists! ";
		else if (ingredients.size() == 0)
			error = error + "Ingredient needs to be selected! ";
		else if (price < 0)
			error += "price can't be negative!";
		if (error.length()> 0)
			throw new InvalidInputException(error);
		
		Menu mr = new Menu(menuName, ingredients, price, 0);
		om.addMenus(mr);
		PersistenceXStreamOrder.saveToXMLwithXStream(om);
				
	}
	
	/**
	 * Takes the menu name and increments its popularity. It also decreases all the supplies of this
	 * menu item by 1 and if one of the supplies was already missing then it throws an exception and does not place the order.
	 * @param menuName
	 * @throws Exception
	 */
	public void placeOrder(String menuName) throws Exception {
		OrderManager om = OrderManager.getInstance();
		Menu menu = om.getMenu(menuName);
		String error = "";
		if (menu.getMealsLeft() == 0)
			error += "You are missing one of the supplies shown";
		if (error.length() > 0)
			throw new Exception(error);
		
		menu.setPopularity(menu.getPopularity()+1);
		for(int i=0; i<menu.getIngredients().size(); i++)
			menu.getIngredients().get(i).setFoodQty(menu.getIngredients().get(i).getFoodQty()-1);
		PersistenceXStreamOrder.saveToXMLwithXStream(om);
	}
	
	/**
	 * Modifies the staff's start and end time for a specific day of the week. If the start time is
	 * after the end time then it throws an exception.
	 * @param staffPosition
	 * @param dotwPosition
	 * @param start
	 * @param end
	 * @throws Exception
	 */
	public void updateSchedule(int staffPosition, int dotwPosition, Time start, Time end) throws Exception {
		StaffManager sm = StaffManager.getInstance();
		if (staffPosition != -1 && sm.getEmployees().size() > 0) {
            if (!start.after(end))
                sm.getEmployee(staffPosition).setDayTimings(dotwPosition, start, end);
            else
                throw new Exception("Start time has to be before End time.");
        }
		PersistenceXStreamOrder.saveToXMLwithXStream(sm);
	}
	
	public void orderCustomizedMeal(Menu menuSelected, Supply[] removeSupplies, Supply[] extraSupplies) throws Exception {
		OrderManager om = OrderManager.getInstance();
				
		// Checking that we have enough supplies.
		for (int i=0; i<extraSupplies.length; i++) {
			if (extraSupplies[i].getFoodQty() < 2)
				throw new Exception("There are not enough supplies.");
		}
		for (int i=0; i<menuSelected.getIngredients().size(); i++) {
			if (menuSelected.getIngredients().get(i).getFoodQty() < 1)
				throw new Exception("There are not enough supplies.");
		}
		
		// Ordering: decreasing supply quantities and increasing popularity
		for (int i=0; i<menuSelected.getIngredients().size(); i++) {
			Supply currentSupply = menuSelected.getIngredients().get(i);
			currentSupply.setFoodQty(currentSupply.getFoodQty()-1);
		}
		for (int i=0; i<removeSupplies.length; i++)
			removeSupplies[i].setFoodQty(removeSupplies[i].getFoodQty()+1);
		for (int i=0; i<extraSupplies.length; i++)
			extraSupplies[i].setFoodQty(extraSupplies[i].getFoodQty()-1);
		menuSelected.setPopularity(menuSelected.getPopularity()+1);
	}
}

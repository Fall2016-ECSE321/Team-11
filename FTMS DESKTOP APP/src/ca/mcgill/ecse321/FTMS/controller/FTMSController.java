package ca.mcgill.ecse321.FTMS.controller;
/**
 * This FTMS controller class is use to control the actions of the project
 * @author Group 11
 */
import java.sql.Date;

import java.sql.Time;
import ca.mcgill.ecse321.FTMS.model.Employee;
import ca.mcgill.ecse321.FTMS.model.Equipment;
import ca.mcgill.ecse321.FTMS.model.Menu;
import ca.mcgill.ecse321.FTMS.model.OrderManager;
import ca.mcgill.ecse321.FTMS.model.OrderTracker;
import ca.mcgill.ecse321.FTMS.model.Schedule;
import ca.mcgill.ecse321.FTMS.model.ScheduleRegistration;
import ca.mcgill.ecse321.FTMS.model.StaffManager;
import ca.mcgill.ecse321.FTMS.model.Supply;
import ca.mcgill.ecse321.FTMS.persistence.PersistenceXStreamOrder;
import ca.mcgill.ecse321.FTMS.persistence.PersistenceXStreamSchedule;

public class FTMSController {

	public FTMSController()
	{
	}
	/**
	 * This method creates an Employee to the system
	 * @author Group 11
	 * @param staffName : Employee Name
	 * @param staffRoles: Employee Staff Title
	 * @param hours: Hours work per week
	 * @throws InvalidInputException : if parameter are null or hours is higher than 40
	 * 
	 */
	public void createEmployee(String staffName, String staffRoles, int hours)throws InvalidInputException
	{	
		// Throw exception if inputs are null or hours > 40 hours 
		String error = "";
		if (staffName==null|| staffName.trim().length() == 0)
			error= error +"Employee name cannot be empty!";
		if (staffRoles==null|| staffRoles.trim().length() == 0)
			error= error + "Employee roles cannot be empty!";
		if (hours==0)
			error= error +"hours to work cannot be 0";
		if (hours>40)
			error = error + "Hours must be lower than the 40 hours";
		if (error.length()> 0)
			throw new InvalidInputException(error);
		
		// Declare a new Employee
		Employee e = new Employee(staffName,staffRoles,hours);
		StaffManager sm = StaffManager.getInstance();
		sm.addEmployee(e);
		// Write the new employee to the ScheduleFTMS XML file 
		PersistenceXStreamSchedule.saveToXMLwithXStream(sm);
	}
	
	/**
	 * This method creates a Weekly Schedule in the system
	 * @param weekday : Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
	 * @param date : Date of the schedule 
	 * @param startTime : Beginning of work time shift
	 * @param endTime : End of work time shift
	 * @throws InvalidInputException :If parameters are null 
	 * @author Group 11
	 */
	public void createSchedule(String weekday, Date date, Time startTime, Time endTime) throws InvalidInputException
	{
		// Throw exception if inputs are null or startTime > endTime hours
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
		
		//Declare a new Schedule
		Schedule s = new Schedule(weekday, date, startTime, endTime);
		StaffManager sm = StaffManager.getInstance();
		sm.addSchedule(s);
		//Write the schedule to the ScheduleFTMS XML file 
		PersistenceXStreamSchedule.saveToXMLwithXStream(sm);
	}
	
	/**
	 * This method registers an employee to a schedule time shift
	 * @param employee : Employee Name
	 * @param schedule : Weekday with time and date
	 * @throws InvalidInputException : if parameter are null or is not contain in the XML file 
	 * @author Group 11
	 */
	public void scheduleRegister(Employee employee, Schedule schedule) throws InvalidInputException
	{
		StaffManager sm = StaffManager.getInstance();
		
		// throw exception if inputs are null or not found in the XML file 
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
		
		//Declare new schedule registration
		ScheduleRegistration sr = new ScheduleRegistration(employee, schedule);
		sm.addScheduleRegistration(sr);

		//Write the new schedule registration to the ScheduleFTMS XML file 
		PersistenceXStreamSchedule.saveToXMLwithXStream(sm);
		
	}
	/**
	 * This method creates a new equipment in the system
	 * @param equipmentName : Name of the equipment
	 * @param equipmentQty : Quantity of the equipment in terms of unity
	 * @throws InvalidInputException : parameter is null
	 * @throws InvalidInputException : quantity is 0 
	 * @author Group 11
	 */

	public void createEquipment(String equipmentName,int equipmentQty)throws InvalidInputException
	{	
		OrderManager om = OrderManager.getInstance();
		
		// throws exception if input is null or equipmentQty is zero 
		String error="";
		if(equipmentName==null)
			error=error+ "Equipment Name must be selected or created ";
		if (equipmentQty==0)
			error=error+"Equipment quantity cannot be zero!";
		error = error.trim();
		if (error.length()> 0)
			throw new InvalidInputException(error);
		
		// Declare new equipment
		Equipment e = new Equipment(equipmentName, equipmentQty);
		om.addEquipment(e);
		
		//Write the new equipment in the OrderFTMS XML file 
		PersistenceXStreamOrder.saveToXMLwithXStream(om);
		
	}
	
	/**
	 * This method creates a supply to the system
	 * @param foodName : Name of Food
	 * @param foodQty : Quantity of Food 
	 * @throws InvalidInputException : parameter is null
	 * @throws InvalidInputException : quantity is 0 
	 * @author Group 11
	 */
	public void createSupply(String foodName,int foodQty)throws InvalidInputException
	{	
		OrderManager om = OrderManager.getInstance();
		
		//throw exception if input is null or quantity is zero
		String error="";
		if(foodName==null)
			error=error+ "Food Name must be selected or created ";
		if (foodQty==0)
			error = error +"Food quantity cannot be zero!";
		error = error.trim();
		if (error.length()> 0)
			throw new InvalidInputException(error);
		
		// Declare new Supply	
		Supply s = new Supply(foodName, foodQty);
		om.addFoodSupply(s);
		//Write new Supply in the OrderFTMS XML file
		PersistenceXStreamOrder.saveToXMLwithXStream(om);
	}
	
	/**
	 * This method creates a new menu, where the ingredient, food quantity and price are link to it.
	 * @param menuName : Menu Name
	 * @param foodName : Ingredient included in the Menu
	 * @param ingredientQty : Quantity per serving
	 * @param price : Price of the Meal
	 * @throws InvalidInputException : parameter is null
	 * @throws InvalidInputException : quantity of ingredient is zero
	 * @throws InvalidInputException : Price is negative
	 * @author Group 11 
	 */
	public void createMenu(String menuName, String ingredientName,int ingredientQty, double price) throws InvalidInputException
	{
		
			OrderManager om = OrderManager.getInstance();
			//throw exception if input is null, ingredient =0 or price is negative
			String error = "";
			if (menuName == null)
				error = error + "Menu needs to be selected for Menu registration! ";
			if (ingredientName == null)
				error = error + "Ingredient needs to be selected for Menu registration! ";
			if(ingredientQty==0)
				error=error+"Ingredient quantity must be higher than zero ";
			if (price <= 0 )
				error=error+ "Price must be a positive quantity";
					
			error = error.trim();
				if (error.length()> 0)
				throw new InvalidInputException(error);
				
			//Declare a new menu
			Menu mr = new Menu(menuName, ingredientName,ingredientQty, price);
			om.addMenus(mr);
			//Write new Menu in the OrderFTMS XML file
			PersistenceXStreamOrder.saveToXMLwithXStream(om);
				
	}
	
	/**
	 * This method creates a popularity Tracker of a Menu with the quantity sold
	 * @param saleName : Menu being Tracked
	 * @param saleQty : Sold quantity
	 * @throws InvalidInputException : if parameter is null
	 * @throws InvalidInputException : if sale quantity is zero
	 * @author Group 11
	 */
public void createTracker(String saleName, int saleQty) throws InvalidInputException
	{
		
			OrderManager om = OrderManager.getInstance();
			
			// throw exception if input is null or sale quantity is zero.
			String error = "";
			if(saleQty==0)
				error=error+"Sale quantity must be higher than 0";
			error = error.trim();

			if (error.length()> 0)
				throw new InvalidInputException(error);
			//Declare a new menu
			OrderTracker sr = new OrderTracker(saleName,  saleQty);
			om.addTracker(sr);
		
			//Write new Tracker in the OrderFTMS XML file
			PersistenceXStreamOrder.saveToXMLwithXStream(om);
				
	}
	
	
}

package ca.mcgill.ecse321.FTMS.persistence;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.FTMS.model.Employee;
import ca.mcgill.ecse321.FTMS.model.Equipment;
import ca.mcgill.ecse321.FTMS.model.Menu;
import ca.mcgill.ecse321.FTMS.model.OrderManager;
import ca.mcgill.ecse321.FTMS.model.OrderTracker;
import ca.mcgill.ecse321.FTMS.model.Schedule;
import ca.mcgill.ecse321.FTMS.model.ScheduleRegistration;
import ca.mcgill.ecse321.FTMS.model.StaffManager;
import ca.mcgill.ecse321.FTMS.model.Supply;
import ca.mcgill.ecse321.FTMS.persistence.PersistenceXStreamSchedule;

public class TestPersistence {

	@Before
	public void setUp() throws Exception {
		StaffManager fm = StaffManager.getInstance();
		
		//create Staff
		Employee em = new Employee("Ben","Chef",56);
		Employee em2 = new Employee("Roman","Cashier", 56);	
		
		//create schedule
		
		Calendar c = Calendar.getInstance();
		c.set(2015,Calendar.SEPTEMBER,15,8,30,0);
		Date date = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2015,Calendar.SEPTEMBER,15,10,0,0);
		Time endTime = new Time(c.getTimeInMillis());
		Schedule sc = new Schedule("monday", date, startTime, endTime);
		
		//register participants to event
		ScheduleRegistration sr = new ScheduleRegistration(em, sc);
		ScheduleRegistration sr2 = new ScheduleRegistration(em2, sc);
		
		//manage registrations
		fm.addScheduleRegistration(sr);
		fm.addScheduleRegistration(sr2);
		fm.addSchedule(sc);
		fm.addEmployee(em);
		fm.addEmployee(em2);

		// order section
		OrderManager om = OrderManager.getInstance();
		
		//create Supply
		Supply su = new Supply("orange",3);
		
		//create Equipment
		Equipment eq = new Equipment("straw",3);
				
		//create Menu
		String ingredient = su.getFoodName();
		Menu me = new Menu("hamburger",ingredient,4, 4.00);
		
		//create tracker
		String menu= me.getMealName();
		OrderTracker sale = new OrderTracker(menu, 4);
		
		om.addFoodSupply(su);
		om.addEquipment(eq);
		om.addMenus(me);
		om.addTracker(sale);


	}

	@After
	public void tearDown() throws Exception {
		//clear all Schedule and Order registrations
				StaffManager sm = StaffManager.getInstance();
				sm.delete();
				OrderManager om = OrderManager.getInstance();
				om.delete();
	
	}

	@Test
	public void scheduletest() {
		//save model for schedule
				StaffManager sm = StaffManager.getInstance();
				PersistenceXStreamSchedule.setFilename("test"+File.separator + "ca"+File.separator+
						"mcgill"+File.separator+"ecse321"+File.separator+"FTMS"+
						File.separator+"persistence"+File.separator+"ScheduleFTMS.xml");
				PersistenceXStreamSchedule.setAlias("schedule", Schedule.class);
				PersistenceXStreamSchedule.setAlias("employee", Employee.class);
				PersistenceXStreamSchedule.setAlias("scheduleRegistration", ScheduleRegistration.class);
				PersistenceXStreamSchedule.setAlias("manager", StaffManager.class);
				if (!PersistenceXStreamSchedule.saveToXMLwithXStream(sm))
				fail("Could not save file.");
				
				//clear the model in memory
				sm.delete();
				assertEquals(0, sm.getEmployees().size());
				assertEquals(0, sm.getSchedules().size());
				assertEquals(0, sm.getScheduleRegistrations().size());
				
				//load model
				sm = (StaffManager) PersistenceXStreamSchedule.loadFromXMLwithXStream();
				if (sm == null)
					fail("could not load file.");
				
				//check employees
				assertEquals(2, sm.getEmployees().size());
				assertEquals("Ben", sm.getEmployee(0).getStaffName().toString());
				assertEquals("Chef", sm.getEmployee(0).getStaffRoles().toString());
				assertEquals(56,sm.getEmployee(0).getHours());
				assertEquals("Roman", sm.getEmployee(1).getStaffName().toString());
				assertEquals("Cashier", sm.getEmployee(1).getStaffRoles().toString());
				assertEquals(56,sm.getEmployee(1).getHours());
				
				//check schedule
				assertEquals(1, sm.getSchedules().size());
				assertEquals("monday", sm.getSchedule(0).getWeekday());
				assertEquals(1, sm.getSchedules().size());
				Calendar c = Calendar.getInstance();
				c.set(2015,Calendar.SEPTEMBER,15,8,30,0);
				Date date = new Date(c.getTimeInMillis());
				Time startTime = new Time(c.getTimeInMillis());
				c.set(2015,Calendar.SEPTEMBER,15,10,0,0);
				Time endTime = new Time(c.getTimeInMillis());
				assertEquals(date.toString(), sm.getSchedule(0).getDate().toString());
				assertEquals(startTime.toString(), sm.getSchedule(0).getStartTime().toString());
				assertEquals(endTime.toString(), sm.getSchedule(0).getEndTime().toString());
				
				//check schedule registrations
				assertEquals(2, sm.getScheduleRegistrations().size());
				assertEquals(sm.getSchedule(0), sm.getScheduleRegistration(0).getSchedule());
				assertEquals(sm.getEmployee(0), sm.getScheduleRegistration(0).getEmployee());
				assertEquals(sm.getSchedule(0), sm.getScheduleRegistration(1).getSchedule());
				assertEquals(sm.getEmployee(1), sm.getScheduleRegistration(1).getEmployee());
				
	}
				@Test
				public void ordertest() {
				//save model for order
				OrderManager om = OrderManager.getInstance();
				PersistenceXStreamOrder.setFilename("test"+File.separator + "ca"+File.separator+
						"mcgill"+File.separator+"ecse321"+File.separator+"FTMS"+
						File.separator+"persistence"+File.separator+"OrderFTMS.xml");
				PersistenceXStreamSchedule.setAlias("supply", Supply.class);
				PersistenceXStreamSchedule.setAlias("equipment", Equipment.class);
				PersistenceXStreamSchedule.setAlias("menu", Menu.class);
				PersistenceXStreamSchedule.setAlias("tracker", Menu.class);
				PersistenceXStreamSchedule.setAlias("manager", OrderManager.class);
				
				if (!PersistenceXStreamOrder.saveToXMLwithXStream(om))
				fail("Could not save file.");
				
				//clear the model in memory
				om.delete();
				assertEquals(0, om.getFoodSupplies().size());
				assertEquals(0, om.getEquipments().size());
				assertEquals(0, om.getMenus().size());
				assertEquals(0, om.getTracker().size());
				//load model
				om = (OrderManager) PersistenceXStreamOrder.loadFromXMLwithXStream();
				if (om == null)
					fail("could not load file.");
				
				//check supplies
				assertEquals(1, om.getFoodSupplies().size());
				assertEquals("orange", om.getFoodSupply(0).getFoodName().toString());
				assertEquals(3,om.getFoodSupply(0).getFoodQty());
				
				//check equipments
				assertEquals(1, om.getEquipments().size());
				assertEquals("straw", om.getEquipment(0).getEquipmentName());
				assertEquals(3,om.getEquipment(0).getEquipmentQty());
				
				//check menus
				assertEquals(1, om.getMenus().size());
				assertEquals("hamburger", om.getMenus(0).getMealName());
				assertEquals("orange", om.getMenus(0).getIngredientName());
				assertEquals(4,om.getMenus(0).getIngredientQty());
				assertEquals(4,00,om.getMenus(0).getPrice());
				
				//check order tracker
				assertEquals(1, om.getTracker().size());
				assertEquals("hamburger", om.getTracker(0).getSaleName());
				assertEquals(4, om.getTracker(0).getSaleQty());
				
				
				
	}

}

package ca.mcgill.ecse321.FTMS.controller;

import static org.junit.Assert.*;



import java.io.File;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
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
import ca.mcgill.ecse321.FTMS.persistence.PersistenceXStreamOrder;
import ca.mcgill.ecse321.FTMS.persistence.PersistenceXStreamSchedule;
/**
 * Class to test the controller
 * @author Group 11
 *
 */
public class TestFTMSController {
	
	/**
	 * Method to load XML and models of the project
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// output to xml
				PersistenceXStreamSchedule.setFilename("test"+File.separator + "ca"+File.separator+
						"mcgill"+File.separator+"ecse321"+File.separator+"FTMS"+
						File.separator+"controller"+File.separator+"ScheduleFTMS.xml");
					
				PersistenceXStreamOrder.setFilename("test"+File.separator + "ca"+File.separator+
						"mcgill"+File.separator+"ecse321"+File.separator+"FTMS"+
						File.separator+"controller"+File.separator+"OrderFTMS.xml");
			
		
			// schedule portion
			PersistenceXStreamSchedule.setAlias("schedule", Schedule.class);
			PersistenceXStreamSchedule.setAlias("employee", Employee.class);
			PersistenceXStreamSchedule.setAlias("registration", ScheduleRegistration.class);
			PersistenceXStreamSchedule.setAlias("manager", StaffManager.class);
			
			//order portion
			PersistenceXStreamOrder.setAlias("supply", Supply.class);
			PersistenceXStreamOrder.setAlias("equipment", Equipment.class);
			PersistenceXStreamOrder.setAlias("menu", Menu.class);
			PersistenceXStreamOrder.setAlias("orderTracker", OrderTracker.class);
			PersistenceXStreamOrder.setAlias("manager", OrderManager.class);
					
	}
	/**
	 * Method to clear registration
	 *
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//clear all registrations
		StaffManager sm = StaffManager.getInstance();
		OrderManager om = OrderManager.getInstance();
		om.delete();
		sm.delete();
	}

	/**
	 * Method to clear registration
	 */
	@After
	public void tearDown() throws Exception {
		//clear all registrations
		StaffManager sm = StaffManager.getInstance();
		OrderManager om = OrderManager.getInstance();
		om.delete();
		sm.delete();
	}

	/**
	 * Method to test for creation of an Employee
	 */
	@Test
	public void testCreateEmployee() {
			
		StaffManager sm = StaffManager.getInstance();
		assertEquals(0, sm.getEmployees().size());
			
		String name = "Ben";
		String role = "Chef";
		int hours = 40;
		
		FTMSController erc = new FTMSController();
			try {
				erc.createEmployee(name, role, hours);
			} catch (InvalidInputException e) {
			// check that no error occurred
				fail();
			}
		checkResultEmployee(name,role,hours, sm);
		StaffManager sm2 = (StaffManager) PersistenceXStreamSchedule.loadFromXMLwithXStream();
			
		//check file content
		checkResultEmployee(name,role,hours, sm2);
	}
	
	/**
	 * Method to test createEmployee for Null Case
	 */

	@Test
	public void testCreateEmployeeNull(){
		StaffManager sm = StaffManager.getInstance();
		assertEquals(0, sm.getEmployees().size());
		
		String name = null;
		String role = null;
		int hours = 0;
		String error = null;
	
		FTMSController erc = new FTMSController();
		try{
			erc.createEmployee(name,role,hours);
		} catch(InvalidInputException e){
			error = e.getMessage();
		}
		
		//check error
		assertEquals("Employee name cannot be empty!Employee roles cannot be empty!hours to work cannot be 0", error);

		//check no change in memory
		assertEquals(0, sm.getEmployees().size());
	}
	

	/**
	 * Method to test createEmployee for empty Case
	 */

 	@Test
	public void testCreateEmployeeEmpty(){
		
		StaffManager sm = StaffManager.getInstance();
		assertEquals(0, sm.getEmployees().size());
		
		String staffName = null;
		String staffRoles =null;
		int hours = 0;
		
		String error = null;
		FTMSController erc = new FTMSController();
		try{
			erc.createEmployee(staffName, staffRoles, hours);
		} catch(InvalidInputException e){
			error = e.getMessage();
		}
		
		//check error
		assertEquals("Employee name cannot be empty!Employee roles cannot be empty!hours to work cannot be 0", error);

		//check no change in memory
		assertEquals(0, sm.getEmployees().size());
	}
	

	/**
	 * Method to test createEmployee for Empty space Case
	 */

	@Test
	public void testCreateEmployeeSpaces(){
		
		StaffManager sm = StaffManager.getInstance();
		assertEquals(0, sm.getEmployees().size());
		
		String staffName = " ";
		String staffRoles = " ";
		int hours = 0;
		String error = null;

		FTMSController erc = new FTMSController();
		try{
			erc.createEmployee(staffName, staffRoles, hours);
		} catch(InvalidInputException e){
			error = e.getMessage();
		}
		
		//check error
		assertEquals("Employee name cannot be empty!Employee roles cannot be empty!hours to work cannot be 0", error);

		//check no change in memory
		assertEquals(0, sm.getEmployees().size());
	}
	

	/**
	 * Method to test createEmployee for maximum hour to be worked
	 */
	@Test
	public void testCreateEmployeeMaxHour() {
		StaffManager sm = StaffManager.getInstance();
		assertEquals(0, sm.getEmployees().size());

		String error=null;
		String name = "Ben";
		String role = "Chef";
		int hours = 50;
		
		FTMSController erc = new FTMSController();
		try {
			erc.createEmployee(name, role, hours);
		} catch (InvalidInputException e) {
			// check that no error occurred
			error = e.getMessage();
		}

		// check error
		assertEquals("Hours must be lower than the 40 hours", error);

		//check no change in memory
		assertEquals(0, sm.getEmployees().size());
	}

	/**
	 * Method to test for creation of an Schedule
	 */
	
	@Test
	public void testCreateSchedule(){
		StaffManager sm = StaffManager.getInstance();
		assertEquals(0, sm.getSchedules().size());
		
		String nameS = "Monday";
		Calendar c = Calendar.getInstance();
		c.set(2016,Calendar.OCTOBER,16,9,00,0);
		Date date = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2016,Calendar.OCTOBER,16,10,30,0);
		Time endTime = new Time(c.getTimeInMillis());
		
		FTMSController erc = new FTMSController();
		try{
			erc.createSchedule(nameS, date, startTime, endTime);
		}catch (InvalidInputException e){
			//check that no error occurred
			fail();
		}
		//check model in memory
		checkResultSchedule(nameS, date, startTime, endTime, sm);
		StaffManager sm2 = (StaffManager) PersistenceXStreamSchedule.loadFromXMLwithXStream();
		
		// check file contents
		checkResultSchedule(nameS, date, startTime, endTime, sm2);
		
	}

	/**
	 * Method to test createSchedule for Null Case
	 */

	@Test
	public void testCreateScheduleNull(){
		StaffManager sm = StaffManager.getInstance();
		assertEquals(0, sm.getSchedules().size());
		String weekday =null;
		Date date = null;
		Time startTime = null;
		Time endTime = null;
		
		String error = null;
		FTMSController erc = new FTMSController();
		try{
			erc.createSchedule(weekday, date, startTime, endTime);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		// check error
		assertEquals("Weekday cannot be empty!Schedule date cannot be empty!Schedule start time cannot be empty!Schedule end time cannot be empty!", error); 
		//check model in memory
		assertEquals(0, sm.getSchedules().size());
		
	}


	/**
	 * Method to test createSchedule for Empty Case
	 */

	@Test
	// test for empty
	public void testCreateScheduleEmpty(){
	StaffManager sm = StaffManager.getInstance();
	assertEquals(0, sm.getSchedules().size());
	
	String weekday = "";
	Calendar c = Calendar.getInstance();
	c.set(2016,Calendar.OCTOBER,16,9,00,0);
	Date eventDate = new Date(c.getTimeInMillis());
	Time startTime = new Time(c.getTimeInMillis());
	c.set(2016,Calendar.OCTOBER,16,10,30,0);
	Time endTime = new Time(c.getTimeInMillis());
	
	String error = null;
	FTMSController erc = new FTMSController();
	try{
		erc.createSchedule(weekday, eventDate, startTime, endTime);
	}catch (InvalidInputException e){
		error = e.getMessage();
	}
	//check error
	assertEquals("Weekday cannot be empty!", error);
	
	//check model in memory
	assertEquals(0, sm.getSchedules().size());	
	}
	

	/**
	 * Method to test createSchedule for empty space Case
	 */

	@Test
	public void testCreateScheduleSpaces(){
		StaffManager sm = StaffManager.getInstance();
		assertEquals(0, sm.getSchedules().size());
		
		String weekday = "";
		Calendar c = Calendar.getInstance();
		c.set(2016,Calendar.OCTOBER,16,9,00,0);
		Date date = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2016,Calendar.OCTOBER,16,10,30,0);
		Time endTime = new Time(c.getTimeInMillis());
		
		String error = null;
		FTMSController erc = new FTMSController();
		try{
			erc.createSchedule(weekday, date, startTime, endTime);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		//check error
		assertEquals("Weekday cannot be empty!", error);
		
		//check model in memory
		assertEquals(0, sm.getSchedules().size());	
			
	}
	

	/**
	 * Method to test createSchedule for startTime larger than endTime
	 */

	@Test
	public void testCreateScheduleEndTimeBeforeStartTime(){
		StaffManager sm = StaffManager.getInstance();
		assertEquals(0, sm.getSchedules().size());
		
		String nameS = "Monday";
		Calendar c = Calendar.getInstance();
		c.set(2016,Calendar.OCTOBER,16,9,00,0);
		Date date = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2016,Calendar.OCTOBER,16,8,59,59);
		Time endTime = new Time(c.getTimeInMillis());
		String error = null;

		FTMSController erc = new FTMSController();
		try{
			erc.createSchedule(nameS, date, startTime, endTime);
		}catch (InvalidInputException e){
			error = e.getMessage();
		}
		//check error
		assertEquals("Schedule end time cannot be before Schedule start time!", error);
		
		//check model in memory
		assertEquals(0, sm.getSchedules().size());	
	}
	
	/**
	 * Method to test for creation of a Schedule Registration
	 */

	@Test
	public void testRegister(){
		StaffManager sm = StaffManager.getInstance();
		assertEquals(0, sm.getScheduleRegistrations().size());
		
		// staff portion
		String nameE = "Ben";
		String roleE = "Chef";
		int hours=56;
		Employee Employee = new Employee(nameE,roleE, hours );
		sm.addEmployee(Employee);
		assertEquals(1, sm.getEmployees().size());
		
		// schedule portion
		String nameS = "Monday";
		Calendar c = Calendar.getInstance();
		c.set(2016,Calendar.OCTOBER,16,9,00,0);
		Date date = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2016,Calendar.OCTOBER,16,10,30,0);
		Time endTime = new Time(c.getTimeInMillis());
		Schedule Schedule = new Schedule(nameS, date, startTime, endTime);
		sm.addSchedule(Schedule);
		assertEquals(1, sm.getSchedules().size());
		
		FTMSController erc = new FTMSController();
		try{
			erc.scheduleRegister(Employee, Schedule);
		}catch (InvalidInputException e){
			//check that no error occurred
			fail();
		}
		//check model in memory
		checkResultScheduleRegister(nameE,roleE, hours,nameS,  date, startTime, endTime, sm);
		
		StaffManager sm2 = (StaffManager) PersistenceXStreamSchedule.loadFromXMLwithXStream();
		
		//check file contents
		checkResultScheduleRegister(nameE, roleE,hours,nameS, date, startTime, endTime, sm2);
		
		}
	

	/**
	 * Method to test scheduleRegister for Null Case
	 */

	@Test
	public void testRegisterNull(){
		StaffManager sm = StaffManager.getInstance();
		assertEquals(0, sm.getScheduleRegistrations().size());
		
		Employee Employee = null;
		assertEquals(0, sm.getEmployees().size());
		
		Schedule Schedule = null;
		assertEquals(0, sm.getSchedules().size());
		
		String error = null;
	
		FTMSController erc = new FTMSController();
		try{
			erc.scheduleRegister(Employee, Schedule);
		}catch (InvalidInputException e){
			error= e.getMessage();
		}
		// check error
		assertEquals("Employee needs to be selected for registration! Schedule needs to be selected for registration!", error);
		
		//check model in memory
		assertEquals(0, sm.getScheduleRegistrations().size());
		assertEquals(0, sm.getEmployees().size());
		assertEquals(0, sm.getSchedules().size());
	}
	
	/**
	 * Method to test scheduleRegister for Non existent Case
	 */

	@Test
	public void testRegisterEmployeeAndScheduleDoNotExist(){
		StaffManager sm = StaffManager.getInstance();
		assertEquals(0, sm.getScheduleRegistrations().size());
		
		// employee portion
		String nameE = "Ben";
		String roleE = "Chef";
		int hours = 56;
		Employee Employee = new Employee(nameE, roleE, hours);
		assertEquals(0, sm.getEmployees().size());
		//schedule portion
		String nameS = "Monday";
		Calendar c = Calendar.getInstance();
		c.set(2016,Calendar.OCTOBER,16,9,00,0);
		Date date = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2016,Calendar.OCTOBER,16,10,30,0);
		Time endTime = new Time(c.getTimeInMillis());
		Schedule Schedule = new Schedule(nameS, date, startTime, endTime);
		assertEquals(0, sm.getSchedules().size());
		
		String error = null;
		
		FTMSController erc = new FTMSController();
		try{
			erc.scheduleRegister(Employee, Schedule);
		}catch (InvalidInputException e){
			error= e.getMessage();
		}	
		// check error
		assertEquals("Employee does not exist! Schedule does not exist!", error);
				
		//check model in memory
		assertEquals(0, sm.getScheduleRegistrations().size());
		assertEquals(0, sm.getEmployees().size());
		assertEquals(0, sm.getSchedules().size());
	}

	/**
	 * Method to test for creation of a Supply
	 */

	@Test
		public void testCreateSupply() {
		OrderManager om = OrderManager.getInstance();
		assertEquals(0, om.getFoodSupplies().size());
		
		String foodName = "orange";
		int foodQty = 3;
		FTMSController erc = new FTMSController();
		try {
			erc.createSupply(foodName, foodQty);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}
		
		checkResultSupply(foodName,foodQty, om);
		
		OrderManager om2 = (OrderManager) PersistenceXStreamOrder.loadFromXMLwithXStream();
		
		//check file content
		checkResultSupply(foodName, foodQty, om2);
		}

	/**
	 * Method to test createSupply for Null Case
	 */
		@Test 
		public void testCreateSupplyNull(){
		OrderManager om = OrderManager.getInstance();
		assertEquals(0, om.getFoodSupplies().size());
	
		String foodName = null;
		int foodQty = 0;
		String error = null;

		FTMSController erc = new FTMSController();
		try{
			erc.createSupply(foodName,foodQty);
		} catch(InvalidInputException e){
			error = e.getMessage();
		}
	
		//check error
		assertEquals("Food Name must be selected or created Food quantity cannot be zero!", error);

		//check no change in memory
		assertEquals(0, om.getFoodSupplies().size());
	

		}
		
		/**
		 * Method to test createSupply for Empty Case
		 */
		@Test
		public void testCreateSupplyEmpty(){
	
			OrderManager om = OrderManager.getInstance();
			assertEquals(0, om.getFoodSupplies().size());
	
			String foodName = null;
			int foodQty = 0;
	
			String error = null;
			FTMSController erc = new FTMSController();
			try{
				erc.createSupply(foodName, foodQty);
			} catch(InvalidInputException e){
				error = e.getMessage();
			}
	
			//check error
			assertEquals("Food Name must be selected or created Food quantity cannot be zero!", error);

			//check no change in memory	
			assertEquals(0, om.getFoodSupplies().size());

		}

		/**
		 * Method to test createSupply for Empty Space Case
		 */
		
		@Test
		public void testCreateSupplySpaces(){
	
			OrderManager om = OrderManager.getInstance();
			assertEquals(0, om.getFoodSupplies().size());
	
			String foodName = " ";
			int foodQty = 0;
			String error = null;
			
			FTMSController erc = new FTMSController();
		
			try{
				erc.createSupply(foodName, foodQty);
			} catch(InvalidInputException e){
				error = e.getMessage();
			}
	
			//check error
			assertEquals("Food quantity cannot be zero!", error);

			//check no change in memory
			assertEquals(0, om.getFoodSupplies().size());
		}

		/**
		 * Method to test for creation of an Equipment
		 */

		@Test
		public void testCreateEquipment() {
			OrderManager om = OrderManager.getInstance();
			assertEquals(0, om.getEquipments().size());
	
			String equipmentName = "straw";
			int equipmentQty = 3;
			FTMSController erc = new FTMSController();
			try {
				erc.createEquipment(equipmentName, equipmentQty);
			} catch (InvalidInputException e) {
		// check that no error occurred
				fail();
			}
	
			checkResultEquipment(equipmentName,equipmentQty, om);
	
			OrderManager om2 = (OrderManager) PersistenceXStreamOrder.loadFromXMLwithXStream();
	
			//check file content
			checkResultEquipment(equipmentName, equipmentQty, om2);
		}
		
		/**
		 * Method to test createEquipment for Null Case
		 */
		
		@Test
		public void testCreateEquipmentNull(){
			OrderManager om = OrderManager.getInstance();
			assertEquals(0, om.getEquipments().size());

			String equipmentName = null;
			int equipmentQty = 0;
			String error = null;
			
			FTMSController erc = new FTMSController();
			
			try{
				erc.createEquipment(equipmentName,equipmentQty);
			} catch(InvalidInputException e){
				error = e.getMessage();
			}

			//check error
			assertEquals("Equipment Name must be selected or created Equipment quantity cannot be zero!", error);

			//check no change in memory
			assertEquals(0, om.getEquipments().size());


		}

		/**
		 * Method to test createEquipment for Empty Case
		 */
		@Test
		public void testCreateEquipmentEmpty(){

			OrderManager om = OrderManager.getInstance();
			assertEquals(0, om.getEquipments().size());

			String equipmentName = null;
			int equipmentQty = 0;

			String error = null;

			FTMSController erc = new FTMSController();
			
			try{
				erc.createEquipment(equipmentName, equipmentQty);
			} catch(InvalidInputException e){
				error = e.getMessage();
			}

			//check error
			assertEquals("Equipment Name must be selected or created Equipment quantity cannot be zero!", error);

			//check no change in memory
			assertEquals(0, om.getEquipments().size());

		}

		/**
		 * Method to test createEquipment for Empty Space Case
		 */
		@Test
		public void testCreateEquipmentSpaces(){

			OrderManager om = OrderManager.getInstance();
			assertEquals(0, om.getEquipments().size());

			String equipmentName = " ";
			int equipmentQty = 0;
			String error = null;
			
			FTMSController erc = new FTMSController();
			
			try{
				erc.createEquipment(equipmentName, equipmentQty);
			} catch(InvalidInputException e){
				error = e.getMessage();
			}

			//check error
			assertEquals("Equipment quantity cannot be zero!", error);

			//check no change in memory
			assertEquals(0, om.getEquipments().size());
		}
		
		/**
		 * Method to test for creation of a Menu
		 */
		@Test
		public void testCreateMenu() {
				
			OrderManager om = OrderManager.getInstance();
			assertEquals(0, om.getMenus().size());
				
			String menuName = "Hamburger";
			String ingredient = "tomatoes";
			int ingredientQty = 40;
			double price = 10.;
							
			FTMSController erc = new FTMSController();
				try {
					erc.createMenu(menuName, ingredient, ingredientQty, price);
				} catch (InvalidInputException e) {
				// check that no error occurred
					fail();
				}
			checkResultMenu(menuName, ingredient, ingredientQty, price, om);
			OrderManager om2 = (OrderManager) PersistenceXStreamOrder.loadFromXMLwithXStream();
				
			//check file content
			checkResultMenu(menuName, ingredient, ingredientQty, price, om2);
		}
		
		/**
		 * Method to test createMenu for Null Case
		 */

		@Test
		public void testCreateMenuNull(){
			OrderManager om = OrderManager.getInstance();
			assertEquals(0, om.getMenus().size());
				
			String menuName = null;
			String ingredient = null;
			int ingredientQty = 0;
			double price = 0.;
				String error = null;
		
			FTMSController erc = new FTMSController();
			try{
				erc.createMenu(menuName, ingredient, ingredientQty, price);
			} catch(InvalidInputException e){
				error = e.getMessage();
			}
			
			//check error
			assertEquals("Menu needs to be selected for Menu registration! Ingredient needs to be selected for Menu registration! Ingredient quantity must be higher than zero Price must be a positive quantity", error);

			//check no change in memory
			assertEquals(0, om.getMenus().size());
		}
		

		/**
		 * Method to test createMenu for empty Case
		 */

	 	@Test
		public void testCreateMenuEmpty(){
			
	 		OrderManager om = OrderManager.getInstance();
			assertEquals(0, om.getMenus().size());
				
			String menuName = null;
			String ingredient = null;
			int ingredientQty = 0;
			double price = 0.;
				String error = null;

				FTMSController erc = new FTMSController();
			try{
				erc.createMenu(menuName, ingredient, ingredientQty, price);
			} catch(InvalidInputException e){
				error = e.getMessage();
			}
			
			//check error
			assertEquals("Menu needs to be selected for Menu registration! Ingredient needs to be selected for Menu registration! Ingredient quantity must be higher than zero Price must be a positive quantity", error);

			//check no change in memory
			assertEquals(0, om.getMenus().size());
		}
		

		/**
		 * Method to test createMenu for Empty space Case
		 */

		@Test
		public void testCreateMenuSpaces(){
			
			OrderManager om = OrderManager.getInstance();
			assertEquals(0, om.getMenus().size());
				
			String menuName = " ";
			String ingredient = null;
			int ingredientQty = 0;
			double price = 0.;
				String error = null;

			FTMSController erc = new FTMSController();
			try{
				erc.createMenu(menuName, ingredient, ingredientQty, price);
			} catch(InvalidInputException e){
				error = e.getMessage();
			}
			
			//check error
			assertEquals("Ingredient needs to be selected for Menu registration! Ingredient quantity must be higher than zero Price must be a positive quantity", error);

			//check no change in memory
			assertEquals(0, om.getMenus().size());
		}
		

		/**
		 * Method to test createMenu for Negative price
		 */

		@Test
		public void testCreateMenuNegativePrice(){
			
			OrderManager om = OrderManager.getInstance();
			assertEquals(0, om.getMenus().size());
				
			String menuName = "Hamburger";
			String ingredient = "tomatoes";
			int ingredientQty = 3;
			double price = -2.30;
				String error = null;

			FTMSController erc = new FTMSController();
			try{
				erc.createMenu(menuName, ingredient, ingredientQty, price);
			} catch(InvalidInputException e){
				error = e.getMessage();
			}
			
			//check error
			assertEquals("Price must be a positive quantity", error);

			//check no change in memory
			assertEquals(0, om.getMenus().size());
		}

		/**
		 * Method to test for creation of a Menu Tracker
		 */
		@Test
		public void testCreateTracker() {
				
			OrderManager om = OrderManager.getInstance();
			assertEquals(0, om.getMenus().size());
				
			String menuName="hamburger";
			int saleQty = 3;
		
			FTMSController erc = new FTMSController();
			try{
				erc.createTracker(menuName,saleQty);
			} catch(InvalidInputException e){
				fail();
			}
			
			checkResultTracker(menuName,saleQty, om);
			OrderManager om2 = (OrderManager) PersistenceXStreamOrder.loadFromXMLwithXStream();
				
			//check file content
			checkResultTracker(menuName, saleQty, om2);
		}
		
		/**
		 * Method to test createMenu for Null Case
		 */

		@Test
		public void testCreateZeroSale(){
			OrderManager om = OrderManager.getInstance();
			assertEquals(0, om.getTracker().size());
				
			String menuName = "hamburger";
			int saleQty = 0;
				String error = null;
		
			FTMSController erc = new FTMSController();
			try{
				erc.createTracker(menuName,saleQty);
			} catch(InvalidInputException e){
				error = e.getMessage();
			}
			
			//check error
			assertEquals("Sale quantity must be higher than 0", error);

			//check no change in memory
			assertEquals(0, om.getTracker().size());
		}

	private void checkResultEmployee(String staffName,String staffRoles, int hours,  StaffManager sm2) {
		assertEquals(1, sm2.getEmployees().size());
		assertEquals(staffName, sm2.getEmployee(0).getStaffName());
		assertEquals(staffRoles, sm2.getEmployee(0).getStaffRoles());
		assertEquals(hours, sm2.getEmployee(0).getHours());
		assertEquals(0, sm2.getSchedules().size());
		assertEquals(0, sm2.getScheduleRegistrations().size());
	}

	private void checkResultSchedule(String Weekday, Date date, Time startTime, Time endTime, StaffManager sm2){
		assertEquals(0, sm2.getEmployees().size());
		assertEquals(1, sm2.getSchedules().size());
		assertEquals(date.toString(), sm2.getSchedule(0).getDate().toString());
		assertEquals(startTime.toString(), sm2.getSchedule(0).getStartTime().toString());
		assertEquals(endTime.toString(), sm2.getSchedule(0).getEndTime().toString());
		assertEquals(0, sm2.getScheduleRegistrations().size());
	}
	
	private void checkResultScheduleRegister(String nameE,String roleE, int hours, String nameS,  Date date, Time startTime, Time endTime, StaffManager sm2){
		assertEquals(1, sm2.getEmployees().size());
		assertEquals(nameE, sm2.getEmployee(0).getStaffName());
		assertEquals(roleE, sm2.getEmployee(0).getStaffRoles());
		assertEquals(hours, sm2.getEmployee(0).getHours());
		assertEquals(1, sm2.getSchedules().size());
		assertEquals(date.toString(), sm2.getSchedule(0).getDate().toString());
		assertEquals(startTime.toString(), sm2.getSchedule(0).getStartTime().toString());
		assertEquals(endTime.toString(), sm2.getSchedule(0).getEndTime().toString());
		assertEquals(1, sm2.getScheduleRegistrations().size());
		assertEquals(nameS, sm2.getSchedule(0).getWeekday());
		assertEquals(sm2.getSchedule(0), sm2.getScheduleRegistration(0).getSchedule());
		assertEquals(sm2.getEmployee(0), sm2.getScheduleRegistration(0).getEmployee());
		
	}
	
	private void checkResultSupply(String foodName, int foodQty,  OrderManager om2) {
		assertEquals(1, om2.getFoodSupplies().size());
		assertEquals(foodName, om2.getFoodSupply(0).getFoodName());
		assertEquals(foodQty, om2.getFoodSupply(0).getFoodQty());
		assertEquals(0, om2.getEquipments().size());
	}


	private void checkResultEquipment(String equipmentName, int equipmentQty,  OrderManager om2) {
		assertEquals(1, om2.getEquipments().size());
		assertEquals(equipmentName, om2.getEquipment(0).getEquipmentName());
		assertEquals(equipmentQty, om2.getEquipment(0).getEquipmentQty());
		assertEquals(0, om2.getFoodSupplies().size());
	}

	private void checkResultMenu(String menuName, String ingredientName, int ingredientQty, double price,  OrderManager om2) {
		assertEquals(1, om2.getMenus().size());
		assertEquals(menuName, om2.getMenus(0).getMealName());
		assertEquals(ingredientName, om2.getMenus(0).getIngredientName());
		assertEquals(ingredientQty, om2.getMenus(0).getIngredientQty());
		assertEquals(0, om2.getTracker().size());
	}

	private void checkResultTracker(String menuName, int saleQty, OrderManager om2) {
		assertEquals(0, om2.getMenus().size());
		assertEquals(menuName, om2.getTracker(0).getSaleName());
		assertEquals(saleQty, om2.getTracker(0).getSaleQty());
		assertEquals(1, om2.getTracker().size());
	}

}



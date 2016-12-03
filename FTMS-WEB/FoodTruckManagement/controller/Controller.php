<?php
require_once '/Applications/XAMPP/xamppfiles/htdocs/FoodTruckManagement/controller/InputValidator.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/FoodTruckManagement/persistence/PersistenceFoodTruckManager.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/FoodTruckManagement/persistence/PersistenceMenuFTMS.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/FoodTruckManagement/model/Schedule.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/FoodTruckManagement/model/StaffManager.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/FoodTruckManagement/model/Employee.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/FoodTruckManagement/model/ScheduleRegistration.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/FoodTruckManagement/model/Supply.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/FoodTruckManagement/model/Equipment.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/FoodTruckManagement/model/Menu.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/FoodTruckManagement/model/MenuItem.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/FoodTruckManagement/model/OrderTracker.php';

class Controller{

	public function __construct(){
		
	}
	public function createEmployee($employee_name, $employee_role, $employee_hours){
		$name = InputValidator::validate_input($employee_name);
		$role = InputValidator::validate_input($employee_role);
		$hours = InputValidator::validate_input($employee_hours);
		$error = "";
		
		if(($name != null || strlen($name)!=0) && ($role == "Chef" || $role == "Cashier") && (($hours != null || strlen($hours)!=0)&& (int)$hours <= 40)){
			//Load data
			$pm = new PersistenceFoodTruckManager();
			$sm = $pm->loadDataFromStore();
			
			//Add employee
			$employee = new Employee($name, $role, $hours);
			$sm->addEmployee($employee);
			
			//Write data
			$pm->writeDataToStore($sm);
		}else{
			if($name == null || strlen($name) == 0){
				$error .= "Employee name cannot be empty! ";
			}
			if($role == null || strlen($role) == 0){
				$error .= "Employee role must be specified correctly! ";
			}
			if($hours == null || strlen($hours) == 0){
				$error .= "Employee hours must be specified correctly! ";
			}
			if(($role != "Chef" && $role != "Cashier") && ($role != null || strlen($role) != 0)){
				$error .= "Employee role should be a Chef or Cashier! ";
			}
			if((int)$hours> 40){
				$error .= "Employee must not have over 40 hours! ";
			}
			throw new Exception(trim($error));
		}
	}
	public function createSchedule($weekday, $date, $starttime, $endtime){
		//1. Validate Input
		$name = InputValidator::validate_input($weekday);
		date('Y-m-d', strtotime($date));
		date('H:i', strtotime($starttime));
		date('H:i', strtotime($endtime));
		$scheduledate = InputValidator::validate_input($date);
		$schedulestarttime = InputValidator::validate_input($starttime);
		$scheduleendtime = InputValidator::validate_input($endtime);
		$error = "";
		

		if(($name != null || strlen($name) != 0)&& $scheduledate != null && $schedulestarttime != null && $scheduleendtime != null && strtotime($scheduleendtime)> strtotime($schedulestarttime)){
			//2. Load all of the data
			$pm = new PersistenceFoodTruckManager();
			$sm = $pm->loadDataFromStore();
				
			//3. Add the new component of the event
			$schedule = new Schedule($name, $scheduledate, $schedulestarttime, $scheduleendtime);
			
			$sm->addSchedule($schedule);
		
			//4. Write all of the data
			$pm->writeDataToStore($sm);
		}else{
			if($name == null || strlen($name) == 0){
				$error .= "@1Weekday cannot be empty! ";
			}
			if($scheduledate == null){
				$error .= "@2Schedule date must be specified correctly (YYYY-MM-DD)! ";
			}
			if($schedulestarttime == null){
				$error .= "@3Schedule start time must be specified correctly (HH:MM)! ";
			}
			if($scheduleendtime == null){
				$error .= "@4Schedule end time must be specified correctly (HH:MM)! ";
			}
			if($scheduleendtime != null && $schedulestarttime != null && strtotime($schedulestarttime)>= strtotime($scheduleendtime)){
				$error .= "@4Schedule end time cannot before event start time!";
			}
			throw new Exception(trim($error));
		}
	}
	public function register($anEmployee, $aSchedule){
		//Load Data
		$pm = new PersistenceFoodTruckManager();
		$sm = $pm->loadDataFromStore();
		
		//Find Employee
		$myemployee = NULL;
		foreach ($sm->getEmployees() as $employee){
			if(strcmp($employee->getStaffName(), $anEmployee) == 0){
				$myemployee = $employee;
				break;
			}
		}
		
		//Find Schedule
		$myschedule = NULL;
		foreach ($sm->getSchedules() as $schedule){
			if(strcmp($schedule->getWeekday(), $aSchedule) == 0){
				$myschedule = $schedule;
				break;
			}
		}
		
		//Register for schedule
		$error = "";
		if($myemployee != NULL && $myschedule != NULL){
			$myregistration = new ScheduleRegistration($myemployee, $myschedule);
			$sm -> addScheduleRegistration($myregistration);
			$pm->writeDataToStore($sm);
		}else{
			if($myemployee == NULL){
				$error .= "@1Employee ";
				if ($anEmployee != NULL) {
					$error .= $anEmployee;
				}
				$error .= " not found! ";
			}
			if($myschedule == NULL){
				$error .= "@2Schedule ";
				if ($aSchedule != NULL){
					$error .= $aSchedule;
				}
				$error .= " not found!";
			}
			throw new Exception(trim($error));
		}
	}
	public function createEquipment($aName, $aQuantity) {
		// 1. Validate input
		$equipment = new Equipment($aName, $aQuantity);
	
		// 2. Load all of the data
		$pm = new PersistenceFoodTruckManager();
		$sm = $pm->loadDataFromStore();
	
		$myname = NULL;
		if (strcmp($equipment->getName(), $aName) == 0) {
			$myname = InputValidator::validate_input($aName);
		}
		$myquantity = NULL;
		if (strcmp($equipment->getQuantity(), $aQuantity) == 0) {
			$myquantity = $aQuantity;
		}
	
		// 3. Add the new equipment
		if ($myname != null && $myquantity != null) {
			$myequipment = new Equipment($myname, $myquantity);
			$sm->addEquipment($myequipment);
				
			// 4. Write all of the data
			$pm->writeDataToStore($sm);
		} else {
			$error = "";
			if ($myname == null || strlen($myname) == 0) {
				$error .= "@1Equipment name cannot be empty! ";
			} if ($myquantity == null || trim($myquantity) == "") {
				$error .= "@2Equipment quantity cannot be empty!";
			} throw new Exception(trim($error));
		}
	}
	
	
	public function createSupply($aName, $aQuantity) {
		// 1. Validate input
		$supply = new Supply($aName, $aQuantity);
	
		// 2. Load all of the data
		$pm2 = new PersistenceMenuFTMS();
		$mm = $pm2->loadDataFromStore();
	
		$myname = NULL;
		if (strcmp($supply->getName(), $aName) == 0) {
			$myname = InputValidator::validate_input($aName);
		}
		$myquantity = NULL;
		if (strcmp($supply->getQuantity(), $aQuantity) == 0) {
			$myquantity = $aQuantity;
		}
	
		// 3. Add the new supply
		if ($myname != null && $myquantity != null) {
			$mysupply = new Supply($myname, $myquantity);
			$mm->addSupply($mysupply);
	
			// 4. Write all of the data
			$pm2->writeDataToStore($mm);
		} else {
			$error = "";
			if ($myname == null || strlen($myname) == 0) {
				$error .= "@1Supply name cannot be empty! ";
			} if ($myquantity == null || trim($myquantity) == "") {
				$error .= "@2Supply quantity cannot be empty!";
			} throw new Exception(trim($error));
		}
	}
	
	
	public function createItem($aName, $aPopularity, $aPrice) {
		// 1. Validate input
		$item = new MenuItem($aName, $aPopularity, $aPrice);
	
		// 2. Load all of the data
		$pm2 = new PersistenceMenuFTMS();
		$mm = $pm2->loadDataFromStore();
	
		$myname = NULL;
		if (strcmp($item->getName(), $aName) == 0) {
			$myname = InputValidator::validate_input($aName);
		}
		$mypopularity = NULL;
		if (strcmp($item->getPopularity(), $aPopularity) == 0) {
			$mypopularity = $aPopularity;
		} else {
			$mypopularity = 0;
		}
		$myprice = NULL;
		if (strcmp($item->getPrice(), $aPrice) == 0) {
			$myprice = $aPrice;
		}
	
		// 3. Add the new item
		if ($myname != null && $mypopularity != null && $myprice != null) {
			$myitem = new MenuItem($myname, $mypopularity, $myprice);
			$mm->addMenuItem($myitem);
	
			// 4. Write all of the data
			$pm2->writeDataToStore($mm);
		} else {
			$error = "";
			if ($myname == null || strlen($myname) == 0) {
				$error .= "@1Menu item name cannot be empty! ";
			} if ($myprice == null || trim($myprice) == "") {
				$error .= "@2Menu item price cannot be empty!";
			} throw new Exception(trim($error));
		}
	}
	public function createSuppliesToMenuItem ($aItem, $aSupplies) {
	
		// 1. Load all of the data
		$pm2 = new PersistenceMenuFTMS();
		$mm = $pm2->loadDataFromStore();
	
		// 2. Find the menu item name and supplies
		$myitem = NULL;
		foreach ($mm->getMenuItems() as $MenuItem) {
			if (strcmp($MenuItem->getName(), $aItem) == 0) {
				$myitem = $MenuItem;
				break;
			}
		}
		$mysupplies = NULL;
		foreach ($mm->getSupplies() as $Supplies) {
			if (strcmp($Supplies->getName(), $aSupplies) == 0) {
				$mysupplies = $Supplies;
				break;
			}
		}
	
		// 3. Add the supplies to the corresponding menu item
		$error = "";
		if ($myitem != NULL && $mysupplies != NULL) {
			foreach ($mm->getMenuItems() as $MenuItem) {
				$MenuItem->addsupply($mysupplies);
			}
		}
	}
	
	
public function createOrder ($aName) {
		// 1. Load all of the data
		$pm2 = new PersistenceMenuFTMS();
		$mm = $pm2->loadDataFromStore();
	
		// 2. Find the menu item names, popularities, and supplies
		$myitem = NULL;
		foreach ($mm->getMenuItems() as $menuitem) {
			$myitem = $menuitem;
		}
		if ($myitem != null) {
			foreach ( $myitem->getPopularity () as $popularity ) {
				$mypopularity = $popularity + 1;
				$myitem->setPopularity ( $mypopularity );
			}
			foreach ( $myitem->getSupplies () as $supplies ) {
				$mysupplies = $supplies;
				$mymenu->removeSupply ( $supplies );
			}
		}
	
		// 3. Order the desired menu item
		$error = "";
		if ($myitem != NULL && $mypopularity != NULL && $mysupplies != NULL) {
			$myorder = new Order($myitem);
			$mm->addOrder($myorder);
			$pm2->writeDataToStore($mm);
		} else {
			if ($myitem == null || strlen($myitem) == 0) {
				throw new Exception("@1Order item cannot be empty! ");
			}
			if ($mysupplies == 0) {
				throw new Exception("@2Order item cannot be made due to lack of supplies!");
			}
		}
	}
}
?>
<?php
require_once '/home/benwilletts/workspace/FoodTruckManagement/persistence/PersistenceFoodTruckManager.php';
require_once '/home/benwilletts/workspace/FoodTruckManagement/model/Employee.php';
require_once '/home/benwilletts/workspace/FoodTruckManagement/model/StaffManager.php';

class PersistenceFoodTruckManagerTest extends PHPUnit_Framework_TestCase{
	protected $pm;
		
	protected function setUp(){
		$this->pm = new PersistenceFoodTruckManager();
		$this->pm2 = new PersistenceMenuFTMS();
	}
	
	protected function tearDown(){
		
	}
	
	public function testPersistence(){
		//1. Create Data
		$sm = StaffManager::getInstance();
		$staff = new Employee("Bob", "Chef", "40");
		$sm->addEmployee($staff);
		
		//Writing Data
		$this->pm->writeDataToStore($sm);
		
		//clear data
		$sm->delete();
		$this->assertEquals(0, count($sm->getEmployees()));
		
		//load data in
		$sm = $this->pm->loadDataFromStore();
		
		//Check loaded data
		$this->assertEquals(1, count($sm->getEmployees()));
		$myEmployee = $sm->getEmployee_index(0);
		$this->assertEquals("Bob", $myEmployee->getStaffName());
		$this->assertEquals("Chef", $myEmployee->getStaffRoles());
		$this->assertEquals("40", $myEmployee->getHours());
	}
	public function testPersistenceMenuManager() {
		// 1. Create test data
		$mm = Menu::getInstance();
		$menuitem = new MenuItem("Food");
		$mm->addMenuItem($menuitem);
	
		// 2. Write all of the data
		$this->pm2->writeDataToStore($mm);
	
		// 3. Clear the data from memory
		$mm->delete();
	
		$this->assertEquals(0, count($mm->getMenuItems()));
	
		// 4. Load it back in
		$mm = $this->pm2->loadDataFromStore();
	
		// 5. Check that we got it back
		$this->assertEquals(1, count($mm->getMenuItems()));
		$myMenuItem = $mm->getMenuItem_index(0);
		$this->assertEquals("Food", $myMenuItem->getName());
	}
}
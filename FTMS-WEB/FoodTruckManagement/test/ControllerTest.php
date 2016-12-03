<?php
require_once '/Applications/XAMPP/xamppfiles/htdocs/FoodTruckManagement/controller/Controller.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/FoodTruckManagement/persistence/PersistenceFoodTruckManager.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/FoodTruckManagement/model/Schedule.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/FoodTruckManagement/model/StaffManager.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/FoodTruckManagement/model/Employee.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/FoodTruckManagement/model/ScheduleRegistration.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/FoodTruckManagement/model/Menu.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/FoodTruckManagement/model/Equipment.php';


class ControllerTest extends PHPUnit_Framework_TestCase
{
    protected $c;
    protected $pm;
    protected $sm;

    protected function setUp()
    {
        $this->c = new Controller();
        
        $this->pm = new PersistenceFoodTruckManager();
        $this->sm = $this->pm->loadDataFromStore();
        $this->sm->delete();
        $this->pm->writeDataToStore($this->sm);
        
        $this->pm2 = new PersistenceMenuFTMS();
        $this->mm = $this->pm2->loadDataFromStore();
        $this->mm->delete();
        $this->pm2->writeDataToStore($this->mm);
    }

    protected function tearDown()
    {
    }

    public function testCreateEmployee() {
        $this->assertEquals(0, count($this->sm->getEmployees()));
    
    	$name = "Bob";
    	$role = "Chef";
    	$hours = "40";
    
    	try {
    		$this->c->createEmployee($name, $role, $hours);
    	} catch (Exception $e) {
    		// check that no error occurred
    		$this->fail();
    	}
    
    	// check file contents
    	$this->sm = $this->pm->loadDataFromStore();
    	$this->assertEquals(1, count($this->sm->getEmployees()));
    	$this->assertEquals($name, $this->sm->getEmployee_index(0)->getStaffName());
    	$this->assertEquals($role, $this->sm->getEmployee_index(0)->getStaffRoles());
    	$this->assertEquals($hours, $this->sm->getEmployee_index(0)->getHours());
    }
    
    public function testcreateEmployeeNull() {
        $this->assertEquals(0, count($this->sm->getEmployees()));
    
    	$name = null;
    	$role = null;
    	$hours = null;
    
    	$error = "";
    	try {
    		$this->c->createEmployee($name,$role,$hours);
    	} catch (Exception $e) {
			$error = $e->getMessage();
    	}
    
    	// check error
    	$this->assertEquals("Employee name cannot be empty! Employee role must be specified correctly! Employee hours must be specified correctly!", $error);
        // check file contents
    	$this->sm = $this->pm->loadDataFromStore();
    	$this->assertEquals(0, count($this->sm->getEmployees()));
    }
    
    public function testcreateEmployeeEmpty() {
    	$this->assertEquals(0, count($this->sm->getEmployees()));
    
    	$name = "";
    	$role = "";
    	$hours = "";
    
    	$error = "";
    	try {
    		$this->c->createEmployee($name, $role, $hours);
    	} catch (Exception $e) {
    		$error = $e->getMessage();
    	}
    
    	// check error
    	$this->assertEquals("Employee name cannot be empty! Employee role must be specified correctly! Employee hours must be specified correctly!", $error);
    	// check file contents
    	$this->sm = $this->pm->loadDataFromStore();
    	$this->assertEquals(0, count($this->sm->getEmployees()));
    }
    
    public function testcreateEmployeeSpaces() {
    	$this->assertEquals(0, count($this->sm->getEmployees()));
    
    	$name = " ";
    	$role = " ";
    	$hours = " ";
    
    	$error = "";
    	try {
    		$this->c->createEmployee($name, $role, $hours);
    	} catch (Exception $e) {
    		$error = $e->getMessage();
    	}
    
    	// check error
    	$this->assertEquals("Employee name cannot be empty! Employee role must be specified correctly! Employee hours must be specified correctly!", $error);
    	// check file contents
    	$this->sm = $this->pm->loadDataFromStore();
    	$this->assertEquals(0, count($this->sm->getEmployees()));
    }
    public function testcreateEmployeeWrongRole() {
    	$this->assertEquals(0, count($this->sm->getEmployees()));
    
    	$name = "Bob";
    	$role = "Driver";
    	$hours = "40";
    
    	$error = "";
    	try {
    		$this->c->createEmployee($name, $role, $hours);
    	} catch (Exception $e) {
    		$error = $e->getMessage();
    	}
    
    	// check error
    	$this->assertEquals("Employee role should be a Chef or Cashier!", $error);
    	// check file contents
    	$this->sm = $this->pm->loadDataFromStore();
    	$this->assertEquals(0, count($this->sm->getEmployees()));
    }    
    public function testcreateEmployeeOverHours() {
    	$this->assertEquals(0, count($this->sm->getEmployees()));
    
    	$name = "Bob";
    	$role = "Chef";
    	$hours = "41";
    
    	$error = "";
    	try {
    		$this->c->createEmployee($name, $role, $hours);
    	} catch (Exception $e) {
    		$error = $e->getMessage();
    	}
    
    	// check error
    	$this->assertEquals("Employee must not have over 40 hours!", $error);
    	// check file contents
    	$this->sm = $this->pm->loadDataFromStore();
    	$this->assertEquals(0, count($this->sm->getEmployees()));
    }
    
    
    
public function testCreateEquipment() {
		$this->assertEquals(0, count($this->sm->getEquipment()));
		
		$equipment = "Knife";
		$quantity = "1";
		
		try { 
			$this->c->createEquipment($equipment, $quantity);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
		
		// Check file contents are proper 
		$this->sm = $this->pm->loadDataFromStore();
		//$this->assertEquals(1, count($this->sm->getEquipment()));
		//$this->assertEquals($equipment, $this->sm->getEquipment_index(0)->getName());
		$this->assertEquals(0, count($this->sm->getEmployees()));		
	}
	
	
	public function testCreateEquipmentNull() {
		$this->assertEquals(0, count($this->sm->getEquipment()));
		
		$equipment = null;
		$quantity = null;
		$error = "";
	
		try {
			$this->c->createEquipment($equipment, $quantity);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		// Check the proper error messages are thrown
		$this->assertEquals("@1Equipment name cannot be empty! @2Equipment quantity cannot be empty!", $error);
		// Check file contents are proper
		$this->sm = $this->pm->loadDataFromStore();
		$this->assertEquals(0, count($this->sm->getEquipment()));
		$this->assertEquals(0, count($this->sm->getEmployees()));
	}
	
	
	public function testCreateEquipmentEmpty() {
		$this->assertEquals(0, count($this->sm->getEquipment()));
	
		$equipment = "";
		$quantity = "";
		$error = "";
	
		try {
			$this->c->createEquipment($equipment, $quantity);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
		// Check the proper error messages are thrown
		$this->assertEquals("@1Equipment name cannot be empty! @2Equipment quantity cannot be empty!", $error);
		// Check file contents are proper
		$this->sm = $this->pm->loadDataFromStore();
		$this->assertEquals(0, count($this->sm->getEquipment()));
		$this->assertEquals(0, count($this->sm->getEmployees()));
	}
	
	
	public function testCreateEquipmentSpaces() {
		$this->assertEquals(0, count($this->sm->getEquipment()));
	
		$equipment = " ";
		$quantity = " ";
		$error = "";
	
		try {
			$this->c->createEquipment($equipment, $quantity);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		// Check the proper error messages are thrown
		$this->assertEquals("@1Equipment name cannot be empty! @2Equipment quantity cannot be empty!", $error);
		// Check file contents are proper
		$this->sm = $this->pm->loadDataFromStore();
		$this->assertEquals(0, count($this->sm->getEquipment()));
		$this->assertEquals(0, count($this->sm->getEmployees()));
	}
	
	
	public function testCreateSupply() {
		$this->assertEquals(0, count($this->mm->getSupplies()));
		
		$name = "Cheese";
		$quantity = "12";
		
		try {
			$this->c->createSupply($name, $quantity);
		} catch (Exception $e) {
			$this->fail();
		}
		
		// Check file contents are proper
		$this->mm = $this->pm2->loadDataFromStore();
		$this->assertEquals(1, count($this->mm->getSupplies()));
		$this->assertEquals($name, $this->mm->getSupply_index(0)->getName());
		$this->assertEquals($quantity, $this->mm->getSupply_index(0)->getQuantity());
		$this->assertEquals(0, count($this->mm->getMenuItems()));
		$this->assertEquals(0, count($this->mm->getOrders()));
	}
	
	
	public function testCreateSupplyNull() {
		$this->assertEquals(0, count($this->mm->getSupplies()));
		
		$name = null;
		$quantity = null;
		
		try {
			$this->c->createSupply($name, $quantity);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
		
		// Check the proper error messages are thrown
		$this->assertEquals("@1Supply name cannot be empty! @2Supply quantity cannot be empty!", $error);
		// Check file contents are proper
		$this->mm = $this->pm2->loadDataFromStore();
		$this->assertEquals(0, count($this->mm->getSupplies()));
		$this->assertEquals(0, count($this->mm->getMenuItems()));
		$this->assertEquals(0, count($this->mm->getOrders()));
	}
	
	
	public function testCreateSupplyEmpty() {
		$this->assertEquals(0, count($this->mm->getSupplies()));
	
		$name = "";
		$quantity = "";
	
		try {
			$this->c->createSupply($name, $quantity);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		// Check the proper error messages are thrown
		$this->assertEquals("@1Supply name cannot be empty! @2Supply quantity cannot be empty!", $error);
		// Check file contents are proper
		$this->mm = $this->pm2->loadDataFromStore();
		$this->assertEquals(0, count($this->mm->getSupplies()));
		$this->assertEquals(0, count($this->mm->getMenuItems()));
		$this->assertEquals(0, count($this->mm->getOrders()));
	}
	
	
	public function testCreateSupplySpaces() {
		$this->assertEquals(0, count($this->mm->getSupplies()));
	
		$name = " ";
		$quantity = " ";
	
		try {
			$this->c->createSupply($name, $quantity);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		// Check the proper error messages are thrown
		$this->assertEquals("@1Supply name cannot be empty! @2Supply quantity cannot be empty!", $error);
		// Check file contents are proper
		$this->mm = $this->pm2->loadDataFromStore();
		$this->assertEquals(0, count($this->mm->getSupplies()));
		$this->assertEquals(0, count($this->mm->getMenuItems()));
		$this->assertEquals(0, count($this->mm->getOrders()));
	}
	
	
	public function testCreateItem() {
		$this->assertEquals(0, count($this->mm->getSupplies()));
		
		$name = "Poutine";
		$popularity = "99";
		$price = "5.99";
		
		try {
			$this->c->createItem($name, $popularity, $price);
		} catch (Exception $e) {
			$this->fail();
		}
		
		// Check file contents are proper
		$this->mm = $this->pm2->loadDataFromStore();
		$this->assertEquals(1, count($this->mm->getMenuItems()));
		$this->assertEquals($name, $this->mm->getMenuItem_index(0)->getName());
		$this->assertEquals($popularity, $this->mm->getMenuItem_index(0)->getPopularity());
		$this->assertEquals($price, $this->mm->getMenuItem_index(0)->getPrice());
		$this->assertEquals(0, count($this->mm->getSupplies()));
		$this->assertEquals(0, count($this->mm->getOrders()));
	}
		
	
	public function testCreateItemNull() {
		$this->assertEquals(0, count($this->mm->getSupplies()));
	
		$name = null;
		$popularity = null;
		$price = null;
	
		try {
			$this->c->createItem($name, $popularity, $price);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
		
		// Check the proper error messages are thrown 
		$this->assertEquals("@1Menu item name cannot be empty! @2Menu item price cannot be empty!", $error);
		// Check file contents are proper
		$this->mm = $this->pm2->loadDataFromStore();
		$this->assertEquals(0, count($this->mm->getMenuItems()));
		$this->assertEquals(0, count($this->mm->getSupplies()));
		$this->assertEquals(0, count($this->mm->getOrders()));
	}
	
	
	public function testCreateItemEmpty() {
		$this->assertEquals(0, count($this->mm->getSupplies()));
	
		$name = "";
		$popularity = "";
		$price = "";
	
		try {
			$this->c->createItem($name, $popularity, $price);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		// Check the proper error messages are thrown
		$this->assertEquals("@1Menu item name cannot be empty! @2Menu item price cannot be empty!", $error);
		// Check file contents are proper
		$this->mm = $this->pm2->loadDataFromStore();
		$this->assertEquals(0, count($this->mm->getMenuItems()));
		$this->assertEquals(0, count($this->mm->getSupplies()));
		$this->assertEquals(0, count($this->mm->getOrders()));
	}
	
	
	public function testCreateItemSpaces() {
		$this->assertEquals(0, count($this->mm->getSupplies()));
	
		$name = " ";
		$popularity = " ";
		$price = " ";
	
		try {
			$this->c->createItem($name, $popularity, $price);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		// Check the proper error messages are thrown
		$this->assertEquals("@1Menu item name cannot be empty! @2Menu item price cannot be empty!", $error);
		// Check file contents are proper
		$this->mm = $this->pm2->loadDataFromStore();
		$this->assertEquals(0, count($this->mm->getMenuItems()));
		$this->assertEquals(0, count($this->mm->getSupplies()));
		$this->assertEquals(0, count($this->mm->getOrders()));
	}
	
	
	public function testOrder() {
		$this->assertEquals(0, count($this->mm->getMenuItems()));
		$this->assertEquals(0, count($this->mm->getSupplies()));
		$this->assertEquals(0, count($this->sm->getEmployees()));
		$this->assertEquals(0, count($this->mm->getOrders()));
		
		$item = "Poutine";
		
		$name = "Poutine";
		$popularity = "99";
		$price = "5.99";
		
		try {
			$this->c->createItem($name, $popularity, $price);
		} catch (Exception $e) {
			$this->fail();
		}
		
		$name2 = "Fries";
		$quantity2 = "52";
		
		$name3 = "Cheese Curds";
		$quantity3 = "30";
		
		$name4 = "Gravy";
		$quantity4 = "5";
		
		try {
			$this->c->createSupply($name2, $quantity2);
			$this->c->createSupply($name3, $quantity3);
			$this->c->createSupply($name4, $quantity4);
		} catch (Exception $e) {
			$this->fail();
		}
		
		$this->mm = $this->pm2->loadDataFromStore();
		$this->assertEquals(1, count($this->mm->getMenuItems()));
		$this->assertEquals(3, count($this->mm->getSupplies()));
		
		try {
			$this->c->createOrder($item);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
		
		// Check file contents are proper
		$this->mm = $this->pm2->loadDataFromStore();
		$this->assertEquals(1, count($this->mm->getMenuItems()));
		$this->assertEquals($item, $this->mm->getMenuItem_index(0)->getName());
		$this->assertEquals($popularity, $this->mm->getMenuItem_index(0)->getPopularity());
		$this->assertEquals($price, $this->mm->getMenuItem_index(0)->getPrice());
		$this->assertEquals(3, count($this->mm->getSupplies()));
		//$this->assertEquals($name, $this->mm->getSupply_index(0)->getName());
		//$this->assertEquals($quantity, $this->mm->getSupply_index(0)->getQuantity());
		$this->assertEquals($name2, $this->mm->getSupply_index(0)->getName());
		$this->assertEquals($quantity2, $this->mm->getSupply_index(0)->getQuantity());
		$this->assertEquals($name3, $this->mm->getSupply_index(1)->getName());
		$this->assertEquals($quantity3, $this->mm->getSupply_index(1)->getQuantity());
		$this->assertEquals($name4, $this->mm->getSupply_index(2)->getName());
		$this->assertEquals($quantity4, $this->mm->getSupply_index(2)->getQuantity());
		//$this->assertEquals(1, count($this->mm->getOrders()));
		//$this->assertEquals($this->mm->getMenuItem_index(0), $this->mm->getOrder_index(0)->getMenuItems());
		//$this->assertEquals($this->mm->getSupply_index(0), $this->mm->getOrder_index(0)->getSupplies());
		//$this->assertEquals($this->mm->getSupply_index(1), $this->mm->getOrder_index(1)->getSupplies());
		//$this->assertEquals($this->mm->getSupply_index(2), $this->mm->getOrder_index(2)->getSupplies());
	}
	
	
	public function testOrderNull() {
		$this->assertEquals(0, count($this->mm->getMenuItems()));
		$this->assertEquals(0, count($this->mm->getSupplies()));
		$this->assertEquals(0, count($this->sm->getEmployees()));
		$this->assertEquals(0, count($this->mm->getOrders()));
		
		$item = null;
		
		try {
			$this->c->createOrder($item);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
		
		$this->assertEquals("@1Order item cannot be empty! ", $error);
		// Check file contents are proper
		$this->mm = $this->pm2->loadDataFromStore();
		$this->assertEquals(0, count($this->mm->getMenuItems()));
    	$this->assertEquals(0, count($this->mm->getSupplies()));
    	$this->assertEquals(0, count($this->sm->getEmployees()));
    	$this->assertEquals(0, count($this->mm->getOrders()));
	}
	
	
	public function testOrderItemDoesNotExist() {
		$this->assertEquals(0, count($this->mm->getMenuItems()));
		$this->assertEquals(0, count($this->mm->getSupplies()));
		$this->assertEquals(0, count($this->sm->getEmployees()));
		$this->assertEquals(0, count($this->mm->getOrders()));
		
		$item = "Pizza";
		
		try {
			$this->c->createOrder($item);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
		
		$this->mm = $this->pm2->loadDataFromStore();
		$this->assertEquals(0, count($this->mm->getMenuItems()));
		//$item = $this->mm->getMenuItem_index(0);
		$this->mm->delete();
		$this->pm2->writeDataToStore($this->mm);
		
		try {
			$this->c->createOrder($item);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
		
		// Check the proper error messages are thrown 
		$this->assertEquals("@1Order item cannot be empty! ");
		// Check file contents are proper
		$this->mm = $this->pm2->loadDataFromStore();
		$this->assertEquals(0, count($this->mm->getMenuItems()));
		$this->assertEquals(0, count($this->mm->getSupplies()));
		$this->assertEquals(0, count($this->sm->getEmployees()));
		$this->assertEquals(0, count($this->mm->getOrders()));
	}
	
	
	public function testOrderInsufficientSupplies() {
		$this->assertEquals(0, count($this->mm->getMenuItems()));
		
		$item = "Poutine";
		$popularity = null;
		$price = "12.99";
		
		try {
			$this->c->createItem($name, $popularity, $price);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
		
		$name = "Fries";
		$quantity = "0";
		
		try {
			$this->c->createSupply($name, $quantity);
		} catch (Exception $e) {
			$this->fail();
		}
		
		$this->mm = $this->pm2->loadDataFromStore();
		$this->assertEquals(1, count($this->mm->getMenuItems()));
		$this->assertEquals(1, count($this->mm->getSupplies()));
		
		try {
			$this->c->order($item);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
		
		// Check that the proper errors are thrown 
		$this->assertEquals("@2Order item cannot be made due to lack of supplies!", $error);
		// Check file contents are proper
		$this->mm = $this->pm2->loadDataFromStore();
		$this->assertEquals(1, count($this->mm->getMenuItems()));
		$this->assertEquals($item, $this->mm->getMenuItem_index(0)->getName());
		$this->assertEquals($popularity, $this->mm->getMenuItem_index(0)->getPopularity());
		$this->assertEquals($price, $this->mm->getMenuItem_index(0)->getPrice());
		$this->assertEquals(1, count($this->mm->getSupplies()));
		$this->assertEquals($name, $this->mm->getSupply_index(0)->getName());
		$this->assertEquals($quantity, $this->mm->getSupply_index(0)->getQuantity());
		$this->assertEquals(1, count($this->mm->getOrders()));
		$this->assertEquals($this->mm->getMenuItem_index(0), $this->mm->getOrder_index(0)->getMenuItems());
		$this->assertEquals($this->mm->getSupply_index(0), $this->mm->getOrder_index(0)->getSupplies());
	}
    
}
?>

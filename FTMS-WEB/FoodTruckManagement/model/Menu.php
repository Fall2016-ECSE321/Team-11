<?php

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-edef018 modeling language!*/

class Menu
{

	//------------------------
	// STATIC VARIABLES
	//------------------------
	
	private static $theInstance = null;
	
	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//Menu Associations
	private $menuItems;
	private $supplies;
	private $orders;

	//------------------------
	// CONSTRUCTOR
	//------------------------

	private function __construct()
	{
		$this->menuItems = array();
		$this->supplies = array();
		$this->orders = array();
	}
	public static function getInstance()
	{
		if(self::$theInstance == null)
		{
			self::$theInstance = new Menu();
		}
		return self::$theInstance;
	}

	//------------------------
	// INTERFACE
	//------------------------

	public function getMenuItem_index($index)
	{
		$aMenuItem = $this->menuItems[$index];
		return $aMenuItem;
	}

	public function getMenuItems()
	{
		$newMenuItems = $this->menuItems;
		return $newMenuItems;
	}

	public function numberOfMenuItems()
	{
		$number = count($this->menuItems);
		return $number;
	}

	public function hasMenuItems()
	{
		$has = $this->numberOfMenuItems() > 0;
		return $has;
	}

	public function indexOfMenuItem($aMenuItem)
	{
		$wasFound = false;
		$index = 0;
		foreach($this->menuItems as $menuItem)
		{
			if ($menuItem->equals($aMenuItem))
			{
				$wasFound = true;
				break;
			}
			$index += 1;
		}
		$index = $wasFound ? $index : -1;
		return $index;
	}

	public function getSupply_index($index)
	{
		$aSupply = $this->supplies[$index];
		return $aSupply;
	}

	public function getSupplies()
	{
		$newSupplies = $this->supplies;
		return $newSupplies;
	}

	public function numberOfSupplies()
	{
		$number = count($this->supplies);
		return $number;
	}

	public function hasSupplies()
	{
		$has = $this->numberOfSupplies() > 0;
		return $has;
	}

	public function indexOfSupply($aSupply)
	{
		$wasFound = false;
		$index = 0;
		foreach($this->supplies as $supply)
		{
			if ($supply->equals($aSupply))
			{
				$wasFound = true;
				break;
			}
			$index += 1;
		}
		$index = $wasFound ? $index : -1;
		return $index;
	}

	public function getOrder_index($index)
	{
		$aOrder = $this->orders[$index];
		return $aOrder;
	}

	public function getOrders()
	{
		$newOrders = $this->orders;
		return $newOrders;
	}

	public function numberOfOrders()
	{
		$number = count($this->orders);
		return $number;
	}

	public function hasOrders()
	{
		$has = $this->numberOfOrders() > 0;
		return $has;
	}

	public function indexOfOrder($aOrder)
	{
		$wasFound = false;
		$index = 0;
		foreach($this->orders as $order)
		{
			if ($order->equals($aOrder))
			{
				$wasFound = true;
				break;
			}
			$index += 1;
		}
		$index = $wasFound ? $index : -1;
		return $index;
	}

	public static function minimumNumberOfMenuItems()
	{
		return 0;
	}

	public function addMenuItem($aMenuItem)
	{
		$wasAdded = false;
		if ($this->indexOfMenuItem($aMenuItem) !== -1) { return false; }
		$this->menuItems[] = $aMenuItem;
		$wasAdded = true;
		return $wasAdded;
	}

	public function removeMenuItem($aMenuItem)
	{
		$wasRemoved = false;
		if ($this->indexOfMenuItem($aMenuItem) != -1)
		{
			unset($this->menuItems[$this->indexOfMenuItem($aMenuItem)]);
			$this->menuItems = array_values($this->menuItems);
			$wasRemoved = true;
		}
		return $wasRemoved;
	}

	public function addMenuItemAt($aMenuItem, $index)
	{
		$wasAdded = false;
		if($this->addMenuItem($aMenuItem))
		{
			if($index < 0 ) { $index = 0; }
			if($index > $this->numberOfMenuItems()) { $index = $this->numberOfMenuItems() - 1; }
			array_splice($this->menuItems, $this->indexOfMenuItem($aMenuItem), 1);
			array_splice($this->menuItems, $index, 0, array($aMenuItem));
			$wasAdded = true;
		}
		return $wasAdded;
	}

	public function addOrMoveMenuItemAt($aMenuItem, $index)
	{
		$wasAdded = false;
		if($this->indexOfMenuItem($aMenuItem) !== -1)
		{
			if($index < 0 ) { $index = 0; }
			if($index > $this->numberOfMenuItems()) { $index = $this->numberOfMenuItems() - 1; }
			array_splice($this->menuItems, $this->indexOfMenuItem($aMenuItem), 1);
			array_splice($this->menuItems, $index, 0, array($aMenuItem));
			$wasAdded = true;
		}
		else
		{
			$wasAdded = $this->addMenuItemAt($aMenuItem, $index);
		}
		return $wasAdded;
	}

	public static function minimumNumberOfSupplies()
	{
		return 0;
	}

	public function addSupply($aSupply)
	{
		$wasAdded = false;
		if ($this->indexOfSupply($aSupply) !== -1) { return false; }
		$this->supplies[] = $aSupply;
		$wasAdded = true;
		return $wasAdded;
	}

	public function removeSupply($aSupply)
	{
		$wasRemoved = false;
		if ($this->indexOfSupply($aSupply) != -1)
		{
			unset($this->supplies[$this->indexOfSupply($aSupply)]);
			$this->supplies = array_values($this->supplies);
			$wasRemoved = true;
		}
		return $wasRemoved;
	}

	public function addSupplyAt($aSupply, $index)
	{
		$wasAdded = false;
		if($this->addSupply($aSupply))
		{
			if($index < 0 ) { $index = 0; }
			if($index > $this->numberOfSupplies()) { $index = $this->numberOfSupplies() - 1; }
			array_splice($this->supplies, $this->indexOfSupply($aSupply), 1);
			array_splice($this->supplies, $index, 0, array($aSupply));
			$wasAdded = true;
		}
		return $wasAdded;
	}

	public function addOrMoveSupplyAt($aSupply, $index)
	{
		$wasAdded = false;
		if($this->indexOfSupply($aSupply) !== -1)
		{
			if($index < 0 ) { $index = 0; }
			if($index > $this->numberOfSupplies()) { $index = $this->numberOfSupplies() - 1; }
			array_splice($this->supplies, $this->indexOfSupply($aSupply), 1);
			array_splice($this->supplies, $index, 0, array($aSupply));
			$wasAdded = true;
		}
		else
		{
			$wasAdded = $this->addSupplyAt($aSupply, $index);
		}
		return $wasAdded;
	}

	public static function minimumNumberOfOrders()
	{
		return 0;
	}

	public function addOrder($aOrder)
	{
		$wasAdded = false;
		if ($this->indexOfOrder($aOrder) !== -1) { return false; }
		$this->orders[] = $aOrder;
		$wasAdded = true;
		return $wasAdded;
	}

	public function removeOrder($aOrder)
	{
		$wasRemoved = false;
		if ($this->indexOfOrder($aOrder) != -1)
		{
			unset($this->orders[$this->indexOfOrder($aOrder)]);
			$this->orders = array_values($this->orders);
			$wasRemoved = true;
		}
		return $wasRemoved;
	}

	public function addOrderAt($aOrder, $index)
	{
		$wasAdded = false;
		if($this->addOrder($aOrder))
		{
			if($index < 0 ) { $index = 0; }
			if($index > $this->numberOfOrders()) { $index = $this->numberOfOrders() - 1; }
			array_splice($this->orders, $this->indexOfOrder($aOrder), 1);
			array_splice($this->orders, $index, 0, array($aOrder));
			$wasAdded = true;
		}
		return $wasAdded;
	}

	public function addOrMoveOrderAt($aOrder, $index)
	{
		$wasAdded = false;
		if($this->indexOfOrder($aOrder) !== -1)
		{
			if($index < 0 ) { $index = 0; }
			if($index > $this->numberOfOrders()) { $index = $this->numberOfOrders() - 1; }
			array_splice($this->orders, $this->indexOfOrder($aOrder), 1);
			array_splice($this->orders, $index, 0, array($aOrder));
			$wasAdded = true;
		}
		else
		{
			$wasAdded = $this->addOrderAt($aOrder, $index);
		}
		return $wasAdded;
	}

	public function equals($compareTo)
	{
		return $this == $compareTo;
	}

	public function delete()
	{
		$this->menuItems = array();
		$this->supplies = array();
		$this->orders = array();
	}

}
?>
<p></p>
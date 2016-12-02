<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

class OrderManager
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static $theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OrderManager Associations
  private $equipments;
  private $foodSupplies;
  private $menus;
  private $tracker;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private function __construct()
  {
    $this->equipments = array();
    $this->foodSupplies = array();
    $this->menus = array();
    $this->tracker = array();
  }

  public static function getInstance()
  {
    if(self::$theInstance == null)
    {
      self::$theInstance = new OrderManager();
    }
    return self::$theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getEquipment_index($index)
  {
    $aEquipment = $this->equipments[$index];
    return $aEquipment;
  }

  public function getEquipments()
  {
    $newEquipments = $this->equipments;
    return $newEquipments;
  }

  public function numberOfEquipments()
  {
    $number = count($this->equipments);
    return $number;
  }

  public function hasEquipments()
  {
    $has = $this->numberOfEquipments() > 0;
    return $has;
  }

  public function indexOfEquipment($aEquipment)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->equipments as $equipment)
    {
      if ($equipment->equals($aEquipment))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getFoodSupply_index($index)
  {
    $aFoodSupply = $this->foodSupplies[$index];
    return $aFoodSupply;
  }

  public function getFoodSupplies()
  {
    $newFoodSupplies = $this->foodSupplies;
    return $newFoodSupplies;
  }

  public function numberOfFoodSupplies()
  {
    $number = count($this->foodSupplies);
    return $number;
  }

  public function hasFoodSupplies()
  {
    $has = $this->numberOfFoodSupplies() > 0;
    return $has;
  }

  public function indexOfFoodSupply($aFoodSupply)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->foodSupplies as $foodSupply)
    {
      if ($foodSupply->equals($aFoodSupply))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getMenus_index($index)
  {
    $aMenus = $this->menus[$index];
    return $aMenus;
  }

  public function getMenus()
  {
    $newMenus = $this->menus;
    return $newMenus;
  }

  public function numberOfMenus()
  {
    $number = count($this->menus);
    return $number;
  }

  public function hasMenus()
  {
    $has = $this->numberOfMenus() > 0;
    return $has;
  }

  public function indexOfMenus($aMenus)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->menus as $menus)
    {
      if ($menus->equals($aMenus))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getTracker_index($index)
  {
    $aTracker = $this->tracker[$index];
    return $aTracker;
  }

  public function getTracker()
  {
    $newTracker = $this->tracker;
    return $newTracker;
  }

  public function numberOfTracker()
  {
    $number = count($this->tracker);
    return $number;
  }

  public function hasTracker()
  {
    $has = $this->numberOfTracker() > 0;
    return $has;
  }

  public function indexOfTracker($aTracker)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->tracker as $tracker)
    {
      if ($tracker->equals($aTracker))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfEquipments()
  {
    return 0;
  }

  public function addEquipment($aEquipment)
  {
    $wasAdded = false;
    if ($this->indexOfEquipment($aEquipment) !== -1) { return false; }
    $this->equipments[] = $aEquipment;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeEquipment($aEquipment)
  {
    $wasRemoved = false;
    if ($this->indexOfEquipment($aEquipment) != -1)
    {
      unset($this->equipments[$this->indexOfEquipment($aEquipment)]);
      $this->equipments = array_values($this->equipments);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addEquipmentAt($aEquipment, $index)
  {  
    $wasAdded = false;
    if($this->addEquipment($aEquipment))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEquipments()) { $index = $this->numberOfEquipments() - 1; }
      array_splice($this->equipments, $this->indexOfEquipment($aEquipment), 1);
      array_splice($this->equipments, $index, 0, array($aEquipment));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveEquipmentAt($aEquipment, $index)
  {
    $wasAdded = false;
    if($this->indexOfEquipment($aEquipment) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEquipments()) { $index = $this->numberOfEquipments() - 1; }
      array_splice($this->equipments, $this->indexOfEquipment($aEquipment), 1);
      array_splice($this->equipments, $index, 0, array($aEquipment));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addEquipmentAt($aEquipment, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfFoodSupplies()
  {
    return 0;
  }

  public function addFoodSupply($aFoodSupply)
  {
    $wasAdded = false;
    if ($this->indexOfFoodSupply($aFoodSupply) !== -1) { return false; }
    $this->foodSupplies[] = $aFoodSupply;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeFoodSupply($aFoodSupply)
  {
    $wasRemoved = false;
    if ($this->indexOfFoodSupply($aFoodSupply) != -1)
    {
      unset($this->foodSupplies[$this->indexOfFoodSupply($aFoodSupply)]);
      $this->foodSupplies = array_values($this->foodSupplies);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addFoodSupplyAt($aFoodSupply, $index)
  {  
    $wasAdded = false;
    if($this->addFoodSupply($aFoodSupply))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfFoodSupplies()) { $index = $this->numberOfFoodSupplies() - 1; }
      array_splice($this->foodSupplies, $this->indexOfFoodSupply($aFoodSupply), 1);
      array_splice($this->foodSupplies, $index, 0, array($aFoodSupply));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveFoodSupplyAt($aFoodSupply, $index)
  {
    $wasAdded = false;
    if($this->indexOfFoodSupply($aFoodSupply) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfFoodSupplies()) { $index = $this->numberOfFoodSupplies() - 1; }
      array_splice($this->foodSupplies, $this->indexOfFoodSupply($aFoodSupply), 1);
      array_splice($this->foodSupplies, $index, 0, array($aFoodSupply));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addFoodSupplyAt($aFoodSupply, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfMenus()
  {
    return 0;
  }

  public function addMenus($aMenus)
  {
    $wasAdded = false;
    if ($this->indexOfMenus($aMenus) !== -1) { return false; }
    $this->menus[] = $aMenus;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeMenus($aMenus)
  {
    $wasRemoved = false;
    if ($this->indexOfMenus($aMenus) != -1)
    {
      unset($this->menus[$this->indexOfMenus($aMenus)]);
      $this->menus = array_values($this->menus);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addMenusAt($aMenus, $index)
  {  
    $wasAdded = false;
    if($this->addMenus($aMenus))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfMenus()) { $index = $this->numberOfMenus() - 1; }
      array_splice($this->menus, $this->indexOfMenus($aMenus), 1);
      array_splice($this->menus, $index, 0, array($aMenus));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveMenusAt($aMenus, $index)
  {
    $wasAdded = false;
    if($this->indexOfMenus($aMenus) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfMenus()) { $index = $this->numberOfMenus() - 1; }
      array_splice($this->menus, $this->indexOfMenus($aMenus), 1);
      array_splice($this->menus, $index, 0, array($aMenus));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addMenusAt($aMenus, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfTracker()
  {
    return 0;
  }

  public function addTracker($aTracker)
  {
    $wasAdded = false;
    if ($this->indexOfTracker($aTracker) !== -1) { return false; }
    $this->tracker[] = $aTracker;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeTracker($aTracker)
  {
    $wasRemoved = false;
    if ($this->indexOfTracker($aTracker) != -1)
    {
      unset($this->tracker[$this->indexOfTracker($aTracker)]);
      $this->tracker = array_values($this->tracker);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addTrackerAt($aTracker, $index)
  {  
    $wasAdded = false;
    if($this->addTracker($aTracker))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfTracker()) { $index = $this->numberOfTracker() - 1; }
      array_splice($this->tracker, $this->indexOfTracker($aTracker), 1);
      array_splice($this->tracker, $index, 0, array($aTracker));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveTrackerAt($aTracker, $index)
  {
    $wasAdded = false;
    if($this->indexOfTracker($aTracker) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfTracker()) { $index = $this->numberOfTracker() - 1; }
      array_splice($this->tracker, $this->indexOfTracker($aTracker), 1);
      array_splice($this->tracker, $index, 0, array($aTracker));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addTrackerAt($aTracker, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->equipments = array();
    $this->foodSupplies = array();
    $this->menus = array();
    $this->tracker = array();
  }

}
?>
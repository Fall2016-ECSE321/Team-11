<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

class StaffManager
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static $theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //StaffManager Associations
  private $employees;
  private $schedules;
  private $scheduleRegistrations;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private function __construct()
  {
    $this->employees = array();
    $this->schedules = array();
    $this->scheduleRegistrations = array();
  }

  public static function getInstance()
  {
    if(self::$theInstance == null)
    {
      self::$theInstance = new StaffManager();
    }
    return self::$theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getEmployee_index($index)
  {
    $aEmployee = $this->employees[$index];
    return $aEmployee;
  }

  public function getEmployees()
  {
    $newEmployees = $this->employees;
    return $newEmployees;
  }

  public function numberOfEmployees()
  {
    $number = count($this->employees);
    return $number;
  }

  public function hasEmployees()
  {
    $has = $this->numberOfEmployees() > 0;
    return $has;
  }

  public function indexOfEmployee($aEmployee)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->employees as $employee)
    {
      if ($employee->equals($aEmployee))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getSchedule_index($index)
  {
    $aSchedule = $this->schedules[$index];
    return $aSchedule;
  }

  public function getSchedules()
  {
    $newSchedules = $this->schedules;
    return $newSchedules;
  }

  public function numberOfSchedules()
  {
    $number = count($this->schedules);
    return $number;
  }

  public function hasSchedules()
  {
    $has = $this->numberOfSchedules() > 0;
    return $has;
  }

  public function indexOfSchedule($aSchedule)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->schedules as $schedule)
    {
      if ($schedule->equals($aSchedule))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getScheduleRegistration_index($index)
  {
    $aScheduleRegistration = $this->scheduleRegistrations[$index];
    return $aScheduleRegistration;
  }

  public function getScheduleRegistrations()
  {
    $newScheduleRegistrations = $this->scheduleRegistrations;
    return $newScheduleRegistrations;
  }

  public function numberOfScheduleRegistrations()
  {
    $number = count($this->scheduleRegistrations);
    return $number;
  }

  public function hasScheduleRegistrations()
  {
    $has = $this->numberOfScheduleRegistrations() > 0;
    return $has;
  }

  public function indexOfScheduleRegistration($aScheduleRegistration)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->scheduleRegistrations as $scheduleRegistration)
    {
      if ($scheduleRegistration->equals($aScheduleRegistration))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfEmployees()
  {
    return 0;
  }

  public function addEmployee($aEmployee)
  {
    $wasAdded = false;
    if ($this->indexOfEmployee($aEmployee) !== -1) { return false; }
    $this->employees[] = $aEmployee;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeEmployee($aEmployee)
  {
    $wasRemoved = false;
    if ($this->indexOfEmployee($aEmployee) != -1)
    {
      unset($this->employees[$this->indexOfEmployee($aEmployee)]);
      $this->employees = array_values($this->employees);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addEmployeeAt($aEmployee, $index)
  {  
    $wasAdded = false;
    if($this->addEmployee($aEmployee))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEmployees()) { $index = $this->numberOfEmployees() - 1; }
      array_splice($this->employees, $this->indexOfEmployee($aEmployee), 1);
      array_splice($this->employees, $index, 0, array($aEmployee));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveEmployeeAt($aEmployee, $index)
  {
    $wasAdded = false;
    if($this->indexOfEmployee($aEmployee) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEmployees()) { $index = $this->numberOfEmployees() - 1; }
      array_splice($this->employees, $this->indexOfEmployee($aEmployee), 1);
      array_splice($this->employees, $index, 0, array($aEmployee));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addEmployeeAt($aEmployee, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfSchedules()
  {
    return 0;
  }

  public function addSchedule($aSchedule)
  {
    $wasAdded = false;
    if ($this->indexOfSchedule($aSchedule) !== -1) { return false; }
    $this->schedules[] = $aSchedule;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeSchedule($aSchedule)
  {
    $wasRemoved = false;
    if ($this->indexOfSchedule($aSchedule) != -1)
    {
      unset($this->schedules[$this->indexOfSchedule($aSchedule)]);
      $this->schedules = array_values($this->schedules);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addScheduleAt($aSchedule, $index)
  {  
    $wasAdded = false;
    if($this->addSchedule($aSchedule))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfSchedules()) { $index = $this->numberOfSchedules() - 1; }
      array_splice($this->schedules, $this->indexOfSchedule($aSchedule), 1);
      array_splice($this->schedules, $index, 0, array($aSchedule));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveScheduleAt($aSchedule, $index)
  {
    $wasAdded = false;
    if($this->indexOfSchedule($aSchedule) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfSchedules()) { $index = $this->numberOfSchedules() - 1; }
      array_splice($this->schedules, $this->indexOfSchedule($aSchedule), 1);
      array_splice($this->schedules, $index, 0, array($aSchedule));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addScheduleAt($aSchedule, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfScheduleRegistrations()
  {
    return 0;
  }

  public function addScheduleRegistration($aScheduleRegistration)
  {
    $wasAdded = false;
    if ($this->indexOfScheduleRegistration($aScheduleRegistration) !== -1) { return false; }
    $this->scheduleRegistrations[] = $aScheduleRegistration;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeScheduleRegistration($aScheduleRegistration)
  {
    $wasRemoved = false;
    if ($this->indexOfScheduleRegistration($aScheduleRegistration) != -1)
    {
      unset($this->scheduleRegistrations[$this->indexOfScheduleRegistration($aScheduleRegistration)]);
      $this->scheduleRegistrations = array_values($this->scheduleRegistrations);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addScheduleRegistrationAt($aScheduleRegistration, $index)
  {  
    $wasAdded = false;
    if($this->addScheduleRegistration($aScheduleRegistration))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfScheduleRegistrations()) { $index = $this->numberOfScheduleRegistrations() - 1; }
      array_splice($this->scheduleRegistrations, $this->indexOfScheduleRegistration($aScheduleRegistration), 1);
      array_splice($this->scheduleRegistrations, $index, 0, array($aScheduleRegistration));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveScheduleRegistrationAt($aScheduleRegistration, $index)
  {
    $wasAdded = false;
    if($this->indexOfScheduleRegistration($aScheduleRegistration) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfScheduleRegistrations()) { $index = $this->numberOfScheduleRegistrations() - 1; }
      array_splice($this->scheduleRegistrations, $this->indexOfScheduleRegistration($aScheduleRegistration), 1);
      array_splice($this->scheduleRegistrations, $index, 0, array($aScheduleRegistration));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addScheduleRegistrationAt($aScheduleRegistration, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->employees = array();
    $this->schedules = array();
    $this->scheduleRegistrations = array();
  }

}
?>
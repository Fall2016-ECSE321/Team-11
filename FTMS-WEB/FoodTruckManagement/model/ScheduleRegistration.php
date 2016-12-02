<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

class ScheduleRegistration
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static $nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Autounique Attributes
  private $id;

  //ScheduleRegistration Associations
  private $employee;
  private $schedule;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aEmployee, $aSchedule)
  {
    $this->id = self::$nextId++;
    if (!$this->setEmployee($aEmployee))
    {
      throw new Exception("Unable to create ScheduleRegistration due to aEmployee");
    }
    if (!$this->setSchedule($aSchedule))
    {
      throw new Exception("Unable to create ScheduleRegistration due to aSchedule");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getId()
  {
    return $this->id;
  }

  public function getEmployee()
  {
    return $this->employee;
  }

  public function getSchedule()
  {
    return $this->schedule;
  }

  public function setEmployee($aNewEmployee)
  {
    $wasSet = false;
    if ($aNewEmployee != null)
    {
      $this->employee = $aNewEmployee;
      $wasSet = true;
    }
    return $wasSet;
  }

  public function setSchedule($aNewSchedule)
  {
    $wasSet = false;
    if ($aNewSchedule != null)
    {
      $this->schedule = $aNewSchedule;
      $wasSet = true;
    }
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->employee = null;
    $this->schedule = null;
  }

}
?>
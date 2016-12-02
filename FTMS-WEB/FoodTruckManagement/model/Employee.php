<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

class Employee
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Employee Attributes
  private $staffName;
  private $staffRoles;
  private $hours;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aStaffName, $aStaffRoles, $aHours)
  {
    $this->staffName = $aStaffName;
    $this->staffRoles = $aStaffRoles;
    $this->hours = $aHours;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setStaffName($aStaffName)
  {
    $wasSet = false;
    $this->staffName = $aStaffName;
    $wasSet = true;
    return $wasSet;
  }

  public function setStaffRoles($aStaffRoles)
  {
    $wasSet = false;
    $this->staffRoles = $aStaffRoles;
    $wasSet = true;
    return $wasSet;
  }

  public function setHours($aHours)
  {
    $wasSet = false;
    $this->hours = $aHours;
    $wasSet = true;
    return $wasSet;
  }

  public function getStaffName()
  {
    return $this->staffName;
  }

  public function getStaffRoles()
  {
    return $this->staffRoles;
  }

  public function getHours()
  {
    return $this->hours;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {}

}
?>
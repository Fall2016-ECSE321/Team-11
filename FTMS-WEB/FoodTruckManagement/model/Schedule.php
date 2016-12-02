<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

class Schedule
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Schedule Attributes
  private $weekday;
  private $date;
  private $startTime;
  private $endTime;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aWeekday, $aDate, $aStartTime, $aEndTime)
  {
    $this->weekday = $aWeekday;
    $this->date = $aDate;
    $this->startTime = $aStartTime;
    $this->endTime = $aEndTime;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setWeekday($aWeekday)
  {
    $wasSet = false;
    $this->weekday = $aWeekday;
    $wasSet = true;
    return $wasSet;
  }

  public function setDate($aDate)
  {
    $wasSet = false;
    $this->date = $aDate;
    $wasSet = true;
    return $wasSet;
  }

  public function setStartTime($aStartTime)
  {
    $wasSet = false;
    $this->startTime = $aStartTime;
    $wasSet = true;
    return $wasSet;
  }

  public function setEndTime($aEndTime)
  {
    $wasSet = false;
    $this->endTime = $aEndTime;
    $wasSet = true;
    return $wasSet;
  }

  public function getWeekday()
  {
    return $this->weekday;
  }

  public function getDate()
  {
    return $this->date;
  }

  public function getStartTime()
  {
    return $this->startTime;
  }

  public function getEndTime()
  {
    return $this->endTime;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {}

}
?>
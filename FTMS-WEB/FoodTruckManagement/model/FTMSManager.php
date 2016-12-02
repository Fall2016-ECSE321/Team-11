<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

class FTMSManager
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FTMSManager Associations
  private $staff;
  private $order;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aStaff, $aOrder)
  {
    if (!$this->setStaff($aStaff))
    {
      throw new Exception("Unable to create FTMSManager due to aStaff");
    }
    if (!$this->setOrder($aOrder))
    {
      throw new Exception("Unable to create FTMSManager due to aOrder");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getStaff()
  {
    return $this->staff;
  }

  public function getOrder()
  {
    return $this->order;
  }

  public function setStaff($aNewStaff)
  {
    $wasSet = false;
    if ($aNewStaff != null)
    {
      $this->staff = $aNewStaff;
      $wasSet = true;
    }
    return $wasSet;
  }

  public function setOrder($aNewOrder)
  {
    $wasSet = false;
    if ($aNewOrder != null)
    {
      $this->order = $aNewOrder;
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
    $this->staff = null;
    $this->order = null;
  }

}
?>
<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

class Equipment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Equipment Attributes
  private $equipmentName;
  private $equipmentQty;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aEquipmentName, $aEquipmentQty)
  {
    $this->equipmentName = $aEquipmentName;
    $this->equipmentQty = $aEquipmentQty;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setEquipmentName($aEquipmentName)
  {
    $wasSet = false;
    $this->equipmentName = $aEquipmentName;
    $wasSet = true;
    return $wasSet;
  }

  public function setEquipmentQty($aEquipmentQty)
  {
    $wasSet = false;
    $this->equipmentQty = $aEquipmentQty;
    $wasSet = true;
    return $wasSet;
  }

  public function getEquipmentName()
  {
    return $this->equipmentName;
  }

  public function getEquipmentQty()
  {
    return $this->equipmentQty;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {}

}
?>
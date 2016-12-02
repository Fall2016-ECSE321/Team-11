<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

class OrderTracker
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OrderTracker Attributes
  private $saleName;
  private $saleQty;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aSaleName, $aSaleQty)
  {
    $this->saleName = $aSaleName;
    $this->saleQty = $aSaleQty;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setSaleName($aSaleName)
  {
    $wasSet = false;
    $this->saleName = $aSaleName;
    $wasSet = true;
    return $wasSet;
  }

  public function setSaleQty($aSaleQty)
  {
    $wasSet = false;
    $this->saleQty = $aSaleQty;
    $wasSet = true;
    return $wasSet;
  }

  public function getSaleName()
  {
    return $this->saleName;
  }

  public function getSaleQty()
  {
    return $this->saleQty;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {}

}
?>
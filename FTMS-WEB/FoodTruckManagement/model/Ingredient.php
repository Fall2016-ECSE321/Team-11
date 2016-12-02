<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

class Ingredient
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ingredient Attributes
  private $ingredientName;
  private $ingredientQty;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aIngredientName, $aIngredientQty)
  {
    $this->ingredientName = $aIngredientName;
    $this->ingredientQty = $aIngredientQty;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setIngredientName($aIngredientName)
  {
    $wasSet = false;
    $this->ingredientName = $aIngredientName;
    $wasSet = true;
    return $wasSet;
  }

  public function setIngredientQty($aIngredientQty)
  {
    $wasSet = false;
    $this->ingredientQty = $aIngredientQty;
    $wasSet = true;
    return $wasSet;
  }

  public function getIngredientName()
  {
    return $this->ingredientName;
  }

  public function getIngredientQty()
  {
    return $this->ingredientQty;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {}

}
?>
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.FTMS.model;

// line 72 "../../../../../FTMS.ump"
// line 150 "../../../../../FTMS.ump"
public class Ingredient
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ingredient Attributes
  private String ingredientName;
  private String ingredientQty;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ingredient(String aIngredientName, String aIngredientQty)
  {
    ingredientName = aIngredientName;
    ingredientQty = aIngredientQty;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void increaseIngredient(int aIngredientQty) {
	  this.ingredientQty += aIngredientQty;
  }
  
  public boolean setIngredientName(String aIngredientName)
  {
    boolean wasSet = false;
    ingredientName = aIngredientName;
    wasSet = true;
    return wasSet;
  }

  public boolean setIngredientQty(String aIngredientQty)
  {
    boolean wasSet = false;
    ingredientQty = aIngredientQty;
    wasSet = true;
    return wasSet;
  }

  public String getIngredientName()
  {
    return ingredientName;
  }

  public String getIngredientQty()
  {
    return ingredientQty;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "ingredientName" + ":" + getIngredientName()+ "," +
            "ingredientQty" + ":" + getIngredientQty()+ "]"
     + outputString;
  }
}
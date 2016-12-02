/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.FTMS.model;

// line 58 "../../../../../FTMS.ump"
// line 140 "../../../../../FTMS.ump"
public class Supply
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Supply Attributes
  private String foodName;
  private int foodQty;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Supply(String aFoodName, int aFoodQty)
  {
    foodName = aFoodName;
    foodQty = aFoodQty;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void increaseIngredient(int aFoodQty) {
	  this.foodQty += aFoodQty;
  }
  
  public boolean setFoodName(String aFoodName)
  {
    boolean wasSet = false;
    foodName = aFoodName;
    wasSet = true;
    return wasSet;
  }

  public boolean setFoodQty(int aFoodQty)
  {
    boolean wasSet = false;
    foodQty = aFoodQty;
    wasSet = true;
    return wasSet;
  }

  public String getFoodName()
  {
    return foodName;
  }

  public int getFoodQty()
  {
    return foodQty;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "foodName" + ":" + getFoodName()+ "," +
            "foodQty" + ":" + getFoodQty()+ "]"
     + outputString;
  }
}
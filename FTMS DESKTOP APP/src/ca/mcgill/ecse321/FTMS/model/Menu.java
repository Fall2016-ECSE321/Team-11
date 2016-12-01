/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.FTMS.model;

// line 122 "../../../../../FTMS.ump"
// line 167 "../../../../../FTMS.ump"
public class Menu
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Menu Attributes
  private String mealName;
  private String ingredientName;
  private int ingredientQty;
  private double price;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Menu(String aMealName, String aIngredientName, int aIngredientQty, double aPrice)
  {
    mealName = aMealName;
    ingredientName = aIngredientName;
    ingredientQty = aIngredientQty;
    price = aPrice;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMealName(String aMealName)
  {
    boolean wasSet = false;
    mealName = aMealName;
    wasSet = true;
    return wasSet;
  }

  public boolean setIngredientName(String aIngredientName)
  {
    boolean wasSet = false;
    ingredientName = aIngredientName;
    wasSet = true;
    return wasSet;
  }

  public boolean setIngredientQty(int aIngredientQty)
  {
    boolean wasSet = false;
    ingredientQty = aIngredientQty;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(double aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public String getMealName()
  {
    return mealName;
  }

  public String getIngredientName()
  {
    return ingredientName;
  }

  public int getIngredientQty()
  {
    return ingredientQty;
  }

  public double getPrice()
  {
    return price;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "mealName" + ":" + getMealName()+ "," +
            "ingredientName" + ":" + getIngredientName()+ "," +
            "ingredientQty" + ":" + getIngredientQty()+ "," +
            "price" + ":" + getPrice()+ "]"
     + outputString;
  }
}
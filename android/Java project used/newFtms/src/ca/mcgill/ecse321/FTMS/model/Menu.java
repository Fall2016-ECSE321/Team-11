/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.FTMS.model;

import java.util.List;

// line 64 "../../../../../FTMS.ump"
// line 87 "../../../../../FTMS.ump"
public class Menu
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Menu Attributes
  private String mealName;
  private List<Supply> ingredients;
  private double price;
  private int popularity;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Menu(String aMealName, List<Supply> ingredients, double price, int popularity)
  {
    this.mealName = aMealName;
    this.ingredients = ingredients;
    this.price = price;
    this.popularity = popularity;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public int getMealsLeft() {
	  int min = ingredients.get(0).getFoodQty();
	  for (int i=0; i<ingredients.size(); i++) {
		  if (ingredients.get(i).getFoodQty() < min)
			  min = ingredients.get(i).getFoodQty();
	  }
	  return min;
  }
  
  public String[] getIngredientNames() {
	  String[] s = new String[ingredients.size()];
	  for (int i=0; i<ingredients.size(); i++)
		  s[i] = ingredients.get(i).getFoodName();
	  return s;
  }
  
  public boolean setMealName(String aMealName)
  {
    boolean wasSet = false;
    this.mealName = aMealName;
    wasSet = true;
    return wasSet;
  }

  public boolean setIngredients(List<Supply> ingredients)
  {
    boolean wasSet = false;
    this.ingredients = ingredients;
    wasSet = true;
    return wasSet;
  }
  
  public boolean setPrice(double price) {
	  boolean wasSet = false;
	  this.price = price;
	  wasSet = true;
	  return wasSet;
  }
  
  public boolean setPopularity(int popularity) {
	  boolean wasSet = false;
	  this.popularity = popularity;
	  wasSet = true;
	  return wasSet;
  }

  public String getMealName()
  {
    return mealName;
  }

  public List<Supply> getIngredients()
  {
    return this.ingredients;
  }
  
  public double getPrice() {
	  return this.price;
  }
  
  public int getPopularity() {
	  return this.popularity;
  }

  public void delete()
  {}


  /*public String toString() TODO: IS THIS NEEDED?
  {
	  String outputString = "";
    return super.toString() + "["+
            "mealName" + ":" + getMealName()+ "," +
             "]" + System.getProperties().getProperty("line.separator") +
            "  " + "ingredients" + "=" + (getIngredientName() != null ? !getIngredientName().equals(this)  ? getIngredientName().toString().replaceAll("  ","    ") : "this" : "null")
     + outputString;
  }*/
}
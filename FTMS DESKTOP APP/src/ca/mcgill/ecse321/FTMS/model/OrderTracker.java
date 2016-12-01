/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.FTMS.model;

// line 141 "../../../../../FTMS.ump"
// line 223 "../../../../../FTMS.ump"
public class OrderTracker
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OrderTracker Attributes
  private String saleName;
  private int saleQty;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public OrderTracker(String aSaleName, int aSaleQty)
  {
    saleName = aSaleName;
    saleQty = aSaleQty;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSaleName(String aSaleName)
  {
    boolean wasSet = false;
    saleName = aSaleName;
    wasSet = true;
    return wasSet;
  }

  public boolean setSaleQty(int aSaleQty)
  {
    boolean wasSet = false;
    saleQty = aSaleQty;
    wasSet = true;
    return wasSet;
  }

  public String getSaleName()
  {
    return saleName;
  }

  public int getSaleQty()
  {
    return saleQty;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "saleName" + ":" + getSaleName()+ "," +
            "saleQty" + ":" + getSaleQty()+ "]"
     + outputString;
  }
}
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.FTMS.model;

// line 100 "../../../../../FTMS.ump"
// line 157 "../../../../../FTMS.ump"
public class Equipment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Equipment Attributes
  private String equipmentName;
  private int equipmentQty;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Equipment(String aEquipmentName, int aEquipmentQty)
  {
    equipmentName = aEquipmentName;
    equipmentQty = aEquipmentQty;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEquipmentName(String aEquipmentName)
  {
    boolean wasSet = false;
    equipmentName = aEquipmentName;
    wasSet = true;
    return wasSet;
  }

  public boolean setEquipmentQty(int aEquipmentQty)
  {
    boolean wasSet = false;
    equipmentQty = aEquipmentQty;
    wasSet = true;
    return wasSet;
  }

  public String getEquipmentName()
  {
    return equipmentName;
  }

  public int getEquipmentQty()
  {
    return equipmentQty;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "equipmentName" + ":" + getEquipmentName()+ "," +
            "equipmentQty" + ":" + getEquipmentQty()+ "]"
     + outputString;
  }
}
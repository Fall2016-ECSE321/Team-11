/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.FTMS.model;

// line 34 "../../../../../FTMS.ump"
// line 251 "../../../../../FTMS.ump"
public class Employee
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Employee Attributes
  private String staffName;
  private String staffRoles;
  private int hours;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Employee(String aStaffName, String aStaffRoles, int aHours)
  {
    staffName = aStaffName;
    staffRoles = aStaffRoles;
    hours = aHours;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStaffName(String aStaffName)
  {
    boolean wasSet = false;
    staffName = aStaffName;
    wasSet = true;
    return wasSet;
  }

  public boolean setStaffRoles(String aStaffRoles)
  {
    boolean wasSet = false;
    staffRoles = aStaffRoles;
    wasSet = true;
    return wasSet;
  }

  public boolean setHours(int aHours)
  {
    boolean wasSet = false;
    hours = aHours;
    wasSet = true;
    return wasSet;
  }

  public String getStaffName()
  {
    return staffName;
  }

  public String getStaffRoles()
  {
    return staffRoles;
  }

  public int getHours()
  {
    return hours;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "staffName" + ":" + getStaffName()+ "," +
            "staffRoles" + ":" + getStaffRoles()+ "," +
            "hours" + ":" + getHours()+ "]"
     + outputString;
  }
}
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.FTMS.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

// line 19 "../../../../../FTMS.ump"
// line 130 "../../../../../FTMS.ump"
public class Employee
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Employee Attributes
  private String staffName;
  private String staffRoles;
  private final List<DayOfTheWeek> schedule; // schedule.get(0) is monday and .get(6) is sunday
  

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Employee(String aStaffName, String aStaffRoles)
  {
    staffName = aStaffName;
    staffRoles = aStaffRoles;
    schedule = new ArrayList<DayOfTheWeek>();
    
    // Initiating schedule.
    Calendar c = Calendar.getInstance(TimeZone.getTimeZone("EST"));
    c.set(Calendar.HOUR_OF_DAY, 8);
    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND, 0);
    DayOfTheWeek monday = new DayOfTheWeek("Monday", new Time(c.getTimeInMillis()), new Time(c.getTimeInMillis())); // Default starts and ends at 8 am
    schedule.add(monday);
    DayOfTheWeek tuesday = new DayOfTheWeek("Tuesday", new Time(c.getTimeInMillis()), new Time(c.getTimeInMillis())); // Default starts and ends at 8 am
    schedule.add(tuesday);
    DayOfTheWeek wednesday = new DayOfTheWeek("Wednesday", new Time(c.getTimeInMillis()), new Time(c.getTimeInMillis())); // Default starts and ends at 8 am
    schedule.add(wednesday);
    DayOfTheWeek thursday = new DayOfTheWeek("Thursday", new Time(c.getTimeInMillis()), new Time(c.getTimeInMillis())); // Default starts and ends at 8 am
    schedule.add(thursday);
    DayOfTheWeek friday = new DayOfTheWeek("Friday", new Time(c.getTimeInMillis()), new Time(c.getTimeInMillis())); // Default starts and ends at 8 am
    schedule.add(friday);
    DayOfTheWeek saturday = new DayOfTheWeek("Saturday", new Time(c.getTimeInMillis()), new Time(c.getTimeInMillis())); // Default starts and ends at 8 am
    schedule.add(saturday);
    DayOfTheWeek sunday = new DayOfTheWeek("Sunday", new Time(c.getTimeInMillis()), new Time(c.getTimeInMillis())); // Default starts and ends at 8 am
    schedule.add(sunday);
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

  public void setDayTimings(int day, Time start, Time end)
  {
    this.schedule.get(day).setStartTime(start);
    this.schedule.get(day).setEndTime(end);
  }
  
  public Time getDayStartTime(int day) {
	  return this.schedule.get(day).getStartTime();
  }
  
  public Time getDayEndTime(int day) {
	  return this.schedule.get(day).getEndTime();
  }

  public String getStaffName()
  {
    return staffName;
  }

  public String getStaffRoles()
  {
    return staffRoles;
  }

  /*public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "staffName" + ":" + getStaffName()+ "," +
            "staffRoles" + ":" + getStaffRoles()+ "," +
            "hours" + ":" + getHours()+ "]"
     + outputString;
  }*/
}
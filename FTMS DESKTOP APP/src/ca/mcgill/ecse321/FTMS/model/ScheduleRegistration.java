/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.FTMS.model;

// line 66 "../../../../../FTMS.ump"
// line 283 "../../../../../FTMS.ump"
public class ScheduleRegistration
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Autounique Attributes
  private int id;

  //ScheduleRegistration Associations
  private Employee employee;
  private Schedule schedule;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ScheduleRegistration(Employee aEmployee, Schedule aSchedule)
  {
    id = nextId++;
    if (!setEmployee(aEmployee))
    {
      throw new RuntimeException("Unable to create ScheduleRegistration due to aEmployee");
    }
    if (!setSchedule(aSchedule))
    {
      throw new RuntimeException("Unable to create ScheduleRegistration due to aSchedule");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public int getId()
  {
    return id;
  }

  public Employee getEmployee()
  {
    return employee;
  }

  public Schedule getSchedule()
  {
    return schedule;
  }

  public boolean setEmployee(Employee aNewEmployee)
  {
    boolean wasSet = false;
    if (aNewEmployee != null)
    {
      employee = aNewEmployee;
      wasSet = true;
    }
    return wasSet;
  }

  public boolean setSchedule(Schedule aNewSchedule)
  {
    boolean wasSet = false;
    if (aNewSchedule != null)
    {
      schedule = aNewSchedule;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    employee = null;
    schedule = null;
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "employee = "+(getEmployee()!=null?Integer.toHexString(System.identityHashCode(getEmployee())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "schedule = "+(getSchedule()!=null?Integer.toHexString(System.identityHashCode(getSchedule())):"null")
     + outputString;
  }
}
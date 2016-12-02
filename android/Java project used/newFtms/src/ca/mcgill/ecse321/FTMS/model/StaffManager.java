/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.FTMS.model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;

// line 12 "../../../../../FTMS.ump"
// line 95 "../../../../../FTMS.ump"
public class StaffManager
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static StaffManager theInstance = new StaffManager();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //StaffManager Associations
  private List<Employee> employees;
  private List<Schedule> schedules;
  private List<ScheduleRegistration> scheduleRegistrations;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public StaffManager()
  {
    employees = new ArrayList<Employee>();
    schedules = new ArrayList<Schedule>();
    scheduleRegistrations = new ArrayList<ScheduleRegistration>();
  }

  public static StaffManager getInstance()
  {
    return theInstance;
  }
  
  public static void setInstance(StaffManager myInstance) { // to avoid having old saved data
	  theInstance = myInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Employee getEmployee(int index)
  {
    Employee aEmployee = employees.get(index);
    return aEmployee;
  }

  public List<Employee> getEmployees()
  {
    List<Employee> newEmployees = Collections.unmodifiableList(employees);
    return newEmployees;
  }

  public int numberOfEmployees()
  {
    int number = employees.size();
    return number;
  }

  public boolean hasEmployees()
  {
    boolean has = employees.size() > 0;
    return has;
  }

  public int indexOfEmployee(Employee aEmployee)
  {
    int index = employees.indexOf(aEmployee);
    return index;
  }

  public Schedule getSchedule(int index)
  {
    Schedule aSchedule = schedules.get(index);
    return aSchedule;
  }

  public List<Schedule> getSchedules()
  {
    List<Schedule> newSchedules = Collections.unmodifiableList(schedules);
    return newSchedules;
  }

  public int numberOfSchedules()
  {
    int number = schedules.size();
    return number;
  }

  public boolean hasSchedules()
  {
    boolean has = schedules.size() > 0;
    return has;
  }

  public int indexOfSchedule(Schedule aSchedule)
  {
    int index = schedules.indexOf(aSchedule);
    return index;
  }

  public ScheduleRegistration getScheduleRegistration(int index)
  {
    ScheduleRegistration aScheduleRegistration = scheduleRegistrations.get(index);
    return aScheduleRegistration;
  }

  public List<ScheduleRegistration> getScheduleRegistrations()
  {
    List<ScheduleRegistration> newScheduleRegistrations = Collections.unmodifiableList(scheduleRegistrations);
    return newScheduleRegistrations;
  }

  public int numberOfScheduleRegistrations()
  {
    int number = scheduleRegistrations.size();
    return number;
  }

  public boolean hasScheduleRegistrations()
  {
    boolean has = scheduleRegistrations.size() > 0;
    return has;
  }

  public int indexOfScheduleRegistration(ScheduleRegistration aScheduleRegistration)
  {
    int index = scheduleRegistrations.indexOf(aScheduleRegistration);
    return index;
  }

  public static int minimumNumberOfEmployees()
  {
    return 0;
  }

  public boolean addEmployee(Employee aEmployee)
  {
    boolean wasAdded = false;
    if (employees.contains(aEmployee)) { return false; }
    employees.add(aEmployee);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEmployee(Employee aEmployee)
  {
    boolean wasRemoved = false;
    if (employees.contains(aEmployee))
    {
      employees.remove(aEmployee);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addEmployeeAt(Employee aEmployee, int index)
  {  
    boolean wasAdded = false;
    if(addEmployee(aEmployee))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmployees()) { index = numberOfEmployees() - 1; }
      employees.remove(aEmployee);
      employees.add(index, aEmployee);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEmployeeAt(Employee aEmployee, int index)
  {
    boolean wasAdded = false;
    if(employees.contains(aEmployee))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmployees()) { index = numberOfEmployees() - 1; }
      employees.remove(aEmployee);
      employees.add(index, aEmployee);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEmployeeAt(aEmployee, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfSchedules()
  {
    return 0;
  }

  public boolean addSchedule(Schedule aSchedule)
  {
    boolean wasAdded = false;
    if (schedules.contains(aSchedule)) { return false; }
    schedules.add(aSchedule);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSchedule(Schedule aSchedule)
  {
    boolean wasRemoved = false;
    if (schedules.contains(aSchedule))
    {
      schedules.remove(aSchedule);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addScheduleAt(Schedule aSchedule, int index)
  {  
    boolean wasAdded = false;
    if(addSchedule(aSchedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSchedules()) { index = numberOfSchedules() - 1; }
      schedules.remove(aSchedule);
      schedules.add(index, aSchedule);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveScheduleAt(Schedule aSchedule, int index)
  {
    boolean wasAdded = false;
    if(schedules.contains(aSchedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSchedules()) { index = numberOfSchedules() - 1; }
      schedules.remove(aSchedule);
      schedules.add(index, aSchedule);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addScheduleAt(aSchedule, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfScheduleRegistrations()
  {
    return 0;
  }

  public boolean addScheduleRegistration(ScheduleRegistration aScheduleRegistration)
  {
    boolean wasAdded = false;
    if (scheduleRegistrations.contains(aScheduleRegistration)) { return false; }
    scheduleRegistrations.add(aScheduleRegistration);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeScheduleRegistration(ScheduleRegistration aScheduleRegistration)
  {
    boolean wasRemoved = false;
    if (scheduleRegistrations.contains(aScheduleRegistration))
    {
      scheduleRegistrations.remove(aScheduleRegistration);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addScheduleRegistrationAt(ScheduleRegistration aScheduleRegistration, int index)
  {  
    boolean wasAdded = false;
    if(addScheduleRegistration(aScheduleRegistration))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfScheduleRegistrations()) { index = numberOfScheduleRegistrations() - 1; }
      scheduleRegistrations.remove(aScheduleRegistration);
      scheduleRegistrations.add(index, aScheduleRegistration);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveScheduleRegistrationAt(ScheduleRegistration aScheduleRegistration, int index)
  {
    boolean wasAdded = false;
    if(scheduleRegistrations.contains(aScheduleRegistration))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfScheduleRegistrations()) { index = numberOfScheduleRegistrations() - 1; }
      scheduleRegistrations.remove(aScheduleRegistration);
      scheduleRegistrations.add(index, aScheduleRegistration);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addScheduleRegistrationAt(aScheduleRegistration, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    employees.clear();
    schedules.clear();
    scheduleRegistrations.clear();
  }

}
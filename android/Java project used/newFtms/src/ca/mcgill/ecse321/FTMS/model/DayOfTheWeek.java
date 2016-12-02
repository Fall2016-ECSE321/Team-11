package ca.mcgill.ecse321.FTMS.model;

import java.sql.Time;

public class DayOfTheWeek
	{
	
	  //------------------------
	  // MEMBER VARIABLES
	  //------------------------
	
	  //DayOfTheWeek Attributes
	  public final String weekday;
	  private Time start;
	  private Time end;
	  
	
	  //------------------------
	  // CONSTRUCTOR
	  //------------------------
	
	  public DayOfTheWeek(String aWeekday, Time aStart, Time aEnd)
	  {
	    weekday = aWeekday;
	    start = aStart;
	    end = aEnd;
	  }
	
	  //------------------------
	  // INTERFACE
	  //------------------------
	
	  public void setStartTime(Time start)
	  {
	    this.start = start;
	  }
	  
	  public void setEndTime(Time end)
	  {
	    this.end = end;
	  }
	
	  public Time getStartTime()
	  {
	    return this.start;
	  }
	
	  public Time getEndTime()
	  {
	    return this.end;
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
package ca.mcgill.ecse321.FTMS.persistence;

import java.util.Iterator;

import ca.mcgill.ecse321.FTMS.model.Employee;
import ca.mcgill.ecse321.FTMS.model.Schedule;
import ca.mcgill.ecse321.FTMS.model.ScheduleRegistration;
import ca.mcgill.ecse321.FTMS.model.StaffManager;

/**
 * Persistence Class for FTMS Schedule
 * @author Group 11
 *
 */


public class PersistenceFTMSSchedule {	
	private static String filename = "Schedule";

	public static void setFilename(String fn) {
		filename = fn;
	}
	/**
	 * Method to load models included in the ScheduleFTMS XML file 
	 */
	private static void initializeXStream(){
		PersistenceXStreamSchedule.setFilename(filename +"FTMS.xml");
		PersistenceXStreamSchedule.setAlias("schedule", Schedule.class);
		PersistenceXStreamSchedule.setAlias("employee", Employee.class);
		PersistenceXStreamSchedule.setAlias("scheduleRegistration", ScheduleRegistration.class);
		PersistenceXStreamSchedule.setAlias("manager", StaffManager.class);
		
	}
	/**
	 * Method to load all Data into the OrderFTMS XML file
	 * @author Group 11
	 */
	
	public static void loadFTMSScheduleModel() {
		StaffManager sm = StaffManager.getInstance();
		PersistenceFTMSSchedule.initializeXStream();
		StaffManager sm2 = (StaffManager)PersistenceXStreamSchedule.loadFromXMLwithXStream();

		// Go through the list and add all data to the XML file 
		if(sm2 != null){
						Iterator<Employee> pIt = sm2.getEmployees().iterator();
			while(pIt.hasNext())
				sm.addEmployee(pIt.next());
			Iterator<Schedule> eIt = sm2.getSchedules().iterator();
			while(eIt.hasNext())
				sm.addSchedule(eIt.next());
			Iterator<ScheduleRegistration> rIt = sm2.getScheduleRegistrations().iterator();
			while(rIt.hasNext())
				sm.addScheduleRegistration(rIt.next());
		}
		
	}


}

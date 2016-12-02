package ca.mcgill.ecse321.FTMS.persistence;

import java.util.Iterator;

import ca.mcgill.ecse321.FTMS.model.Employee;
import ca.mcgill.ecse321.FTMS.model.Schedule;
import ca.mcgill.ecse321.FTMS.model.ScheduleRegistration;
import ca.mcgill.ecse321.FTMS.model.StaffManager;




public class PersistenceFTMSSchedule {	
	private static String filename = "Schedule";

	public static void setFilename(String fn) {
		filename = fn;
	}
	
	private static void initializeXStream(){
		PersistenceXStreamSchedule.setFilename(filename +"FTMS.xml");
		PersistenceXStreamSchedule.setAlias("schedule", Schedule.class);
		PersistenceXStreamSchedule.setAlias("employee", Employee.class);
		PersistenceXStreamSchedule.setAlias("scheduleRegistration", ScheduleRegistration.class);
		PersistenceXStreamSchedule.setAlias("manager", StaffManager.class);
		
	}
	
	public static void loadFTMSScheduleModel() {
		StaffManager sm = StaffManager.getInstance();
		PersistenceFTMSSchedule.initializeXStream();
		StaffManager sm2 = (StaffManager)PersistenceXStreamSchedule.loadFromXMLwithXStream();
		if(sm2 != null){
			// unfortunately, this creates a second RegistrationManager object, even though its singleton
			//copy loaded model into singleton instance of RegistrationManager, because this will be used throughout the application
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

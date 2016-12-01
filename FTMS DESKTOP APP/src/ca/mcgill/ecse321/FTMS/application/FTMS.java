package ca.mcgill.ecse321.FTMS.application;
/**
 *@FTMSMANAGER
 * @author Group 11
 * 
 */
import ca.mcgill.ecse321.FTMS.persistence.PersistenceFTMSOrder;
import ca.mcgill.ecse321.FTMS.persistence.PersistenceFTMSSchedule;
import ca.mcgill.ecse321.FTMS.view.FTMSPage;

/**
 * This class is the main class of the project.
 * The main method loads the model of each persistence and sets the view to the introductory page.
 * @author Group 11
 *
 */
public class FTMS {
		public static void main(String[] args) {
			//load model
			PersistenceFTMSSchedule.loadFTMSScheduleModel();
			PersistenceFTMSOrder.loadFTMSInventoryModel();
			
			// start UI
			java.awt.EventQueue.invokeLater(new Runnable(){
				public void run(){
					new FTMSPage().setVisible(true);
					
				}
			});

		}

	}

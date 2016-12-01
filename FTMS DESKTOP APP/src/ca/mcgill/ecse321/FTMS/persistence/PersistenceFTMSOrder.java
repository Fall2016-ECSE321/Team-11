package ca.mcgill.ecse321.FTMS.persistence;

import java.util.Iterator;

import ca.mcgill.ecse321.FTMS.model.Equipment;
import ca.mcgill.ecse321.FTMS.model.Menu;
import ca.mcgill.ecse321.FTMS.model.OrderManager;
import ca.mcgill.ecse321.FTMS.model.OrderTracker;
import ca.mcgill.ecse321.FTMS.model.Supply;
/**
 * Persistence Class for FTMS Order
 * @author Group 11
 *
 */


public class PersistenceFTMSOrder {	
	private static String filename = "Order";

	
	public static void setFilename(String fn) {
		filename = fn;
	}
	
	/**
	 * Method to load models included in the ScheduleFTMS XML file 
	 */
	private static void initializeXStream(){
		PersistenceXStreamOrder.setFilename(filename +"FTMS.xml");
		PersistenceXStreamOrder.setAlias("equipment", Equipment.class);
		PersistenceXStreamOrder.setAlias("supply", Supply.class);
		PersistenceXStreamOrder.setAlias("menu", Menu.class);
		PersistenceXStreamOrder.setAlias("tracker", OrderTracker.class);
		PersistenceXStreamOrder.setAlias("manager", OrderManager.class);
	}
	/**
	 * Method to load all Data into the OrderFTMS XML file
	 * @author Group 11
	 */
	public static void loadFTMSInventoryModel() {
		OrderManager om = OrderManager.getInstance();
		PersistenceFTMSOrder.initializeXStream();
		OrderManager om2 = (OrderManager)PersistenceXStreamOrder.loadFromXMLwithXStream();
		
		// Go through the list and add all data to the XML file 
		if(om2 != null){
			Iterator<Supply> fIt = om2.getFoodSupplies().iterator();
			while(fIt.hasNext())
				om.addFoodSupply(fIt.next());
			Iterator<Equipment> eIt = om2.getEquipments().iterator();
			while(eIt.hasNext())
				om.addEquipment(eIt.next());
			Iterator<Menu> mIt = om2.getMenus().iterator();
			while(mIt.hasNext())
				om.addMenus(mIt.next());
			Iterator<OrderTracker> tIt = om2.getTracker().iterator();
			while(tIt.hasNext())
				om.addTracker(tIt.next());
		}
		
		
	}

}

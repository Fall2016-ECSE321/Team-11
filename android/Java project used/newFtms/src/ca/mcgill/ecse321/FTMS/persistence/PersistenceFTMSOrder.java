package ca.mcgill.ecse321.FTMS.persistence;

import java.util.Iterator;

import ca.mcgill.ecse321.FTMS.model.Equipment;
import ca.mcgill.ecse321.FTMS.model.Menu;
import ca.mcgill.ecse321.FTMS.model.OrderManager;
import ca.mcgill.ecse321.FTMS.model.Supply;



public class PersistenceFTMSOrder {	
	private static String filename = "Order";

	public static void setFilename(String fn) {
		filename = fn;
	}
	
	private static void initializeXStream(){
		PersistenceXStreamOrder.setFilename(filename +"FTMS.xml");
		PersistenceXStreamOrder.setAlias("equipment", Equipment.class);
		PersistenceXStreamOrder.setAlias("supply", Supply.class);
		PersistenceXStreamOrder.setAlias("menu", Menu.class);
		PersistenceXStreamOrder.setAlias("manager", OrderManager.class);
		
	}

	public static void loadFTMSInventoryModel() {
		OrderManager om = OrderManager.getInstance();
		PersistenceFTMSOrder.initializeXStream();
		OrderManager om2 = (OrderManager)PersistenceXStreamOrder.loadFromXMLwithXStream();
		if(om2 != null){
			// unfortunately, this creates a second RegistrationManager object, even though its singleton
			//copy loaded model into singleton instance of RegistrationManager, because this will be used throughout the application
			Iterator<Supply> fIt = om2.getFoodSupplies().iterator();
			while(fIt.hasNext())
				om.addFoodSupply(fIt.next());
			Iterator<Equipment> eIt = om2.getEquipments().iterator();
			while(eIt.hasNext())
				om.addEquipment(eIt.next());
			Iterator<Menu> mIt = om2.getMenus().iterator();
			while(fIt.hasNext())
				om.addMenus(mIt.next());
			
			}
		
		
	}

}

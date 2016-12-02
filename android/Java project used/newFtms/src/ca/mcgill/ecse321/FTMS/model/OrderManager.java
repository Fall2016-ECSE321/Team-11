/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.FTMS.model;
import java.util.*;

// line 44 "../../../../../FTMS.ump"
// line 104 "../../../../../FTMS.ump"
public class OrderManager
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static OrderManager theInstance = new OrderManager();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OrderManager Associations
  private List<Equipment> equipments;
  private List<Supply> foodSupplies;
  private List<Menu> menus;
  private List<OrderTracker> tracker;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public OrderManager()
  {
    equipments = new ArrayList<Equipment>();
    foodSupplies = new ArrayList<Supply>();
    menus = new ArrayList<Menu>();
    tracker = new ArrayList<OrderTracker>();
  }

  public static OrderManager getInstance()
  {
    return theInstance;
  }
  
  public static void setInstance(OrderManager myInstance) { // to avoid having old saved data
	  theInstance = myInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Equipment getEquipment(int index)
  {
    Equipment aEquipment = equipments.get(index);
    return aEquipment;
  }

  public List<Equipment> getEquipments()
  {
    List<Equipment> newEquipments = Collections.unmodifiableList(equipments);
    return newEquipments;
  }
  
  public Equipment getEquipment(String equipment) {
	  Equipment myEquipment = null;
	  for (int i=0; i<this.equipments.size(); i++) {
		  if (equipments.get(i).getEquipmentName().toLowerCase().trim().equals(equipment.toLowerCase().trim()))
			  myEquipment = equipments.get(i);
	  }
	  return myEquipment;
  }

  public int numberOfEquipments()
  {
    int number = equipments.size();
    return number;
  }

  public boolean hasEquipments()
  {
    boolean has = equipments.size() > 0;
    return has;
  }

  public int indexOfEquipment(Equipment aEquipment)
  {
    int index = equipments.indexOf(aEquipment);
    return index;
  }

  public Supply getFoodSupply(int index)
  {
    Supply aFoodSupply = foodSupplies.get(index);
    return aFoodSupply;
  }

  public List<Supply> getFoodSupplies()
  {
    List<Supply> newFoodSupplies = Collections.unmodifiableList(foodSupplies);
    return newFoodSupplies;
  }
  
  public Supply getFoodSupply(String ingredient) {
	  Supply myIngredient = null;
	  for (int i=0; i<this.foodSupplies.size(); i++) {
		  if (foodSupplies.get(i).getFoodName().toLowerCase().trim().equals(ingredient.toLowerCase().trim()))
			  myIngredient = foodSupplies.get(i);
	  }
	  return myIngredient;
  }

  public int numberOfFoodSupplies()
  {
    int number = foodSupplies.size();
    return number;
  }

  public boolean hasFoodSupplies()
  {
    boolean has = foodSupplies.size() > 0;
    return has;
  }

  public int indexOfFoodSupply(Supply aFoodSupply)
  {
    int index = foodSupplies.indexOf(aFoodSupply);
    return index;
  }

  public Menu getMenu(int index)
  {
    Menu aMenus = menus.get(index);
    return aMenus;
  }

  public List<Menu> getMenus()
  {
    List<Menu> newMenus = Collections.unmodifiableList(menus);
    return newMenus;
  }
  
  public Menu getMenu(String menuItem) {
	  Menu myMenu = null;
	  for (int i=0; i<this.menus.size(); i++) {
		  if (menus.get(i).getMealName().toLowerCase().trim().equals(menuItem.toLowerCase().trim()))
			  myMenu = menus.get(i);
	  }
	  return myMenu;
  }

  public int numberOfMenus()
  {
    int number = menus.size();
    return number;
  }

  public boolean hasMenus()
  {
    boolean has = menus.size() > 0;
    return has;
  }

  public int indexOfMenus(Menu aMenus)
  {
    int index = menus.indexOf(aMenus);
    return index;
  }

  public OrderTracker getTracker(int index)
  {
    OrderTracker aTracker = tracker.get(index);
    return aTracker;
  }

  public List<OrderTracker> getTracker()
  {
    List<OrderTracker> newTracker = Collections.unmodifiableList(tracker);
    return newTracker;
  }

  public int numberOfTracker()
  {
    int number = tracker.size();
    return number;
  }

  public boolean hasTracker()
  {
    boolean has = tracker.size() > 0;
    return has;
  }

  public int indexOfTracker(OrderTracker aTracker)
  {
    int index = tracker.indexOf(aTracker);
    return index;
  }

  public static int minimumNumberOfEquipments()
  {
    return 0;
  }

  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    for(int i=0; i<equipments.size(); i++) {
    	if (equipments.get(i).getEquipmentName().toLowerCase().trim().equals(aEquipment.getEquipmentName().toLowerCase().trim())) {
    		equipments.get(i).increaseEquipment(aEquipment.getEquipmentQty());
    		wasAdded = true;
    	}
    }
    if (!wasAdded)
    	equipments.add(aEquipment);
    return wasAdded;
  }

  public boolean removeEquipment(Equipment aEquipment)
  {
    boolean wasRemoved = false;
    if (equipments.contains(aEquipment))
    {
      equipments.remove(aEquipment);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addEquipmentAt(Equipment aEquipment, int index)
  {  
    boolean wasAdded = false;
    if(addEquipment(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipments()) { index = numberOfEquipments() - 1; }
      equipments.remove(aEquipment);
      equipments.add(index, aEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentAt(Equipment aEquipment, int index)
  {
    boolean wasAdded = false;
    if(equipments.contains(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipments()) { index = numberOfEquipments() - 1; }
      equipments.remove(aEquipment);
      equipments.add(index, aEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentAt(aEquipment, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfFoodSupplies()
  {
    return 0;
  }

  public boolean addFoodSupply(Supply aFoodSupply)
  {
    boolean wasAdded = false;
    for(int i=0; i<foodSupplies.size(); i++) {
    	if (foodSupplies.get(i).getFoodName().toLowerCase().trim().equals(aFoodSupply.getFoodName().toLowerCase().trim())) {
    		foodSupplies.get(i).increaseIngredient(aFoodSupply.getFoodQty());
    		wasAdded = true;
    	}
    }
    if (!wasAdded)
    	foodSupplies.add(aFoodSupply);
    return wasAdded;
  }

  public boolean removeFoodSupply(Supply aFoodSupply)
  {
    boolean wasRemoved = false;
    if (foodSupplies.contains(aFoodSupply))
    {
      foodSupplies.remove(aFoodSupply);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addFoodSupplyAt(Supply aFoodSupply, int index)
  {  
    boolean wasAdded = false;
    if(addFoodSupply(aFoodSupply))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFoodSupplies()) { index = numberOfFoodSupplies() - 1; }
      foodSupplies.remove(aFoodSupply);
      foodSupplies.add(index, aFoodSupply);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveFoodSupplyAt(Supply aFoodSupply, int index)
  {
    boolean wasAdded = false;
    if(foodSupplies.contains(aFoodSupply))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFoodSupplies()) { index = numberOfFoodSupplies() - 1; }
      foodSupplies.remove(aFoodSupply);
      foodSupplies.add(index, aFoodSupply);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addFoodSupplyAt(aFoodSupply, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfMenus()
  {
    return 0;
  }

  public boolean addMenus(Menu aMenus)
  {
    boolean wasAdded = false;
    if (menus.contains(aMenus)) { return false; }
    menus.add(aMenus);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMenus(Menu aMenus)
  {
    boolean wasRemoved = false;
    if (menus.contains(aMenus))
    {
      menus.remove(aMenus);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addMenusAt(Menu aMenus, int index)
  {  
    boolean wasAdded = false;
    if(addMenus(aMenus))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMenus()) { index = numberOfMenus() - 1; }
      menus.remove(aMenus);
      menus.add(index, aMenus);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMenusAt(Menu aMenus, int index)
  {
    boolean wasAdded = false;
    if(menus.contains(aMenus))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMenus()) { index = numberOfMenus() - 1; }
      menus.remove(aMenus);
      menus.add(index, aMenus);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMenusAt(aMenus, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfTracker()
  {
    return 0;
  }

  public boolean addTracker(OrderTracker aTracker)
  {
    boolean wasAdded = false;
    if (tracker.contains(aTracker)) { return false; }
    tracker.add(aTracker);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTracker(OrderTracker aTracker)
  {
    boolean wasRemoved = false;
    if (tracker.contains(aTracker))
    {
      tracker.remove(aTracker);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addTrackerAt(OrderTracker aTracker, int index)
  {  
    boolean wasAdded = false;
    if(addTracker(aTracker))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTracker()) { index = numberOfTracker() - 1; }
      tracker.remove(aTracker);
      tracker.add(index, aTracker);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTrackerAt(OrderTracker aTracker, int index)
  {
    boolean wasAdded = false;
    if(tracker.contains(aTracker))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTracker()) { index = numberOfTracker() - 1; }
      tracker.remove(aTracker);
      tracker.add(index, aTracker);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTrackerAt(aTracker, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    equipments.clear();
    foodSupplies.clear();
    menus.clear();
    tracker.clear();
  }

}
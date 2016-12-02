/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.FTMS.model;

// line 3 "../../../../../FTMS.ump"
// line 122 "../../../../../FTMS.ump"
public class FTMSManager
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static FTMSManager theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FTMSManager Associations
  private StaffManager staff;
  private OrderManager order;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private FTMSManager()
  {}

  public static FTMSManager getInstance()
  {
    if(theInstance == null)
    {
      theInstance = new FTMSManager();
    }
    return theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public StaffManager getStaff()
  {
    return staff;
  }

  public boolean hasStaff()
  {
    boolean has = staff != null;
    return has;
  }

  public OrderManager getOrder()
  {
    return order;
  }

  public boolean hasOrder()
  {
    boolean has = order != null;
    return has;
  }

  public boolean setStaff(StaffManager aNewStaff)
  {
    boolean wasSet = false;
    staff = aNewStaff;
    wasSet = true;
    return wasSet;
  }

  public boolean setOrder(OrderManager aNewOrder)
  {
    boolean wasSet = false;
    order = aNewOrder;
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    staff = null;
    order = null;
  }

}
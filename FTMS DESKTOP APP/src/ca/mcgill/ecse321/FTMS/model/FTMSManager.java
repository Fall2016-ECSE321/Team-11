/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.FTMS.model;

// line 5 "../../../../../FTMS.ump"
// line 235 "../../../../../FTMS.ump"
public class FTMSManager
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FTMSManager Associations
  private StaffManager staff;
  private OrderManager order;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public FTMSManager(StaffManager aStaff, OrderManager aOrder)
  {
    if (!setStaff(aStaff))
    {
      throw new RuntimeException("Unable to create FTMSManager due to aStaff");
    }
    if (!setOrder(aOrder))
    {
      throw new RuntimeException("Unable to create FTMSManager due to aOrder");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public StaffManager getStaff()
  {
    return staff;
  }

  public OrderManager getOrder()
  {
    return order;
  }

  public boolean setStaff(StaffManager aNewStaff)
  {
    boolean wasSet = false;
    if (aNewStaff != null)
    {
      staff = aNewStaff;
      wasSet = true;
    }
    return wasSet;
  }

  public boolean setOrder(OrderManager aNewOrder)
  {
    boolean wasSet = false;
    if (aNewOrder != null)
    {
      order = aNewOrder;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    staff = null;
    order = null;
  }

}
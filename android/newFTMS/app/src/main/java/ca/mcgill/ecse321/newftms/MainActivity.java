package ca.mcgill.ecse321.newftms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import ca.mcgill.ecse321.FTMS.controller.FTMSController;
import ca.mcgill.ecse321.FTMS.model.OrderManager;
import ca.mcgill.ecse321.FTMS.model.StaffManager;
import ca.mcgill.ecse321.FTMS.model.Supply;
import ca.mcgill.ecse321.FTMS.persistence.PersistenceFTMSOrder;
import ca.mcgill.ecse321.FTMS.persistence.PersistenceXStreamOrder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //PersistenceFTMSOrder.setFilename(getFilesDir().getAbsolutePath() + "Order");
        //PersistenceFTMSOrder.loadFTMSInventoryModel();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initiating various elements of the food truck
        OrderManager om = new OrderManager(); // completely new: old data is gone
        OrderManager.setInstance(om); // to get rid of old saved data
        StaffManager sm = new StaffManager();
        StaffManager.setInstance(sm);
        FTMSController fc = new FTMSController();
        try { // initiating all items since persistence does not work
            // initiating supplies
            fc.createSupply("fries", 10);
            fc.createSupply("cheese", 5);
            fc.createSupply("meat", 5);
            fc.createSupply("hotdog", 5);
            fc.createSupply("bread", 10);

            // initiating dishes
            List<Supply> poutineIngredients = new ArrayList<Supply>(Arrays.asList(om.getFoodSupply("fries"), om.getFoodSupply("cheese")));
            fc.createMenu("poutine", poutineIngredients, 3.5);

            List<Supply> friesIngredients = new ArrayList<Supply>(Arrays.asList(om.getFoodSupply("fries")));
            fc.createMenu("fries", friesIngredients, 2);

            List<Supply> hotdogIngredients = new ArrayList<Supply>(Arrays.asList(om.getFoodSupply("hotdog"), om.getFoodSupply("bread")));
            fc.createMenu("hotdog", hotdogIngredients, 2.5);

            List<Supply> meatSandwichIngredients = new ArrayList<Supply>(Arrays.asList(om.getFoodSupply("meat"), om.getFoodSupply("bread")));
            fc.createMenu("meat sandwich", meatSandwichIngredients, 4);

            fc.createEquipment("grill", 1);
            fc.createEquipment("deep fryer", 2);
            fc.createEquipment("knives", 10);
            fc.createEquipment("napkins", 50);

            // initiating employees and schedules
            Calendar c = Calendar.getInstance();
            Time start;
            Time end;
            fc.createEmployee("Mark", "Cashier");
            fc.createEmployee("Carl", "Chef");

            c.setTimeInMillis(sm.getEmployee(0).getDayStartTime(0).getTime());
            start = new Time(c.getTimeInMillis()); // 8am
            c.set(Calendar.HOUR_OF_DAY, 14);
            end = new Time(c.getTimeInMillis()); // 2pm
            sm.getEmployee(0).setDayTimings(0, start, end);

            c.setTimeInMillis(sm.getEmployee(0).getDayStartTime(0).getTime());
            start = new Time(c.getTimeInMillis());
            c.set(Calendar.HOUR_OF_DAY, 14);
            end = new Time(c.getTimeInMillis());
            sm.getEmployee(1).setDayTimings(0, start, end);

            c.setTimeInMillis(sm.getEmployee(0).getDayStartTime(0).getTime());
            start = new Time(c.getTimeInMillis());
            c.set(Calendar.HOUR_OF_DAY, 12);
            end = new Time(c.getTimeInMillis());
            sm.getEmployee(0).setDayTimings(1, start, end);

            c.setTimeInMillis(sm.getEmployee(0).getDayStartTime(0).getTime());
            start = new Time(c.getTimeInMillis());
            c.set(Calendar.HOUR_OF_DAY, 15);
            end = new Time(c.getTimeInMillis());
            sm.getEmployee(1).setDayTimings(1, start, end);

            c.setTimeInMillis(sm.getEmployee(0).getDayStartTime(0).getTime());
            start = new Time(c.getTimeInMillis());
            c.set(Calendar.HOUR_OF_DAY, 11);
            end = new Time(c.getTimeInMillis());
            sm.getEmployee(0).setDayTimings(2, start, end);

            c.setTimeInMillis(sm.getEmployee(0).getDayStartTime(0).getTime());
            start = new Time(c.getTimeInMillis());
            c.set(Calendar.HOUR_OF_DAY, 18);
            end = new Time(c.getTimeInMillis());
            sm.getEmployee(1).setDayTimings(2, start, end);

            c.setTimeInMillis(sm.getEmployee(0).getDayStartTime(0).getTime());
            start = new Time(c.getTimeInMillis());
            c.set(Calendar.HOUR_OF_DAY, 15);
            end = new Time(c.getTimeInMillis());
            sm.getEmployee(0).setDayTimings(3, start, end);

            c.setTimeInMillis(sm.getEmployee(0).getDayStartTime(0).getTime());
            start = new Time(c.getTimeInMillis());
            c.set(Calendar.HOUR_OF_DAY, 10);
            end = new Time(c.getTimeInMillis());
            sm.getEmployee(1).setDayTimings(3, start, end);

            c.setTimeInMillis(sm.getEmployee(0).getDayStartTime(0).getTime());
            start = new Time(c.getTimeInMillis());
            c.set(Calendar.HOUR_OF_DAY, 13);
            end = new Time(c.getTimeInMillis());
            sm.getEmployee(0).setDayTimings(4, start, end);

            c.setTimeInMillis(sm.getEmployee(0).getDayStartTime(0).getTime());
            start = new Time(c.getTimeInMillis());
            c.set(Calendar.HOUR_OF_DAY, 12);
            end = new Time(c.getTimeInMillis());
            sm.getEmployee(1).setDayTimings(4, start, end);

            c.setTimeInMillis(sm.getEmployee(0).getDayStartTime(0).getTime());
            start = new Time(c.getTimeInMillis());
            c.set(Calendar.HOUR_OF_DAY, 14);
            end = new Time(c.getTimeInMillis());
            sm.getEmployee(0).setDayTimings(5, start, end);

            c.setTimeInMillis(sm.getEmployee(0).getDayStartTime(0).getTime());
            start = new Time(c.getTimeInMillis());
            c.set(Calendar.HOUR_OF_DAY, 15);
            end = new Time(c.getTimeInMillis());
            sm.getEmployee(1).setDayTimings(5, start, end);

            c.setTimeInMillis(sm.getEmployee(0).getDayStartTime(0).getTime());
            start = new Time(c.getTimeInMillis());
            c.set(Calendar.HOUR_OF_DAY, 11);
            end = new Time(c.getTimeInMillis());
            sm.getEmployee(0).setDayTimings(6, start, end);

            c.setTimeInMillis(sm.getEmployee(0).getDayStartTime(0).getTime());
            start = new Time(c.getTimeInMillis());
            c.set(Calendar.HOUR_OF_DAY, 14);
            end = new Time(c.getTimeInMillis());
            sm.getEmployee(1).setDayTimings(6, start, end);
        } catch (Exception e) {}

        ImageButton to_addMenu = (ImageButton) findViewById(R.id.addMenuButton);

        to_addMenu.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, addMenu.class));
            }
        });

        ImageButton to_inventoryMenu = (ImageButton) findViewById(R.id.inventoryMenuButton);

        to_inventoryMenu.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, inventoryMenu.class));
            }
        });

        ImageButton to_scheduleMenu = (ImageButton) findViewById(R.id.scheduleMenuButton);

        to_scheduleMenu.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, scheduleMenu.class));
            }
        });
    }
}

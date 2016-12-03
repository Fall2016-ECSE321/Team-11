package ca.mcgill.ecse321.newftms;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import ca.mcgill.ecse321.FTMS.controller.FTMSController;
import ca.mcgill.ecse321.FTMS.model.Equipment;
import ca.mcgill.ecse321.FTMS.model.Menu;
import ca.mcgill.ecse321.FTMS.model.OrderManager;
import ca.mcgill.ecse321.FTMS.model.Supply;

/**
 * This activity gets created when the user clicks on the button Inventory Menu in the MainActivity page. You can view and modify the quantities of all inventory items you have and
 * you can order a meal.
 */
public class InventoryMenu extends AppCompatActivity {

    private FTMSController fc = new FTMSController();
    private String equipmentSelected = null;
    private String dishSelected = null;
    private String supplySelected = null;
    private int dishPosition = 0;
    private int equipmentPosition = 0;
    private int supplyPosition = 0;

    /**
     * Creates the equipment, dishes and supplies spinners.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_menu);

        OrderManager om = OrderManager.getInstance();

        // Initiate equipment spinner
        TextView t_e = (TextView) findViewById(R.id.equipment_inventory_label);
        t_e.setTextColor(Color.rgb(0, 0, 200));
        Spinner equipment = (Spinner)findViewById(R.id.equipment_spinner);
        int numberOfE = om.getEquipments().size();
        final String[] itemsE = new String[numberOfE];
        final String[] displayedE = new String[numberOfE];
        for (int i=0; i<numberOfE; i++) {
            itemsE[i] = om.getEquipment(i).getEquipmentName();
            displayedE[i] = om.getEquipment(i).getEquipmentName() + "   (" + om.getEquipment(i).getEquipmentQty() + " left)";
        }
        ArrayAdapter<String> adapterE = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, displayedE);
        equipment.setAdapter(adapterE);
        equipment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                equipmentSelected = itemsE[position];
                equipmentPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // avoids error
            }
        });

        // Initiate dishes spinner
        TextView t_d = (TextView) findViewById(R.id.dishes_inventory_label);
        t_d.setTextColor(Color.rgb(0, 0, 200));
        Spinner dishes = (Spinner)findViewById(R.id.dishes_spinner);
        int numberOfD = om.getMenus().size();
        final String[] itemsD = new String[numberOfD];
        final String[] displayedD = new String[numberOfD];
        for (int i=0; i<numberOfD; i++) {
            itemsD[i] = om.getMenu(i).getMealName();
            displayedD[i] = om.getMenu(i).getMealName() + "   (popularity " + om.getMenu(i).getPopularity() + ")";
        }
        ArrayAdapter<String> adapterD = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, displayedD);
        dishes.setAdapter(adapterD);
        dishes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                dishSelected = itemsD[position];
                dishPosition = position;
                updateDishInfo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // avoids error
            }
        });

        // Initiate supplies spinner
        TextView t_s = (TextView) findViewById(R.id.supplies_inventory_label);
        t_s.setTextColor(Color.rgb(0, 0, 200));
        Spinner supplies = (Spinner)findViewById(R.id.supplies_spinner);
        int numberOfS = om.getFoodSupplies().size();
        final String[] itemsS = new String[numberOfS];
        final String[] displayedS = new String[numberOfS];
        for (int i=0; i<numberOfS; i++) {
            itemsS[i] = om.getFoodSupply(i).getFoodName();
            displayedS[i] = om.getFoodSupply(i).getFoodName() + "   (" + om.getFoodSupply(i).getFoodQty() + " left)";
        }
        ArrayAdapter<String> adapterS = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, displayedS);
        supplies.setAdapter(adapterS);
        supplies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                supplySelected = itemsS[position];
                supplyPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // avoids error
            }
        });
    }

    /**
     * Updates the elements in the equipment spinner with the equipment items in the order manager.
     */
    // Equipment functions ****
    private void updateEquipmentSpinner() {
        OrderManager om = OrderManager.getInstance();

        Spinner equipment = (Spinner)findViewById(R.id.equipment_spinner);
        int numberOfItems = om.getEquipments().size();
        String[] displayed = new String[numberOfItems];
        for (int i=0; i<numberOfItems; i++) {
            displayed[i] = om.getEquipment(i).getEquipmentName() + "   (" + om.getEquipment(i).getEquipmentQty() + " left)";
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, displayed);
        equipment.setAdapter(adapter);
        equipment.setSelection(equipmentPosition);
    }

    /**
     * Increases the quantity of the equipment selected
     * @param v
     * View that calls this method, so the up-arrow button
     */
    public void increaseEquipment(View v) {
        if (equipmentSelected != null) {
            OrderManager om = OrderManager.getInstance();
            Equipment e = om.getEquipment(equipmentSelected);
            e.setEquipmentQty(e.getEquipmentQty() + 1);
            updateEquipmentSpinner();
        }
    }

    /**
     * Decreases the quantity of the equipment selected
     * @param v
     * View that calls this method, so the down-arrow button
     */
    public void decreaseEquipment(View v) {
        if (equipmentSelected != null) {
            OrderManager om = OrderManager.getInstance();
            Equipment e = om.getEquipment(equipmentSelected);
            if (e.getEquipmentQty() > 0) {
                e.setEquipmentQty(e.getEquipmentQty() - 1);
                updateEquipmentSpinner();
            }
        }
    }
    // ****

    /**
     * Updates the elements in the dishes spinner with the meal items in the order manager.
     */
    // Dishes functions ****
    private void updateDishSpinner() {
        OrderManager om = OrderManager.getInstance();

        Spinner dish = (Spinner)findViewById(R.id.dishes_spinner);
        int numberOfItems = om.getMenus().size();
        String[] displayed = new String[numberOfItems];
        for (int i=0; i<numberOfItems; i++) {
            displayed[i] = om.getMenu(i).getMealName() + "   (popularity " + om.getMenu(i).getPopularity() + ")";
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, displayed);
        dish.setAdapter(adapter);
        dish.setSelection(dishPosition);
    }

    /**
     * Updates the text below the dishes spinner, indicating the quantity of the selected meal that is remaining, the price and the supplies the selected meal is made of.
     */
    public void updateDishInfo() {
        OrderManager om = OrderManager.getInstance();
        Menu myDishSelected = om.getMenu(dishSelected);
        TextView dishesLeft = (TextView) findViewById(R.id.dishes_left_text);
        dishesLeft.setText(myDishSelected.getMealsLeft() + " dishes of " + myDishSelected.getMealName() + " remaining.");
        TextView price = (TextView) findViewById(R.id.dishes_price_text);
        price.setText("Price: " + Double.toString(myDishSelected.getPrice()));
        TextView dishIngredients = (TextView) findViewById(R.id.dishes_ingredients_text);
        String ingredients = myDishSelected.getMealName() + " is made of ";
        for (int i=0; i<myDishSelected.getIngredients().size(); i++) {
            ingredients += myDishSelected.getIngredients().get(i).getFoodName();
            if (i == myDishSelected.getIngredients().size()-1)
                ingredients += ".";
            else if (i == myDishSelected.getIngredients().size()-2)
                ingredients += " and ";
            else
                ingredients += ", ";
        }
        dishIngredients.setText(ingredients);
    }

    /**
     * Opens the CustomizeMenu activity and sends it info on the selected dish.
     * @param v
     * View that calls this method, so the Customize button.
     */
    public void customizeOrder(View v) {
        if (dishSelected != null) {
            Intent intent = new Intent(InventoryMenu.this, CustomizeMenu.class);
            intent.putExtra("dishSelected", dishSelected);
            startActivity(intent);
            startActivityForResult(intent, 4);
        }
    }

    /**
     * Orders the selected meal and calls the dishes (for popularity value) and supplies spinners to be updated with the new values in the order manager.
     * @param v
     * View that calls this method, so the Order button.
     */
    public void placeOrder(View v) {
        if (dishSelected != null) {
            Button order = (Button) findViewById(R.id.order_button);
            order.setError(null);
            try {
                fc.placeOrder(dishSelected);
            } catch (Exception e) {
                order.setError(e.getMessage());
            }
            updateDishSpinner();
            updateSuppliesSpinner();
        }
    }
    // ****

    /**
     * Updates the elements in the supplies spinner with the supply items in the order manager.
     */
    // Supplies functions ****
    private void updateSuppliesSpinner() {
        OrderManager om = OrderManager.getInstance();

        Spinner supplies = (Spinner)findViewById(R.id.supplies_spinner);
        int numberOfItems = om.getFoodSupplies().size();
        String[] displayed = new String[numberOfItems];
        for (int i=0; i<numberOfItems; i++) {
            displayed[i] = om.getFoodSupply(i).getFoodName() + "   (" + om.getFoodSupply(i).getFoodQty() + " left)";
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, displayed);
        supplies.setAdapter(adapter);
        supplies.setSelection(supplyPosition);
    }

    /**
     * Increases the quantity of the supply selected
     * @param v
     * View that calls this method, so the up-arrow button
     */
    public void increaseSupply(View v) {
        if (supplySelected != null) {
            OrderManager om = OrderManager.getInstance();
            Supply s = om.getFoodSupply(supplySelected);
            s.setFoodQty(s.getFoodQty() + 1);
            updateSuppliesSpinner();
            updateDishSpinner();
        }
    }

    /**
     * Decreases the quantity of the supply selected
     * @param v
     * View that calls this method, so the down-arrow button
     */
    public void decreaseSupply(View v) {
        if (supplySelected != null) {
            OrderManager om = OrderManager.getInstance();
            Supply s = om.getFoodSupply(supplySelected);
            if (s.getFoodQty() > 0) {
                s.setFoodQty(s.getFoodQty() - 1);
                updateSuppliesSpinner();
                updateDishSpinner();
            }
        }
    }
    // ****

    /**
     * Gets called whenever user presses the back button when in the Customize Menu to come back to this activity. This method ensures this activity gets updates with the new values in the order manager.
     * @param requestCode
     * Integer sent to CustomizeMenu activity from this activity by the customizeOrder method.
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 4) {
            updateDishSpinner();
            updateDishInfo();
            updateSuppliesSpinner();
            updateEquipmentSpinner();
        }
    }
}

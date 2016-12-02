package ca.mcgill.ecse321.newftms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.mcgill.ecse321.FTMS.controller.FTMSController;
import ca.mcgill.ecse321.FTMS.model.Equipment;
import ca.mcgill.ecse321.FTMS.model.OrderManager;
import ca.mcgill.ecse321.FTMS.model.StaffManager;
import ca.mcgill.ecse321.FTMS.model.Supply;

public class addMenu extends AppCompatActivity {

    private FTMSController fc = new FTMSController();
    private ArrayList<String> selectedSupplies = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);


        ListView mil = (ListView)findViewById(R.id.menuitem_list);
        mil.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        updateSupplyListView();
        mil.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView)view).getText().toString();
                if(selectedSupplies.contains(selectedItem))
                    selectedSupplies.remove(selectedItem);
                else
                    selectedSupplies.add(selectedItem);
            }
        });
    }

    private void refreshData() {
        TextView tvEN = (TextView) findViewById(R.id.newequipment_name);
        tvEN.setText("");
        TextView tvEQ = (TextView) findViewById(R.id.newequipment_quantity);
        tvEQ.setText("");
        TextView tvSN = (TextView) findViewById(R.id.newsupply_name);
        tvSN.setText("");
        TextView tvSQ = (TextView) findViewById(R.id.newsupply_quantity);
        tvSQ.setText("");
        TextView tvSTR = (TextView) findViewById(R.id.newstaff_role);
        tvSTR.setText("");
        TextView tvSTN = (TextView) findViewById(R.id.newstaff_name);
        tvSTN.setText("");
        TextView tvDN = (TextView) findViewById(R.id.newmenuitem_name);
        tvDN.setText("");
        TextView tvDP = (TextView) findViewById(R.id.newmenuitem_price);
        tvDP.setText("");

        Button e = (Button) findViewById(R.id.newequipment_button);
        e.setError(null);
        Button s = (Button) findViewById(R.id.newsupply_button);
        s.setError(null);
        Button d = (Button) findViewById(R.id.newmenuitem_button);
        d.setError(null);
        Button st = (Button) findViewById(R.id.newstaff_button);
        st.setError(null);

        updateSupplyListView();
    }

    private void updateSupplyListView() {
        OrderManager om = OrderManager.getInstance();

        ListView mil = (ListView)findViewById(R.id.menuitem_list);
        int numberOfSupplies = om.getFoodSupplies().size();
        String[] menuItems = new String[numberOfSupplies];
        for (int i=0; i<numberOfSupplies; i++) {
            menuItems[i] = om.getFoodSupply(i).getFoodName();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.menuitemcheck, R.id.menuitemcheckbox, menuItems);
        mil.setAdapter(adapter);

        ListAdapter listAdapter = mil.getAdapter();
        int totalItemsHeight = 0;
        for (int itemPos = 0; itemPos < numberOfSupplies; itemPos++) {
            View item = listAdapter.getView(itemPos, null, mil);
            item.measure(0, 0);
            totalItemsHeight += item.getMeasuredHeight();
        }
        int totalDividersHeight = mil.getDividerHeight() *
                (numberOfSupplies - 1);

        // Set list height.
        ViewGroup.LayoutParams params = mil.getLayoutParams();
        params.height = totalItemsHeight + totalDividersHeight;
        mil.setLayoutParams(params);
        mil.requestLayout();

        selectedSupplies = new ArrayList<String>();
    }

    public void addEquipment(View v) {

        EditText tv1 = (EditText) findViewById(R.id.newequipment_name);
        TextView tv2 = (TextView) findViewById(R.id.newequipment_quantity);
        Button bn = (Button) findViewById(R.id.newequipment_button);

        try {
            fc.createEquipment(tv1.getText().toString(), Integer.parseInt(tv2.getText().toString()));
            refreshData();
        } catch (Exception e) {
            bn.requestFocus();
            bn.setError(e.getMessage());
        }
    }

    public void addSupply(View v) {

        TextView tv1 = (TextView) findViewById(R.id.newsupply_name);
        TextView tv2 = (TextView) findViewById(R.id.newsupply_quantity);
        Button bn = (Button) findViewById(R.id.newsupply_button);

        try {
            fc.createSupply(tv1.getText().toString(), Integer.parseInt(tv2.getText().toString()));
            updateSupplyListView();
            refreshData();
        } catch (Exception e) {
            bn.requestFocus();
            bn.setError(e.getMessage());
        }
    }

    public void addStaff(View v) {

        TextView tv1 = (TextView) findViewById(R.id.newstaff_name);
        TextView tv2 = (TextView) findViewById(R.id.newstaff_role);
        Button bn = (Button) findViewById(R.id.newsupply_button);

        try {
            fc.createEmployee(tv1.getText().toString(), tv2.getText().toString());
            refreshData();
        } catch (Exception e) {
            bn.requestFocus();
            bn.setError(e.getMessage());
        }
    }

    public void addMenuItem(View v) {
        OrderManager om = OrderManager.getInstance();
        List<Supply> ingredients = new ArrayList<Supply>();
        for (int i=0; i<selectedSupplies.size(); i++)
            ingredients.add(om.getFoodSupply(selectedSupplies.get(i)));

        TextView tv1 = (TextView) findViewById(R.id.newmenuitem_name);
        TextView tv2 = (TextView) findViewById(R.id.newmenuitem_price);
        Button bn = (Button) findViewById(R.id.newmenuitem_button);

        try {
            fc.createMenu(tv1.getText().toString(), ingredients, Double.parseDouble(tv2.getText().toString()));
            refreshData();
        } catch (Exception e) {
            bn.requestFocus();
            bn.setError(e.getMessage());
        }
    }

}

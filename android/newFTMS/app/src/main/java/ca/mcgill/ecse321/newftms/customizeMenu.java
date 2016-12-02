package ca.mcgill.ecse321.newftms;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ca.mcgill.ecse321.FTMS.controller.FTMSController;
import ca.mcgill.ecse321.FTMS.model.Menu;
import ca.mcgill.ecse321.FTMS.model.OrderManager;
import ca.mcgill.ecse321.FTMS.model.Supply;

public class customizeMenu extends AppCompatActivity {

    FTMSController fc = new FTMSController();
    private String dishSelected;
    private Menu menuSelected;
    private ArrayList<String> selectedRemoveSupplies;
    private ArrayList<String> selectedExtraSupplies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_menu);

        OrderManager om = OrderManager.getInstance();

        TextView t = (TextView) findViewById(R.id.customize_label);
        t.setTextColor(Color.rgb(0, 0, 200));

        dishSelected = getIntent().getStringExtra("dishSelected");
        menuSelected = om.getMenu(dishSelected);

        createRemoveSuppliesListView();
        createExtraSuppliesListView();
    }

    private void createRemoveSuppliesListView() {
        ListView mil = (ListView)findViewById(R.id.remove_supplies_list);
        mil.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        int numberOfSupplies = menuSelected.getIngredients().size();
        String[] items = new String[numberOfSupplies];
        for (int i=0; i<numberOfSupplies; i++) {
            items[i] = menuSelected.getIngredients().get(i).getFoodName();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.menuitemcheck, R.id.menuitemcheckbox, items);
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

        selectedRemoveSupplies = new ArrayList<String>();

        mil.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView)view).getText().toString();
                if(selectedRemoveSupplies.contains(selectedItem))
                    selectedRemoveSupplies.remove(selectedItem);
                else
                    selectedRemoveSupplies.add(selectedItem);
            }
        });
    }

    private void createExtraSuppliesListView() {
        ListView mil = (ListView)findViewById(R.id.extra_supplies_list);
        mil.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        int numberOfSupplies = menuSelected.getIngredients().size();
        String[] items = new String[numberOfSupplies];
        for (int i=0; i<numberOfSupplies; i++) {
            items[i] = menuSelected.getIngredients().get(i).getFoodName();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.menuitemcheck, R.id.menuitemcheckbox, items);
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

        selectedExtraSupplies = new ArrayList<String>();

        mil.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView)view).getText().toString();
                if(selectedExtraSupplies.contains(selectedItem))
                    selectedExtraSupplies.remove(selectedItem);
                else
                    selectedExtraSupplies.add(selectedItem);
            }
        });
    }

    public void orderCustomizedMeal(View v) {
        if (selectedRemoveSupplies.size() > 0 || selectedExtraSupplies.size() > 0) {
            OrderManager om = OrderManager.getInstance();
            Button btn = (Button) findViewById(R.id.order_customized_button);

            Supply[] remove = new Supply[selectedRemoveSupplies.size()];
            for(int i=0; i<selectedRemoveSupplies.size(); i++)
                remove[i] = om.getFoodSupply(selectedRemoveSupplies.get(i));

            Supply[] extra = new Supply[selectedExtraSupplies.size()];
            for(int i=0; i<selectedExtraSupplies.size(); i++)
                extra[i] = om.getFoodSupply(selectedExtraSupplies.get(i));

            try {
                fc.orderCustomizedMeal(menuSelected, remove, extra);
            } catch (Exception e) {
                btn.setError(e.getMessage());
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            setResult(4);
        }
        return super.onKeyDown(keyCode, event);
    }
}

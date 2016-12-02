package ca.mcgill.ecse321.newftms;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Time;
import java.util.Calendar;
import java.util.TimeZone;

import ca.mcgill.ecse321.FTMS.controller.FTMSController;
import ca.mcgill.ecse321.FTMS.model.Employee;
import ca.mcgill.ecse321.FTMS.model.OrderManager;
import ca.mcgill.ecse321.FTMS.model.StaffManager;

public class scheduleMenu extends AppCompatActivity {

    private int selectedStaffPosition = -1;
    private int selectedDotwPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_menu);

        StaffManager sm = StaffManager.getInstance();

        TextView t_s = (TextView) findViewById(R.id.choose_staff_label); // Decorating in blue
        t_s.setTextColor(Color.rgb(0, 0, 200));

        // Initiate staff spinner
        Spinner staff = (Spinner)findViewById(R.id.staff_spinner);
        int numberOfS = sm.getEmployees().size();
        final String[] itemsS = new String[numberOfS];
        for (int i=0; i<numberOfS; i++) {
            itemsS[i] = sm.getEmployee(i).getStaffName();
        }
        ArrayAdapter<String> adapterS = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, itemsS);
        staff.setAdapter(adapterS);
        staff.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                selectedStaffPosition = position;
                updateRoleInfo();
                updateTimesDisplay();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // avoids error
            }
        });

        // Initiate DayOfTheWeek spinner
        Spinner dotw = (Spinner)findViewById(R.id.dotw_spinner);
        final String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, days);
        dotw.setAdapter(adapter);
        dotw.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                selectedDotwPosition = position;
                updateTimesDisplay();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // avoids error
            }
        });
    }

    // TIME METHODS ****
    public void showTimePickerDialog(View v) {
        TextView tf = (TextView) v;
        Bundle args = getTimeFromLabel(tf.getText());
        args.putInt("id", v.getId());

        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    private Bundle getTimeFromLabel(CharSequence text) {
        Bundle rtn = new Bundle();
        String comps[] = text.toString().split(":");
        int hour = 8;
        int minute = 0;

        if (comps.length == 2) {
            hour = Integer.parseInt(comps[0]);
            minute = Integer.parseInt(comps[1]);
        }

        rtn.putInt("hour", hour);
        rtn.putInt("minute", minute);

        return rtn;
    }

    public void setTime(int id, int hourOfDay, int minute) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(String.format("%02d:%02d", hourOfDay, minute));
    }
    // ****

    public void updateRoleInfo() {
        StaffManager sm = StaffManager.getInstance();
        Employee myStaffSelected = sm.getEmployee(selectedStaffPosition);
        TextView role = (TextView) findViewById(R.id.staff_role_text);
        role.setText("Role: " + myStaffSelected.getStaffRoles());
    }

    public void updateTimesDisplay() {
        StaffManager sm = StaffManager.getInstance();
        int hourStart, hourEnd, minuteStart, minuteEnd;
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("EST"));
        if (sm.getEmployees().size() > 0) { // Avoids null pointer exception
            Employee e = sm.getEmployee(selectedStaffPosition);
            c.setTimeInMillis(e.getDayStartTime(selectedDotwPosition).getTime());
            hourStart = c.get(Calendar.HOUR_OF_DAY);
            minuteStart = c.get(Calendar.MINUTE);
            c.setTimeInMillis(e.getDayEndTime(selectedDotwPosition).getTime());
            hourEnd = c.get(Calendar.HOUR_OF_DAY);
            minuteEnd = c.get(Calendar.MINUTE);
        } else {
            hourStart = 8;
            minuteStart = 0;
            hourEnd = 8;
            minuteEnd = 0;
        }
        setTime(R.id.schedule_starttime_display, hourStart, minuteStart);
        setTime(R.id.schedule_endtime_display, hourEnd, minuteEnd);
    }

    public void updateSchedule(View v) {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("EST"));
        Button updateBtn = (Button) findViewById(R.id.update_schedule_button);
        updateBtn.setError(null);

        TextView tvS = (TextView) findViewById(R.id.schedule_starttime_display);
        String[] t1 = tvS.getText().toString().split(":");
        int h1 = Integer.parseInt(t1[0]);
        int m1 = Integer.parseInt(t1[1]);
        c.set(Calendar.HOUR_OF_DAY, h1);
        c.set(Calendar.MINUTE, m1);
        Time tS = new Time(c.getTimeInMillis());

        TextView tvE = (TextView) findViewById(R.id.schedule_endtime_display);
        String[] t2 = tvE.getText().toString().split(":");
        int h2 = Integer.parseInt(t2[0]);
        int m2 = Integer.parseInt(t2[1]);
        c.set(Calendar.HOUR_OF_DAY, h2);
        c.set(Calendar.MINUTE, m2);
        Time tE = new Time(c.getTimeInMillis());

        StaffManager sm = StaffManager.getInstance();
        FTMSController fc = new FTMSController();
        try {
            fc.updateSchedule(selectedStaffPosition, selectedDotwPosition, tS, tE);
        } catch (Exception e) {
            updateBtn.setError(e.getMessage());
        }
    }
}

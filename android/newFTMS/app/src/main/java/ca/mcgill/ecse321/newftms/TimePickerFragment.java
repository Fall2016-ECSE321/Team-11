package ca.mcgill.ecse321.newftms;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

/**
 * Created by Rony on 2016-11-28.
 */

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int hour = 0;
        int minute = 0;

        // Parse the existing time from the arguments
        Bundle args = getArguments();
        if (args != null) {
            hour = args.getInt("hour");
            minute = args.getInt("minute");
        }

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        scheduleMenu myActivity = (scheduleMenu)getActivity();
        myActivity.setTime(getArguments().getInt("id"), hourOfDay, minute);
    }
}
package ca.mcgill.ecse321.newftms;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

/**
 * When the time in the schedule page is clicked, this activity gets called and a time picker gets created.
 */
public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    /**
     * Creates the time picker with the values displayed in the time textview
     * @param savedInstanceState
     * This bundle has the time information form the textview
     * @return
     * Makes the time picker dialog appear
     */
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

    /**
     * When the time is set in the time picker, this method gets called and it updates the textview for the time.
     * @param view
     * @param hourOfDay
     * Hour in 24-hour format
     * @param minute
     */
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        ScheduleMenu myActivity = (ScheduleMenu)getActivity();
        myActivity.setTime(getArguments().getInt("id"), hourOfDay, minute);
    }
}
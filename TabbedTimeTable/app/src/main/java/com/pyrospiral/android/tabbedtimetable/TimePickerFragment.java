package com.pyrospiral.android.tabbedtimetable;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Kush on 1/17/2015.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

   int pHour;
   int pMinute;

   int day;

   TextView text;
   Calendar cal;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        pHour = hourOfDay;
        pMinute = minute;
        Log.e("Time picker hour", ""+pHour);
        Log.e("Time picker min", ""+pMinute);
        updateTime(text,cal,day);
    }



    // Updates time in text View
    public void updateTime(TextView text,Calendar cal,int day){
        this.text = text;
        this.cal = cal;
        this.day =day;

        if (day == 0)
            cal.set(Calendar.DAY_OF_WEEK,2);
        else if(day == 1)
            cal.set(Calendar.DAY_OF_WEEK,3);
        else if(day == 2)
            cal.set(Calendar.DAY_OF_WEEK,4);
        else if(day == 3)
            cal.set(Calendar.DAY_OF_WEEK,5);
        else if(day == 4)
            cal.set(Calendar.DAY_OF_WEEK,6);


        cal.set(Calendar.HOUR_OF_DAY, pHour); // For 1 PM or 2 PM
        cal.set(Calendar.MINUTE, pMinute);
        cal.set(Calendar.SECOND, 0);

        Log.e("Timepicker","calender "+cal);

        text.setText(
                new StringBuilder()
                        .append(pad(pHour)).append(":")
                        .append(pad(pMinute)));
    }


    // Add padding to numbers less than ten
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
}

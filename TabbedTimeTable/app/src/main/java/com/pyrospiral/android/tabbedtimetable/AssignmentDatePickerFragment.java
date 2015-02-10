package com.pyrospiral.android.tabbedtimetable;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Kush on 1/20/2015.
 */
public class AssignmentDatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {


    int pDay;
    int pMonth;
    int pYear;

    TextView text;

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        pDay = dayOfMonth;
        pMonth = monthOfYear;
        pYear = year;
        updateDate(text);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of TimePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month,day);
    }


    public void updateDate(TextView text){
        this.text = text;
        text.setText(new StringBuilder().append(pDay).append("/").append(pMonth+1).append("/").append(pYear));
    }
}

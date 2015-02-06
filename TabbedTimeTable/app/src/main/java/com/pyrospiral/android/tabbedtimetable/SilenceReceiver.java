package com.pyrospiral.android.tabbedtimetable;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Kush on 2/6/2015.
 */
public class SilenceReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Receiver","received");
        Toast.makeText(context, "Alarm !!!!!!!!!!", Toast.LENGTH_LONG).show();

    }

    public void SetAlarm(Context context)
    {

        Intent i = new Intent(context, SilenceReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 5, i, 0);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        boolean value = prefs.getBoolean("silent_checkbox", true);



            Calendar cal = Calendar.getInstance();
            //Get time from database
            cal.set(Calendar.HOUR_OF_DAY, 21);
            cal.set(Calendar.MINUTE, 23);
            cal.set(Calendar.SECOND, 0);


            AlarmManager am2 = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            am2.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pi);



    }

}

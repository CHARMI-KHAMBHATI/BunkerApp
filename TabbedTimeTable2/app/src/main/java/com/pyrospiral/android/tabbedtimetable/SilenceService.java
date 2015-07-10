package com.pyrospiral.android.tabbedtimetable;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by Kush on 1/26/2015.
 */
public class SilenceService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.e("service ","created");
        setAlarm();
        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    private void setAlarm()
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean value = prefs.getBoolean("silent_checkbox", true);

        if(value) {

            Intent i = new Intent(this, SilenceReceiver.class).putExtra("value", 1);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 5, i, 0);


            Calendar cal = Calendar.getInstance();
            //Get time from database
            cal.set(Calendar.HOUR_OF_DAY, 3);
            cal.set(Calendar.MINUTE, 11);
            cal.set(Calendar.SECOND, 0);


            AlarmManager am2 = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                Log.e("ver","kitkat +");
                am2.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
            } else {
                am2.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
                Log.e("ver","other");
            }

        }
    }
}
package com.pyrospiral.android.tabbedtimetable;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity {


    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

        DrawerFragment drawerFragment = (DrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_fragment);
        drawerFragment.setup(R.id.navigation_fragment,(DrawerLayout)findViewById(R.id.drawer_layout),toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new TimeTableViewPagerFragment())
                    .commit();
        }



        //FOR DAILY ATTENDANCE NOTIFICATION

        Calendar calendar = Calendar.getInstance();
        Intent intent1 = new Intent(this,NotificationMaker.class);

        if(calendar.get(Calendar.HOUR_OF_DAY) > 21)
        {
            calendar.add(Calendar.DAY_OF_MONTH,1);
        }

        calendar.set(Calendar.HOUR_OF_DAY, 21); // For 9PM
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0,intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);


        // Enable Local Datastore.


        //For Silence


        //Start looping

            Calendar Silencecal = Calendar.getInstance();


            Silencecal.add(Calendar.MINUTE, 1);// set calander time from database

            Intent intent = new Intent(this, SilenceReceiver.class);

            intent.putExtra("value",1);  //PUT 1 for silent (when class starts) , 2 for normal (when class ends)
            int intentNum = 435;  //change this for every alarm

            //This remains the same
            PendingIntent sender = PendingIntent.getBroadcast(this, intentNum, intent, PendingIntent.FLAG_ONE_SHOT);
            AlarmManager silenceAlarm = (AlarmManager) getSystemService(ALARM_SERVICE);
            silenceAlarm.setRepeating(AlarmManager.RTC_WAKEUP, Silencecal.getTimeInMillis(),AlarmManager.INTERVAL_DAY * 7, sender);


        //End looping



        //TODO: For Events

        /*
        //Start looping

            Calendar Eventcal = Calendar.getInstance();

            Intent eintent = new Intent(this, EventNotificationReceiver.class);

            //SET THESE FROM DATABASE
            String description = "aaa";
            String chapter = "aaa";
            String timing = "aaa";
            int id = 234;
            Eventcal.add(Calendar.MINUTE, 1);// set calander time from database


            eintent.putExtra("description",description).putExtra("chapter",chapter).putExtra("timing",timing).putExtra("id",id);

            int eintentNum = 435;  //change this for every alarm

            //This remains the same
            PendingIntent p = PendingIntent.getBroadcast(this, eintentNum, eintent, PendingIntent.FLAG_ONE_SHOT);
            AlarmManager eventAlarm = (AlarmManager) getSystemService(ALARM_SERVICE);
            eventAlarm.set(AlarmManager.RTC_WAKEUP, Eventcal.getTimeInMillis(), p);


        //End looping

        */

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_timetableweek) {
            startActivity(new Intent(this,TimeTableWeek.class));
        }

        return super.onOptionsItemSelected(item);
    }



}

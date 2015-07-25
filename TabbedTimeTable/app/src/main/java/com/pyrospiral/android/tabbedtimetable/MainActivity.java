package com.pyrospiral.android.tabbedtimetable;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
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
        drawerFragment.setup(R.id.navigation_fragment, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new TimeTableViewPagerFragment())
                    .commit();
        }



        // Enable Local Datastore.
        DBAdapter dba = new DBAdapter(MainActivity.this);
        dba.open();
        Cursor c1 = dba.getAll();

        //For Silence

        int intentNum = 1;


        //Start looping
        if (c1.moveToFirst()) {

            do {
                Calendar Silencecal = Calendar.getInstance();
                Calendar currentCal = Calendar.getInstance();

                //day

                int index5 = c1.getColumnIndex(DBAdapter.DAY_WEEK);
                String day = c1.getString(index5);
                int dayofweek = getDay(day);
                //start time
                int index7 = c1.getColumnIndex(DBAdapter.START_TIME);
                double a = Double.parseDouble(c1.getString(index7));
                int x = (int) a;
                a = a - x;
                a = a * 60;
                a = (double) Math.round(a * 100) / 100;
                int y = (int) a;

                Silencecal.set(Calendar.DAY_OF_WEEK, dayofweek);
                Silencecal.set(Calendar.HOUR_OF_DAY, x);
                Silencecal.set(Calendar.MINUTE, y);
                Silencecal.set(Calendar.SECOND, 0);

                Intent intent = new Intent(this, SilenceReceiver.class);

                intent.putExtra("value", 1);  //PUT 1 for silent (when class starts) , 2 for normal (when class ends)
                //change this for every alarm

                //This remains the same
                if (Silencecal.getTimeInMillis() > currentCal.getTimeInMillis()) {


                    PendingIntent sender = PendingIntent.getBroadcast(this, intentNum, intent, PendingIntent.FLAG_ONE_SHOT);
                    AlarmManager silenceAlarm = (AlarmManager) getSystemService(ALARM_SERVICE);
                    silenceAlarm.setRepeating(AlarmManager.RTC_WAKEUP, Silencecal.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, sender);
                    intentNum++;
                }

                //end time
                int index8 = c1.getColumnIndex(DBAdapter.END_TIME);
                a = Double.parseDouble(c1.getString(index8));
                x = (int) a;
                a = a - x;
                a = a * 60;
                a = (double) Math.round(a * 100) / 100;
                y = (int) a;

                Calendar Silencecal2 = Calendar.getInstance();

                Silencecal2.set(Calendar.DAY_OF_WEEK, dayofweek);
                Silencecal2.set(Calendar.HOUR_OF_DAY, x);
                Silencecal2.set(Calendar.MINUTE, y);
                Silencecal2.set(Calendar.SECOND, 0);

                Intent intent2 = new Intent(this, SilenceReceiver.class);

                intent2.putExtra("value", 2);  //PUT 1 for silent (when class starts) , 2 for normal (when class ends)
                //change this for every alarm

                //This remains the same
                if (Silencecal.getTimeInMillis() > currentCal.getTimeInMillis()) {

                    PendingIntent senders = PendingIntent.getBroadcast(this, intentNum, intent2, PendingIntent.FLAG_ONE_SHOT);
                    AlarmManager silenceAlarms = (AlarmManager) getSystemService(ALARM_SERVICE);
                    silenceAlarms.setRepeating(AlarmManager.RTC_WAKEUP, Silencecal2.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, senders);
                    intentNum++;
                }

            } while (c1.moveToNext());
            //End looping
        }

        dba.close();


        //TODO: For Events

        DBEvent dbe = new DBEvent(MainActivity.this);
        dbe.open();
        int eintentNum=0;
        Cursor c2 = dbe.getAllContacts();
        if(c2.moveToFirst())
        {
            do{
        //Start looping

                Calendar Eventcal = Calendar.getInstance();
                Calendar currentCal = Calendar.getInstance();

                Intent eintent = new Intent(this, EventNotificationReceiver.class);

                //SET THESE FROM DATABASE
                String description = c2.getString(c2.getColumnIndex(DBEvent.DESCRIPTION));
                String chapter =  c2.getString(c2.getColumnIndex(DBEvent.CHAPTER));
                String timing =  c2.getString(c2.getColumnIndex(DBEvent.TIME));
                String date=c2.getString(c2.getColumnIndex(DBEvent.DATE));

                String a=""+timing.charAt(0)+timing.charAt(1);
                int hour=Integer.parseInt(a)-5;
                Eventcal.set(Calendar.HOUR_OF_DAY,hour);

                a=""+timing.charAt(3)+timing.charAt(4);
                int minute=Integer.parseInt(a);
                Eventcal.set(Calendar.MINUTE,minute);

                String day_of_month=""+date.charAt(0)+date.charAt(1);
                String month=""+date.charAt(3)+date.charAt(4);
                Eventcal.set(Calendar.DAY_OF_MONTH,Integer.parseInt(day_of_month)-1);
                Eventcal.set(Calendar.MONTH,Integer.parseInt(month)-1);


                //int id = 234;
                //Eventcal.add(Calendar.MINUTE, 1);// set calander time from database
                if (Eventcal.getTimeInMillis() > currentCal.getTimeInMillis()) {

                    eintentNum++;
                    eintent.putExtra("description", description).putExtra("chapter", chapter).putExtra("timing", timing).putExtra("id", eintentNum);

                    //change this for every alarm

                    //This remains the same
                    PendingIntent p = PendingIntent.getBroadcast(this, eintentNum, eintent, PendingIntent.FLAG_ONE_SHOT);
                    AlarmManager eventAlarm = (AlarmManager) getSystemService(ALARM_SERVICE);
                    eventAlarm.set(AlarmManager.RTC_WAKEUP, Eventcal.getTimeInMillis(), p);

                }

            }while(c2.moveToNext());

        }
        dbe.close();

    }

    public int getDay(String day)
    {
        int ans=0;
        if (day.equals("MONDAY"))
            ans=2;
        else if (day.equals("TUESDAY"))
            ans=3;
        else if (day.equals("WEDNESDAY"))
            ans=4;
        else if (day.equals("THURSDAY"))
            ans=5;
        else if (day.equals("FRIDAY"))
            ans=6;

        return ans;
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

        if (id == R.id.action_timetableweek) {
            startActivity(new Intent(this,TimeTableWeek.class));
        }

        return super.onOptionsItemSelected(item);
    }



}

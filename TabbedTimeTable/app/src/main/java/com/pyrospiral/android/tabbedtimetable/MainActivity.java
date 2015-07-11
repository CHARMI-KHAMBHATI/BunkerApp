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

        if(calendar.get(Calendar.HOUR_OF_DAY)>23 && Calendar.MINUTE>54)
        {
            calendar.add(Calendar.DAY_OF_MONTH,1);
        }

        calendar.set(Calendar.HOUR_OF_DAY, 23); // For 9PM
        calendar.set(Calendar.MINUTE, 54);
        calendar.set(Calendar.SECOND, 00);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0,intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);


        // Enable Local Datastore.




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

package com.pyrospiral.android.tabbedtimetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;


public class FillAttendance extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_attendance);

        String check=null;
        Intent intent = this.getIntent();

        if(intent != null && intent.hasExtra(Intent.EXTRA_TEXT))
        {
            check =intent.getStringExtra(Intent.EXTRA_TEXT);
        }

        if (savedInstanceState == null && check.equals("Fill")) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container1, new AttendanceFragment())
                    .commit();
        }
        else if(savedInstanceState == null && check.equals("Edit"))
        {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container1, new Modify_Attendance())
                    .commit();
        }
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

        return super.onOptionsItemSelected(item);
    }
}

package com.pyrospiral.android.tabbedtimetable;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class EventDetails extends ActionBarActivity {


    String eventName;
    String chapterName="";
    String date="" ;
    String time="";
    String description="";
    String location="";
    String co1="";
    String co2="";
    String con1="";
    String con2="";
    String logo="";
    String fee="";
    String size="";
    String link="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_event_details);

        String recieved=null;
        int val=-1;

        Intent intent = this.getIntent();

        if(intent != null )
        {
            recieved=intent.getStringExtra(Intent.EXTRA_TEXT);

        }

        if (recieved!=null)
            val=Integer.parseInt(recieved);

        DBEvent dbe=new DBEvent(EventDetails.this);
        dbe.open();
        Cursor c2=dbe.getAllContacts();

        TextView event_name, chapter_name, event_date, event_time, event_location, event_desciption, event_logo,event_fee,event_size,
        event_co1,event_co2,event_con1,event_con2,event_link;
        int i=0;

        if(c2.moveToFirst()) {


            do {
                eventName=c2.getString(c2.getColumnIndex(DBEvent.EVENT));
                chapterName=c2.getString(c2.getColumnIndex(DBEvent.CHAPTER));
                date=c2.getString(c2.getColumnIndex(DBEvent.DATE));
                time=c2.getString(c2.getColumnIndex(DBEvent.TIME));
                location=c2.getString(c2.getColumnIndex(DBEvent.LOCATION));
                co1=c2.getString(c2.getColumnIndex(DBEvent.COORDI1N));
                co2=c2.getString(c2.getColumnIndex(DBEvent.COORDI2N));
                con1=c2.getString(c2.getColumnIndex(DBEvent.COORDI1NU));
                con2=c2.getString(c2.getColumnIndex(DBEvent.COORDI2NU));
                fee=c2.getString(c2.getColumnIndex(DBEvent.FEE));
                link=c2.getString(c2.getColumnIndex(DBEvent.LINK));
                size=c2.getString(c2.getColumnIndex(DBEvent.TEAMSIZE));
                description=c2.getString(c2.getColumnIndex(DBEvent.DESCRIPTION));



                if(i==val)
                    break;

                i++;

            }while (c2.moveToNext());
        }

        //ProgressBar pb = (ProgressBar) findViewById(R.id.progress);
        //pb.setVisibility(View.GONE);
        //LinearLayout ll=(LinearLayout)findViewById(R.id.layout);
        //ll.setVisibility(View.VISIBLE);
        logo = String.valueOf(chapterName.charAt(0));
        event_date = (TextView) findViewById(R.id.date);
        chapter_name = (TextView) findViewById(R.id.chapter_name);
        event_name = (TextView) findViewById(R.id.event_name);
        event_time = (TextView) findViewById(R.id.time);
        event_location = (TextView) findViewById(R.id.location);
        event_desciption = (TextView) findViewById(R.id.desc);
        event_logo = (TextView) findViewById(R.id.logo);
        event_co1 = (TextView) findViewById(R.id.co_name);
        event_co2 = (TextView) findViewById(R.id.co_name2);
        event_con1 = (TextView) findViewById(R.id.co_number);
        event_con2 = (TextView) findViewById(R.id.co_number2);
        event_fee = (TextView) findViewById(R.id.reg_fee);
        event_link = (TextView) findViewById(R.id.reg_link);

        event_date.setText(date);
        event_name.setText(eventName);
        event_time.setText(time);
        event_location.setText(location);
        event_desciption.setText(description);
        event_co1.setText(co1);
        event_co2.setText(co2);
        event_con1.setText(con1);
        event_con2.setText(con2);
        event_fee.setText(fee);
        event_logo.setText(logo);
        event_link.setText(link);
        chapter_name.setText(chapterName);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_details, menu);
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

        return super.onOptionsItemSelected(item);
    }
}

package com.pyrospiral.android.tabbedtimetable;

import android.app.DialogFragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class TimeTableEntry extends ActionBarActivity {


    private LinearLayout monTime;
    private LinearLayout tueTime;
    private LinearLayout wedTime;
    private LinearLayout thuTime;
    private LinearLayout friTime;

    private TextView monStartTime;
    private TextView tueStartTime;
    private TextView wedStartTime;
    private TextView thuStartTime;
    private TextView friStartTime;


    private TextView monEndTime;
    private TextView tueEndTime;
    private TextView wedEndTime;
    private TextView thuEndTime;
    private TextView friEndTime;

    TimePickerFragment TimePicker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_entry);
        Button saves=(Button)findViewById(R.id.save);
        Button discards=(Button)findViewById(R.id.discard);

        final EditText subjects=(EditText)findViewById(R.id.subject);


        EditText teachers=(EditText)findViewById(R.id.monTeacherText);

        monTime = (LinearLayout) findViewById(R.id.mondayTimings);
        tueTime = (LinearLayout) findViewById(R.id.tuesdayTimings);
        wedTime = (LinearLayout) findViewById(R.id.wednesdayTimings);
        thuTime = (LinearLayout) findViewById(R.id.thursdayTimings);
        friTime = (LinearLayout) findViewById(R.id.fridayTimings);

        monStartTime = (TextView) findViewById(R.id.monStartTimeDisplay);
        tueStartTime = (TextView) findViewById(R.id.tueStartTimeDisplay);
        wedStartTime = (TextView) findViewById(R.id.wedStartTimeDisplay);
        thuStartTime = (TextView) findViewById(R.id.thuStartTimeDisplay);
        friStartTime = (TextView) findViewById(R.id.friStartTimeDisplay);

        monEndTime = (TextView) findViewById(R.id.monEndTimeDisplay);
        tueEndTime = (TextView) findViewById(R.id.tueEndTimeDisplay);
        wedEndTime = (TextView) findViewById(R.id.wedEndTimeDisplay);
        thuEndTime = (TextView) findViewById(R.id.thuEndTimeDisplay);
        friEndTime = (TextView) findViewById(R.id.friEndTimeDisplay);



        monStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Entry","Button Clicked");
                Toast.makeText(getBaseContext(), "yololl", Toast.LENGTH_LONG).show();
                showTimePickerDialog();
                TimePicker.updateTime(monStartTime);
            }
        });
        tueStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Entry","Button Clicked");
                showTimePickerDialog();
                TimePicker.updateTime(tueStartTime);
            }
        });
        wedStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Entry","Button Clicked");
                showTimePickerDialog();
                TimePicker.updateTime(wedStartTime);
            }
        });
        thuStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Entry","Button Clicked");
                showTimePickerDialog();
                TimePicker.updateTime(thuStartTime);
            }
        });
        friStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Entry","Button Clicked");
                showTimePickerDialog();
                TimePicker.updateTime(friStartTime);
            }
        });
        monEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Entry","Button Clicked");
                showTimePickerDialog();
                TimePicker.updateTime(monEndTime);
            }
        });
        tueEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Entry","Button Clicked");
                showTimePickerDialog();
                TimePicker.updateTime(tueEndTime);
            }
        });
        wedEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Entry","Button Clicked");
                showTimePickerDialog();
                TimePicker.updateTime(wedEndTime);
            }
        });
        thuEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Entry","Button Clicked");
                showTimePickerDialog();
                TimePicker.updateTime(thuEndTime);
            }
        });
        friEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Entry","Button Clicked");
                showTimePickerDialog();
                TimePicker.updateTime(friEndTime);
            }
        });


        final DBAdapter db=new DBAdapter(TimeTableEntry.this);

        saves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int flag=0;

                // TODO Auto-generated method stub
                db.open();


                CheckBox bix = (CheckBox) findViewById(R.id.checkBoxMon);
                boolean val = bix.isChecked();

                if(val)
                {
                    String start_ts=monStartTime.getText().toString();
                    String end_ts=monEndTime.getText().toString();
                    double start_t=0,end_t=0;
                    int j;
                    for( j=0;start_ts.charAt(j)!=':';j++)
                    {
                        start_t=start_t*10+(start_ts.charAt(j)-48);
                    }

                    start_t=start_t+(double)((start_ts.charAt(j+1)-48)*10+(start_ts.charAt(j+2)-48))/60;

                    Toast.makeText(getBaseContext(), "VALUE IS "+start_t+"", Toast.LENGTH_LONG).show();

                    for(j=0;end_ts.charAt(j)!=':';j++)
                    {
                        end_t=end_t*10+(end_ts.charAt(j)-48);
                    }

                    end_t=end_t+(double)((end_ts.charAt(j+1)-48)*10+(end_ts.charAt(j+2)-48))/60;



                    String subject_t=subjects.getText().toString();

                    subject_t=subject_t.toUpperCase();

                    EditText teachers=(EditText)findViewById(R.id.monTeacherText);

                    String teacher_t=teachers.getText().toString();
                    teacher_t=teacher_t.toUpperCase();

                    if(start_t>=end_t || subject_t=="" || teacher_t=="")
                    {
                        Toast.makeText(getBaseContext(), "you drunk bro?", Toast.LENGTH_LONG).show();
                        flag=1;
                    }

                    if(flag==0)
                        db.insertContact(subject_t,start_t,end_t,teacher_t,"MONDAY");

                    //Toast.makeText(getBaseContext(), "yolo", Toast.LENGTH_LONG).show();

                }

                //  Toast.makeText(getBaseContext(), "yolo", Toast.LENGTH_LONG).show();



                //Cursor c = db.getAllContacts();
               /*if (c.moveToFirst()) {
                    do {
                        //DisplayContact(c);
                    } while (c.moveToNext());
                }
                */
                db.close();
                //Toast.makeText(getBaseContext(), "Update successful", Toast.LENGTH_LONG).show();

                //TUESDAY
                flag=0;

                bix = (CheckBox) findViewById(R.id.checkBoxTue);
                val = bix.isChecked();
                db.open();
                if(val)
                {
                    String start_ts=tueStartTime.getText().toString();
                    String end_ts=tueEndTime.getText().toString();
                    double start_t=0,end_t=0;
                    int j;
                    for( j=0;start_ts.charAt(j)!=':';j++)
                    {
                        start_t=start_t*10+(start_ts.charAt(j)-48);
                    }

                    start_t=start_t+(double)((start_ts.charAt(j+1)-48)*10+(start_ts.charAt(j+2)-48))/60;

                    Toast.makeText(getBaseContext(), "VALUE IS "+start_t+"", Toast.LENGTH_LONG).show();

                    for(j=0;end_ts.charAt(j)!=':';j++)
                    {
                        end_t=end_t*10+(end_ts.charAt(j)-48);
                    }

                    end_t=end_t+(double)((end_ts.charAt(j+1)-48)*10+(end_ts.charAt(j+2)-48))/60;



                    String subject_t=subjects.getText().toString();

                    subject_t=subject_t.toUpperCase();

                    EditText teachers=(EditText)findViewById(R.id.tueTeacherText);

                    String teacher_t=teachers.getText().toString();
                    teacher_t=teacher_t.toUpperCase();

                    if(start_t>=end_t || subject_t=="" || teacher_t=="")
                    {
                        Toast.makeText(getBaseContext(), "you drunk bro?", Toast.LENGTH_LONG).show();
                        flag=1;
                    }

                    if(flag==0)
                        db.insertContact(subject_t,start_t,end_t,teacher_t,"TUESDAY");

                 //   Toast.makeText(getBaseContext(), "OY DUDE", Toast.LENGTH_LONG).show();

                }
                db.close();

                //WEDNESDAY
                flag=0;

                bix = (CheckBox) findViewById(R.id.checkBoxWed);
                val = bix.isChecked();
                db.open();
                if(val)
                {
                    String start_ts=wedStartTime.getText().toString();
                    String end_ts=wedEndTime.getText().toString();
                    double start_t=0,end_t=0;
                    int j;
                    for( j=0;start_ts.charAt(j)!=':';j++)
                    {
                        start_t=start_t*10+(start_ts.charAt(j)-48);
                    }

                    start_t=start_t+(double)((start_ts.charAt(j+1)-48)*10+(start_ts.charAt(j+2)-48))/60;

                    Toast.makeText(getBaseContext(), "VALUE IS "+start_t+"", Toast.LENGTH_LONG).show();

                    for(j=0;end_ts.charAt(j)!=':';j++)
                    {
                        end_t=end_t*10+(end_ts.charAt(j)-48);
                    }

                    end_t=end_t+(double)((end_ts.charAt(j+1)-48)*10+(end_ts.charAt(j+2)-48))/60;



                    String subject_t=subjects.getText().toString();

                    subject_t=subject_t.toUpperCase();

                    EditText teachers=(EditText)findViewById(R.id.wedTeacherText);

                    String teacher_t=teachers.getText().toString();
                    teacher_t=teacher_t.toUpperCase();

                    if(start_t>=end_t || subject_t=="" || teacher_t=="")
                    {
                        Toast.makeText(getBaseContext(), "you drunk bro?", Toast.LENGTH_LONG).show();
                        flag=1;
                    }

                    if(flag==0)
                        db.insertContact(subject_t,start_t,end_t,teacher_t,"WEDNESDAY");



                }
                db.close();

                //THURSDAY
                flag=0;
               // final DBAdapter dbth=new DBAdapter(TimeTableEntry.this);
                bix = (CheckBox) findViewById(R.id.checkBoxThu);
                val = bix.isChecked();
                db.open();
                if(val)
                {
                    String start_ts=thuStartTime.getText().toString();
                    String end_ts=thuEndTime.getText().toString();
                    double start_t=0,end_t=0;
                    int j;
                    for( j=0;start_ts.charAt(j)!=':';j++)
                    {
                        start_t=start_t*10+(start_ts.charAt(j)-48);
                    }

                    start_t=start_t+(double)((start_ts.charAt(j+1)-48)*10+(start_ts.charAt(j+2)-48))/60;

                    Toast.makeText(getBaseContext(), "VALUE IS "+start_t+"", Toast.LENGTH_LONG).show();

                    for(j=0;end_ts.charAt(j)!=':';j++)
                    {
                        end_t=end_t*10+(end_ts.charAt(j)-48);
                    }

                    end_t=end_t+(double)((end_ts.charAt(j+1)-48)*10+(end_ts.charAt(j+2)-48))/60;



                    String subject_t=subjects.getText().toString();

                    subject_t=subject_t.toUpperCase();

                    EditText teachers=(EditText)findViewById(R.id.thuTeacherText);

                    String teacher_t=teachers.getText().toString();
                    teacher_t=teacher_t.toUpperCase();

                    if(start_t>=end_t || subject_t=="" || teacher_t=="")
                    {
                        Toast.makeText(getBaseContext(), "you drunk bro?", Toast.LENGTH_LONG).show();
                        flag=1;
                    }

                    if(flag==0)
                        db.insertContact(subject_t,start_t,end_t,teacher_t,"THURSDAY");



                }
                db.close();

                //FRIDAY
                flag=0;
                //final DBAdapter dbf=new DBAdapter(TimeTableEntry.this);
                bix = (CheckBox) findViewById(R.id.checkBoxFri);
                val = bix.isChecked();
                db.open();
                if(val)
                {
                    String start_ts=friStartTime.getText().toString();
                    String end_ts=friEndTime.getText().toString();
                    double start_t=0,end_t=0;
                    int j;
                    for( j=0;start_ts.charAt(j)!=':';j++)
                    {
                        start_t=start_t*10+(start_ts.charAt(j)-48);
                    }

                    start_t=start_t+(double)((start_ts.charAt(j+1)-48)*10+(start_ts.charAt(j+2)-48))/60;

                    Toast.makeText(getBaseContext(), "VALUE IS "+start_t+"", Toast.LENGTH_LONG).show();

                    for(j=0;end_ts.charAt(j)!=':';j++)
                    {
                        end_t=end_t*10+(end_ts.charAt(j)-48);
                    }

                    end_t=end_t+(double)((end_ts.charAt(j+1)-48)*10+(end_ts.charAt(j+2)-48))/60;



                    String subject_t=subjects.getText().toString();

                    subject_t=subject_t.toUpperCase();

                    EditText teachers=(EditText)findViewById(R.id.friTeacherText);

                    String teacher_t=teachers.getText().toString();
                    teacher_t=teacher_t.toUpperCase();

                    if(start_t>=end_t || subject_t=="" || teacher_t=="")
                    {
                        //Toast.makeText(getBaseContext(), "you drunk bro?", Toast.LENGTH_LONG).show();
                        flag=1;
                    }

                    if(flag==0)
                        db.insertContact(subject_t,start_t,end_t,teacher_t,"FRIDAY");



                }
                db.close();


              //  Toast.makeText(getBaseContext(), "yolo", Toast.LENGTH_LONG).show();



                String s;
                final DBAttendence dbat=new DBAttendence(TimeTableEntry.this);
                dbat.open();
                String subject_t=subjects.getText().toString();
                subject_t=subject_t.toUpperCase();
                Cursor c=dbat.getAllContacts();
                flag=0;
                if(c.moveToFirst())
                {

                    do {
                        int index1=c.getColumnIndex(DBAttendence.SUBJECT);
                        s=c.getString(index1);
                        subject_t=subject_t.toUpperCase();
                        Log.e("USER INPUT IS ",subject_t);
                        Log.e("DATABSE IS ",s);

                        if(s.compareTo(subject_t)==0)
                        {
                            flag=1;
                        }

                    }while (c.moveToNext());
                }

                if(flag==0)
                    dbat.insertContact(subject_t);




                dbat.close();

                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();

            }

        });


        discards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                CheckBox bix = (CheckBox) findViewById(R.id.checkBoxMon);
                bix.setChecked(false);
                bix = (CheckBox) findViewById(R.id.checkBoxTue);
                bix.setChecked(false);
                bix = (CheckBox) findViewById(R.id.checkBoxWed);
                bix.setChecked(false);
                bix = (CheckBox) findViewById(R.id.checkBoxThu);
                bix.setChecked(false);
                bix = (CheckBox) findViewById(R.id.checkBoxFri);
                bix.setChecked(false);
                subjects.setText("");
                monTime.setVisibility(View.GONE);

                tueTime.setVisibility(View.GONE);

                wedTime.setVisibility(View.GONE);

                thuTime.setVisibility(View.GONE);

                friTime.setVisibility(View.GONE);

                EditText teachers=(EditText)findViewById(R.id.friTeacherText);
                teachers.setText("");

                teachers=(EditText)findViewById(R.id.thuTeacherText);
                teachers.setText("");

                teachers=(EditText)findViewById(R.id.wedTeacherText);
                teachers.setText("");

                teachers=(EditText)findViewById(R.id.tueTeacherText);
                teachers.setText("");

                teachers=(EditText)findViewById(R.id.monTeacherText);
                teachers.setText("");




                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();



            }

        });

    }//end of on-create


    public void onCheckboxClicked(View view){
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBoxMon:
                if (checked)
                    monTime.setVisibility(View.VISIBLE);
                else
                    monTime.setVisibility(View.GONE);
                break;
            case R.id.checkBoxTue:
                if (checked)
                    tueTime.setVisibility(View.VISIBLE);
                else
                    tueTime.setVisibility(View.GONE);
                break;
            case R.id.checkBoxWed:
                if (checked)
                    wedTime.setVisibility(View.VISIBLE);
                else
                    wedTime.setVisibility(View.GONE);
                break;
            case R.id.checkBoxThu:
                if (checked)
                    thuTime.setVisibility(View.VISIBLE);
                else
                    thuTime.setVisibility(View.GONE);
                break;
            case R.id.checkBoxFri:
                if (checked)
                    friTime.setVisibility(View.VISIBLE);
                else
                    friTime.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }




    public void showTimePickerDialog() {
        DialogFragment newFragment = new TimePickerFragment();
        TimePicker = (TimePickerFragment) newFragment;
        newFragment.show(getFragmentManager(), "timePicker");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_time_table_entry, menu);
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

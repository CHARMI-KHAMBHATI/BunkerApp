package com.pyrospiral.android.tabbedtimetable;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Timer;
import java.util.TimerTask;


public class TimeTableDownload extends ActionBarActivity {

    static String[] mSubjects=new String[100];
    static boolean[] mAssignments=new boolean[10];
    static String[] mAttendances=new String[100];
    static String[] mTeachers=new String[100];

    static Double[] mStartTimings=new Double[100];

    static Double[] mEndTimings=new Double[100];

    static String[] mDay=new String[100];

    Timer timer;

    TimerTask timerTask;

    final Handler handler = new Handler();










    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_entry);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new SelectYear())
                    .commit();
        }
        // Button saves=(Button)findViewById(R.id.save);


        /*final ProgressBar spinner;
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        final Button discards=(Button)findViewById(R.id.discard);
        discards.setVisibility(View.GONE);



        //   final EditText subjects=(EditText)findViewById(R.id.subject);


        //  EditText teachers=(EditText)findViewById(R.id.monTeacherText);



        ParseQuery<ParseObject> query = ParseQuery.getQuery("Time_Table_Btech2_coed");
        // query.whereEqualTo("DAY_week", "MONDAY");
        //query.orderByAscending("Start_time");
        query.findInBackground(new FindCallback<ParseObject>() {

            public void done(List<ParseObject> Subject, ParseException e) {
                if (e == null) {
                    int i=0;

                    //Log.e("score", "Retrieved " + Subject.size() + " scores");

                    String descriptions = null;
                    for (ParseObject totem : Subject)
                    {

                        mSubjects[i] = totem.getString("Subject");


                        DBAttendence db=new DBAttendence(TimeTableDownload.this);
                        db.open();
                        int flagg=0;
                        Cursor c2=db.getAllContacts();
                        if(c2.moveToFirst()) {
                            do {


                                int index1 = c2.getColumnIndex(DBAttendence.SUBJECT);
                                String s = c2.getString(index1);
                                String subject_t = mSubjects[i].toUpperCase();

                                if (s.compareTo(subject_t) == 0) {
                                    flagg = 1;
                                }
                            }while (c2.moveToNext());
                        }
                        if(flagg==0)
                        {
                            db.insertContact(mSubjects[i].toUpperCase());
                        }
                        db.close();


                        //  Toast.makeText(TimeTableEntry.this,mSubjects[i],Toast.LENGTH_SHORT).show();
                        i++;
                        //   count++;

                    }
                    i=0;
                    for (ParseObject totem : Subject)
                    {

                        mTeachers[i] = totem.getString("Teacher");
                        i++;

                    }

                    i=0;
                    for (ParseObject totem : Subject)
                    {

                        mDay[i] = totem.getString("DAY_week");
                        i++;

                    }

                    i=0;
                    for (ParseObject totem : Subject)
                    {

                        double a= totem.getDouble("Start_time");
                        mStartTimings[i%10]=a;

                        i++;

                    }
                    i=0;
                    for (ParseObject totem : Subject)
                    {

                        double a= totem.getDouble("End_time");
                        mEndTimings[i%10]=a;

                        i++;

                    }








                } else {
                    Log.d("score", "Error: " + e.getMessage());
                    Toast.makeText(TimeTableDownload.this,"ERROR, CANT CONNECT",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });



        final DBAdapter db=new DBAdapter(TimeTableDownload.this);








        discards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                // sleep(2000);

                db.open();
                db.deleteEverything();
                db.close();





                //Toast.makeText(TimeTableEntry.this,"REACHED THIS STEP 1",Toast.LENGTH_SHORT).show();
                // TODO Auto-generated method stub
                db.open();
                //Toast.makeText(TimeTableEntry.this,"REACHED THIS STEP 2",Toast.LENGTH_SHORT).show();
                for(int i=0;;i++)
                {
                    if(mSubjects[i]==null)
                        break;

                    db.insertContact(mSubjects[i], mStartTimings[i], mEndTimings[i], mTeachers[i], mDay[i]);
                    //   Toast.makeText(TimeTableEntry.this,"THIS 3",Toast.LENGTH_SHORT).show();
                }
                db.close();
                String s;

                *//*

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
                *//*


                Intent intent = new Intent();
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //Toast.makeText(TimeTableEntry.this,"REACHED THIS STEP 4",Toast.LENGTH_SHORT).show();
                finish();

            }

        });
        //  Toast.makeText(TimeTableEntry.this,"REACHED THIS STEP a",Toast.LENGTH_SHORT).show();



*/


    }//end of on-create









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



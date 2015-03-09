package com.pyrospiral.android.tabbedtimetable;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.getbase.floatingactionbutton.FloatingActionButton;

/**
 * Created by Kush on 1/14/2015.
 */
public class TimeTableFragment extends ListFragment {

    int mNum;
    TimeTableArrayList list;



    static String[] mSubjects=new String[100];
    static boolean[] mAssignments=new boolean[10];
    static String[] mAttendances=new String[100];
    static String[] mTeachers=new String[100];

    static String[] mTimings=new String[100];



    static TimeTableFragment newInstance(int position) {
        TimeTableFragment f = new TimeTableFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", position);
        f.setArguments(args);



        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_day, container,
                false);


        TimeTableListAdapter adapter = new TimeTableListAdapter(getActivity(), list.items);
        setListAdapter(adapter);

        FloatingActionButton buttona = (FloatingActionButton) v.findViewById(R.id.action_a);
        buttona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),TimeTableEntry.class));
            }
        });

        FloatingActionButton buttonb = (FloatingActionButton) v.findViewById(R.id.action_b);
        buttonb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),SubjectRemoveActivity.class));
            }
        });

        return v;
    }


    /**
     * When creating, retrieve this instance's number from its arguments.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        final DBAdapter db=new DBAdapter(getActivity());



        mNum = getArguments() != null ? getArguments().getInt("num") : 1;

        switch (mNum)
        {
            case 0:

                int i=0;
                db.open();
                Cursor c = db.getAllContacts("MONDAY");
                if (c.moveToFirst()) {
                    do {
                        int index=c.getColumnIndex(DBAdapter.SUBJECT);
                        //Log.e("Adesh",c.getString(3));
                        //mSubjects[i] = ;
                        mSubjects[i]=c.getString(index);
                        //int index2=c.getColumnIndex(DBAdapterM.ATTENDANCE);
                        //mAttendances[i]=c.getString(index2);
                        int index3=c.getColumnIndex(DBAdapter.TEACHER);
                        mTeachers[i]=c.getString(index3);
                        //Log.e("ss",mSubjects[i]);
                       // int index4=c.getColumnIndex(DBAdapterM.ATTENDANCE);
                        //mAttendances[i]=c.getString(index4);

                        int index5=c.getColumnIndex(DBAdapter.TEACHER);
                        mTeachers[i]=c.getString(index5);

                        int index6=c.getColumnIndex(DBAdapter.ASSIGNMENT);
                        mAssignments[i]=false;

                        int index7=c.getColumnIndex(DBAdapter.START_TIME);
                        double a=Double.parseDouble(c.getString(index7));
                        int x=(int)a;
                        a=a-x;
                        a=a*60;
                        a=(double)Math.round(a * 100)/100;
                        int y=(int)a;
                        if(y<10)
                            mTimings[i]=Integer.toString(x)+":0"+Integer.toString(y);
                        else
                            mTimings[i]=Integer.toString(x)+":"+Integer.toString(y);

                        int index8=c.getColumnIndex(DBAdapter.END_TIME);

                        a=Double.parseDouble(c.getString(index8));
                        x=(int)a;
                        a=a-x;
                        a=a*60;
                        a=(double)Math.round(a * 100)/100;
                        y=(int)a;
                        if(y<10)
                            mTimings[i]=mTimings[i]+"-"+Integer.toString(x)+":0"+Integer.toString(y);
                        else
                            mTimings[i]=mTimings[i]+"-"+Integer.toString(x)+":"+Integer.toString(y);








                        i++;

                    } while (c.moveToNext());
                }
                db.close();






                //mSubjects = new String[]{"5","5","5","5"};
                //mAssignments = new boolean[]{false,false,false,false};
                //mAttendances = new String[]{"95","95","95","95"};
                //mTeachers = new String[]{"a","b","c","d"};
                break;

            case 1:

                i=0;
                db.open();
                c = db.getAllContacts("TUESDAY");
                if (c.moveToFirst()) {
                    do {
                        int index=c.getColumnIndex(DBAdapter.SUBJECT);
                        //Log.e("Adesh",c.getString(3));
                        //mSubjects[i] = ;
                        mSubjects[i]=c.getString(index);
                        //int index2=c.getColumnIndex(DBAdapterM.ATTENDANCE);
                        //mAttendances[i]=c.getString(index2);
                        int index3=c.getColumnIndex(DBAdapter.TEACHER);
                        mTeachers[i]=c.getString(index3);
                        //Log.e("ss",mSubjects[i]);
                       // int index4=c.getColumnIndex(DBAdapterTu.ATTENDANCE);
                        //mAttendances[i]=c.getString(index4);

                        int index5=c.getColumnIndex(DBAdapter.TEACHER);
                        mTeachers[i]=c.getString(index5);

                        int index6=c.getColumnIndex(DBAdapter.ASSIGNMENT);
                        mAssignments[i]=false;

                        int index7=c.getColumnIndex(DBAdapter.START_TIME);
                        double a=Double.parseDouble(c.getString(index7));
                        int x=(int)a;
                        a=a-x;
                        a=a*60;
                        a=(double)Math.round(a * 100)/100;
                        int y=(int)a;
                        if(y<10)
                            mTimings[i]=Integer.toString(x)+":0"+Integer.toString(y);
                        else
                            mTimings[i]=Integer.toString(x)+":"+Integer.toString(y);

                        int index8=c.getColumnIndex(DBAdapter.END_TIME);

                        a=Double.parseDouble(c.getString(index8));
                        x=(int)a;
                        a=a-x;
                        a=a*60;
                        a=(double)Math.round(a * 100)/100;
                        y=(int)a;
                        if(y<10)
                            mTimings[i]=mTimings[i]+"-"+Integer.toString(x)+":0"+Integer.toString(y);
                        else
                            mTimings[i]=mTimings[i]+"-"+Integer.toString(x)+":"+Integer.toString(y);


                        i++;

                    } while (c.moveToNext());
                }
                db.close();






                break;
            case 2:
                i=0;
                db.open();
                c = db.getAllContacts("WEDNESDAY");
                if (c.moveToFirst()) {
                    do {
                        int index=c.getColumnIndex(DBAdapter.SUBJECT);
                        //Log.e("Adesh",c.getString(3));
                        //mSubjects[i] = ;
                        mSubjects[i]=c.getString(index);
                        //int index2=c.getColumnIndex(DBAdapterM.ATTENDANCE);
                        //mAttendances[i]=c.getString(index2);
                        int index3=c.getColumnIndex(DBAdapter.TEACHER);
                        mTeachers[i]=c.getString(index3);
                        //Log.e("ss",mSubjects[i]);
                        //int index4=c.getColumnIndex(DBAdapterW.ATTENDANCE);
                        //mAttendances[i]=c.getString(index4);

                        int index5=c.getColumnIndex(DBAdapter.TEACHER);
                        mTeachers[i]=c.getString(index5);

                        int index6=c.getColumnIndex(DBAdapter.ASSIGNMENT);
                        mAssignments[i]=false;

                        int index7=c.getColumnIndex(DBAdapter.START_TIME);
                        double a=Double.parseDouble(c.getString(index7));
                        int x=(int)a;
                        a=a-x;
                        a=a*60;
                        a=(double)Math.round(a * 100)/100;
                        int y=(int)a;
                        if(y<10)
                            mTimings[i]=Integer.toString(x)+":0"+Integer.toString(y);
                        else
                            mTimings[i]=Integer.toString(x)+":"+Integer.toString(y);

                        int index8=c.getColumnIndex(DBAdapter.END_TIME);

                        a=Double.parseDouble(c.getString(index8));
                        x=(int)a;
                        a=a-x;
                        a=a*60;
                        a=(double)Math.round(a * 100)/100;
                        y=(int)a;
                        if(y<10)
                            mTimings[i]=mTimings[i]+"-"+Integer.toString(x)+":0"+Integer.toString(y);
                        else
                            mTimings[i]=mTimings[i]+"-"+Integer.toString(x)+":"+Integer.toString(y);



                        i++;

                    } while (c.moveToNext());
                }
                db.close();

                break;
            case 3:
                i=0;
                db.open();
                c = db.getAllContacts("THURSDAY");
                if (c.moveToFirst()) {
                    do {
                        int index=c.getColumnIndex(DBAdapter.SUBJECT);
                        //Log.e("Adesh",c.getString(3));
                        //mSubjects[i] = ;
                        mSubjects[i]=c.getString(index);
                        //int index2=c.getColumnIndex(DBAdapterM.ATTENDANCE);
                        //mAttendances[i]=c.getString(index2);
                        int index3=c.getColumnIndex(DBAdapter.TEACHER);
                        mTeachers[i]=c.getString(index3);
                        //Log.e("ss",mSubjects[i]);
                        //int index4=c.getColumnIndex(DBAdapterTh.ATTENDANCE);
                        //mAttendances[i]=c.getString(index4);

                        int index5=c.getColumnIndex(DBAdapter.TEACHER);
                        mTeachers[i]=c.getString(index5);

                        int index6=c.getColumnIndex(DBAdapter.ASSIGNMENT);
                        mAssignments[i]=false;

                        int index7=c.getColumnIndex(DBAdapter.START_TIME);
                        double a=Double.parseDouble(c.getString(index7));
                        int x=(int)a;
                        a=a-x;
                        a=a*60;
                        a=(double)Math.round(a * 100)/100;
                        int y=(int)a;
                        if(y<10)
                            mTimings[i]=Integer.toString(x)+":0"+Integer.toString(y);
                        else
                            mTimings[i]=Integer.toString(x)+":"+Integer.toString(y);

                        int index8=c.getColumnIndex(DBAdapter.END_TIME);

                        a=Double.parseDouble(c.getString(index8));
                        x=(int)a;
                        a=a-x;
                        a=a*60;
                        a=(double)Math.round(a * 100)/100;
                        y=(int)a;
                        if(y<10)
                            mTimings[i]=mTimings[i]+"-"+Integer.toString(x)+":0"+Integer.toString(y);
                        else
                            mTimings[i]=mTimings[i]+"-"+Integer.toString(x)+":"+Integer.toString(y);



                        i++;

                    } while (c.moveToNext());
                }
                db.close();
                break;
            case 4:
                i=0;
                db.open();
                c = db.getAllContacts("FRIDAY");
                if (c.moveToFirst()) {
                    do {
                        int index=c.getColumnIndex(DBAdapter.SUBJECT);
                        //Log.e("Adesh",c.getString(3));
                        //mSubjects[i] = ;
                        mSubjects[i]=c.getString(index);
                        //int index2=c.getColumnIndex(DBAdapterM.ATTENDANCE);
                        //mAttendances[i]=c.getString(index2);
                        int index3=c.getColumnIndex(DBAdapter.TEACHER);
                        mTeachers[i]=c.getString(index3);
                        //Log.e("ss",mSubjects[i]);
                       // int index4=c.getColumnIndex(DBAdapterF.ATTENDANCE);
                       // mAttendances[i]=c.getString(index4);

                        int index5=c.getColumnIndex(DBAdapter.TEACHER);
                        mTeachers[i]=c.getString(index5);

                        int index6=c.getColumnIndex(DBAdapter.ASSIGNMENT);
                        mAssignments[i]=false;

                        int index7=c.getColumnIndex(DBAdapter.START_TIME);
                        double a=Double.parseDouble(c.getString(index7));
                        int x=(int)a;
                        a=a-x;
                        a=a*60;
                        a=(double)Math.round(a * 100)/100;
                        int y=(int)a;
                        if(y<10)
                            mTimings[i]=Integer.toString(x)+":0"+Integer.toString(y);
                        else
                            mTimings[i]=Integer.toString(x)+":"+Integer.toString(y);

                        int index8=c.getColumnIndex(DBAdapter.END_TIME);

                        a=Double.parseDouble(c.getString(index8));
                        x=(int)a;
                        a=a-x;
                        a=a*60;
                        a=(double)Math.round(a * 100)/100;
                        y=(int)a;
                        if(y<10)
                            mTimings[i]=mTimings[i]+"-"+Integer.toString(x)+":0"+Integer.toString(y);
                        else
                            mTimings[i]=mTimings[i]+"-"+Integer.toString(x)+":"+Integer.toString(y);

                        i++;

                    } while (c.moveToNext());
                }
                db.close();
                break;

            default:

                mSubjects = new String[]{};
                mAssignments = new boolean[]{};
                mAttendances = new String[]{};
                mTeachers = new String[]{};
                break;

        }
        db.close();


        list = new TimeTableArrayList(mSubjects, mAssignments, mAttendances, mTeachers,mTimings);
        for(int i=0;i<10;i++)
        {

            mSubjects[i]=null;
                  // mAssignments[i]=null;
            mAttendances[i]=null;
            mTeachers[i]=null;
            mTimings[i]=null;


        }
        // mSubjects=null;
    }


}



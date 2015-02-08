package com.pyrospiral.android.tabbedtimetable;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by kaano8 on 22/1/15.
 */
public class AttendanceFragment extends Fragment {

    //String subname
    String[] subName=new String[100];
    String[] teacherName=new String[100];
    String[] timings=new String[100];


    public RecyclerView mrecyclerView;
    private AttendanceAdapter mAdapter;
    List<Info> data = new ArrayList<>();

    public Button all_P,all_A,all_C;
    public ImageView cancel;

    public AttendanceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int i,index1,index2,index3,index4,index9,index10,index11;
        double day_get,done;
        int flag;

        switch (day) {
            case Calendar.MONDAY:

                i = 0;
                final DBAdapterM dbm = new DBAdapterM(getActivity());
                dbm.open();
                Cursor c = dbm.getAllContacts();
                if (c.moveToFirst()) {
                    do {
                        flag = 0;
                        index9 = c.getColumnIndex(DBAdapterM.DAY);
                        index10 = c.getColumnIndex(DBAdapterM.DONE);
                        day_get = c.getDouble(index9);
                        index11 = c.getColumnIndex(DBAdapterM.ROW_ID);
                        done = c.getDouble(index10);
                        int x = calendar.get(Calendar.DATE);
                        if (x == day_get) {
                            //Toast.makeText(getActivity(),"YOLO",Toast.LENGTH_SHORT).show();
                            if (done == 1) {
                                i++;
                                flag = 1;
                                continue;
                            }

                        } else {
                            dbm.updateContact(c.getInt(index11), x,0);
                        }
                    } while (c.moveToNext());
                }
                i=0;
                c=dbm.getAllContacts();
                if(c.moveToFirst() ){
                    do{
                        int x;

                        if(c.getInt(c.getColumnIndex(DBAdapterM.DONE))==1)
                            continue;

                        index9 = c.getColumnIndex(DBAdapterM.DAY);
                        //Toast.makeText(getActivity(), "DAY IS " + c.getInt(index9) + " DAY OF MONTH is" + calendar.get(Calendar.DATE) + "", Toast.LENGTH_SHORT).show();
                        index1 = c.getColumnIndex(DBAdapterM.SUBJECT);
                        subName[i] = c.getString(index1);
                        index2 = c.getColumnIndex(DBAdapterM.TEACHER);
                        teacherName[i] = c.getString(index2);

                        index3 = c.getColumnIndex(DBAdapterM.START_TIME);
                        double a = Double.parseDouble(c.getString(index3));
                        x = (int) a;
                        a = a - x;
                        a = a * 60;
                        a = (double) Math.round(a * 100) / 100;
                        int y = (int) a;
                        if (y < 10)
                            timings[i] = Integer.toString(x) + ":0" + Integer.toString(y);
                        else
                            timings[i] = Integer.toString(x) + ":" + Integer.toString(y);

                        index4 = c.getColumnIndex(DBAdapterM.END_TIME);

                        a = Double.parseDouble(c.getString(index4));
                        x = (int) a;
                        a = a - x;
                        a = a * 60;
                        a = (double) Math.round(a * 100) / 100;
                        y = (int) a;
                        if (y < 10)
                            timings[i] = timings[i] + "-" + Integer.toString(x) + ":0" + Integer.toString(y);
                        else
                            timings[i] = timings[i] + "-" + Integer.toString(x) + ":" + Integer.toString(y);

                        i++;


                        dbm.close();
                    } while (c.moveToNext());
                }


                        break;

                        case Calendar.TUESDAY:


                            i = 0;
                            final DBAdapterTu dbtu = new DBAdapterTu(getActivity());
                            dbtu.open();
                             c = dbtu.getAllContacts();
                            if (c.moveToFirst()) {
                                do {
                                    flag = 0;
                                    index9 = c.getColumnIndex(DBAdapterTu.DAY);
                                    index10 = c.getColumnIndex(DBAdapterTu.DONE);
                                    day_get = c.getDouble(index9);
                                    index11 = c.getColumnIndex(DBAdapterTu.ROW_ID);
                                    done = c.getDouble(index10);
                                    int x = calendar.get(Calendar.DATE);
                                    if (x == day_get) {
                                        //Toast.makeText(getActivity(),"YOLO",Toast.LENGTH_SHORT).show();
                                        if (done == 1) {
                                            i++;
                                            flag = 1;
                                            continue;
                                        }

                                    } else {
                                        dbtu.updateContact(c.getInt(index11),x,0);
                                    }
                                } while (c.moveToNext());
                            }
                            i=0;
                            c=dbtu.getAllContacts();
                            if(c.moveToFirst() ){
                                do{
                                    int x;

                                    if(c.getInt(c.getColumnIndex(DBAdapterTu.DONE))==1)
                                        continue;

                                    index9 = c.getColumnIndex(DBAdapterTu.DAY);
                                    //Toast.makeText(getActivity(), "DAY IS " + c.getInt(index9) + " DAY OF MONTH is" + calendar.get(Calendar.DATE) + "", Toast.LENGTH_SHORT).show();
                                    index1 = c.getColumnIndex(DBAdapterTu.SUBJECT);
                                    subName[i] = c.getString(index1);
                                    index2 = c.getColumnIndex(DBAdapterTu.TEACHER);
                                    teacherName[i] = c.getString(index2);

                                    index3 = c.getColumnIndex(DBAdapterTu.START_TIME);
                                    double a = Double.parseDouble(c.getString(index3));
                                    x = (int) a;
                                    a = a - x;
                                    a = a * 60;
                                    a = (double) Math.round(a * 100) / 100;
                                    int y = (int) a;
                                    if (y < 10)
                                        timings[i] = Integer.toString(x) + ":0" + Integer.toString(y);
                                    else
                                        timings[i] = Integer.toString(x) + ":" + Integer.toString(y);

                                    index4 = c.getColumnIndex(DBAdapterTu.END_TIME);

                                    a = Double.parseDouble(c.getString(index4));
                                    x = (int) a;
                                    a = a - x;
                                    a = a * 60;
                                    a = (double) Math.round(a * 100) / 100;
                                    y = (int) a;
                                    if (y < 10)
                                        timings[i] = timings[i] + "-" + Integer.toString(x) + ":0" + Integer.toString(y);
                                    else
                                        timings[i] = timings[i] + "-" + Integer.toString(x) + ":" + Integer.toString(y);

                                    i++;


                                    dbtu.close();
                                } while (c.moveToNext());
                            }


                            break;

                        case Calendar.WEDNESDAY:

                            i = 0;
                            final DBAdapterW dbw = new DBAdapterW(getActivity());
                            dbw.open();
                            c = dbw.getAllContacts();
                            if (c.moveToFirst()) {
                                do {
                                    flag = 0;
                                    index9 = c.getColumnIndex(DBAdapterW.DAY);
                                    index10 = c.getColumnIndex(DBAdapterW.DONE);
                                    day_get = c.getDouble(index9);
                                    index11 = c.getColumnIndex(DBAdapterW.ROW_ID);
                                    done = c.getDouble(index10);
                                    int x = calendar.get(Calendar.DATE);
                                    if (x == day_get) {
                                        //Toast.makeText(getActivity(),"YOLO",Toast.LENGTH_SHORT).show();
                                        if (done == 1) {
                                            i++;
                                            flag = 1;
                                            continue;
                                        }

                                    } else {
                                        dbw.updateContact(c.getInt(index11), x,0);
                                    }
                                } while (c.moveToNext());
                            }
                            i=0;
                            c=dbw.getAllContacts();
                            if(c.moveToFirst() ){
                                do{
                                    int x;

                                    if(c.getInt(c.getColumnIndex(DBAdapterW.DONE))==1)
                                        continue;

                                    index9 = c.getColumnIndex(DBAdapterW.DAY);
                                    //Toast.makeText(getActivity(), "DAY IS " + c.getInt(index9) + " DAY OF MONTH is" + calendar.get(Calendar.DATE) + "", Toast.LENGTH_SHORT).show();
                                    index1 = c.getColumnIndex(DBAdapterW.SUBJECT);
                                    subName[i] = c.getString(index1);
                                    index2 = c.getColumnIndex(DBAdapterW.TEACHER);
                                    teacherName[i] = c.getString(index2);

                                    index3 = c.getColumnIndex(DBAdapterW.START_TIME);
                                    double a = Double.parseDouble(c.getString(index3));
                                    x = (int) a;
                                    a = a - x;
                                    a = a * 60;
                                    a = (double) Math.round(a * 100) / 100;
                                    int y = (int) a;
                                    if (y < 10)
                                        timings[i] = Integer.toString(x) + ":0" + Integer.toString(y);
                                    else
                                        timings[i] = Integer.toString(x) + ":" + Integer.toString(y);

                                    index4 = c.getColumnIndex(DBAdapterW.END_TIME);

                                    a = Double.parseDouble(c.getString(index4));
                                    x = (int) a;
                                    a = a - x;
                                    a = a * 60;
                                    a = (double) Math.round(a * 100) / 100;
                                    y = (int) a;
                                    if (y < 10)
                                        timings[i] = timings[i] + "-" + Integer.toString(x) + ":0" + Integer.toString(y);
                                    else
                                        timings[i] = timings[i] + "-" + Integer.toString(x) + ":" + Integer.toString(y);

                                    i++;


                                    dbw.close();
                                } while (c.moveToNext());
                            }


                            break;


                        case Calendar.THURSDAY:


                            i = 0;
                            final DBAdapterTh dbth = new DBAdapterTh(getActivity());
                            dbth.open();
                            c = dbth.getAllContacts();
                            if (c.moveToFirst()) {
                                do {
                                    flag = 0;
                                    index9 = c.getColumnIndex(DBAdapterTh.DAY);
                                    index10 = c.getColumnIndex(DBAdapterTh.DONE);
                                    day_get = c.getDouble(index9);
                                    index11 = c.getColumnIndex(DBAdapterTh.ROW_ID);
                                    done = c.getDouble(index10);
                                    int x = calendar.get(Calendar.DATE);
                                    if (x == day_get) {
                                        //Toast.makeText(getActivity(),"YOLO",Toast.LENGTH_SHORT).show();
                                        if (done == 1) {
                                            i++;
                                            flag = 1;
                                            continue;
                                        }

                                    } else {
                                        dbth.updateContact(c.getInt(index11), x,0);
                                    }
                                } while (c.moveToNext());
                            }
                            i=0;
                            c=dbth.getAllContacts();
                            if(c.moveToFirst() ){
                                do{
                                    int x;

                                    if(c.getInt(c.getColumnIndex(DBAdapterTh.DONE))==1)
                                        continue;

                                    index9 = c.getColumnIndex(DBAdapterTh.DAY);
                                    //Toast.makeText(getActivity(), "DAY IS " + c.getInt(index9) + " DAY OF MONTH is" + calendar.get(Calendar.DATE) + "", Toast.LENGTH_SHORT).show();
                                    index1 = c.getColumnIndex(DBAdapterTh.SUBJECT);
                                    subName[i] = c.getString(index1);
                                    index2 = c.getColumnIndex(DBAdapterTh.TEACHER);
                                    teacherName[i] = c.getString(index2);

                                    index3 = c.getColumnIndex(DBAdapterTh.START_TIME);
                                    double a = Double.parseDouble(c.getString(index3));
                                    x = (int) a;
                                    a = a - x;
                                    a = a * 60;
                                    a = (double) Math.round(a * 100) / 100;
                                    int y = (int) a;
                                    if (y < 10)
                                        timings[i] = Integer.toString(x) + ":0" + Integer.toString(y);
                                    else
                                        timings[i] = Integer.toString(x) + ":" + Integer.toString(y);

                                    index4 = c.getColumnIndex(DBAdapterTh.END_TIME);

                                    a = Double.parseDouble(c.getString(index4));
                                    x = (int) a;
                                    a = a - x;
                                    a = a * 60;
                                    a = (double) Math.round(a * 100) / 100;
                                    y = (int) a;
                                    if (y < 10)
                                        timings[i] = timings[i] + "-" + Integer.toString(x) + ":0" + Integer.toString(y);
                                    else
                                        timings[i] = timings[i] + "-" + Integer.toString(x) + ":" + Integer.toString(y);

                                    i++;


                                    dbth.close();
                                } while (c.moveToNext());
                            }


                            break;

                        case Calendar.FRIDAY:

                            i = 0;
                            final DBAdapterF dbf = new DBAdapterF(getActivity());
                            dbf.open();
                            c = dbf.getAllContacts();
                            if (c.moveToFirst()) {
                                do {
                                    flag = 0;
                                    index9 = c.getColumnIndex(DBAdapterF.DAY);
                                    index10 = c.getColumnIndex(DBAdapterF.DONE);
                                    day_get = c.getDouble(index9);
                                    index11 = c.getColumnIndex(DBAdapterF.ROW_ID);
                                    done = c.getDouble(index10);
                                    int x = calendar.get(Calendar.DATE);
                                    if (x == day_get) {
                                        //Toast.makeText(getActivity(),"YOLO",Toast.LENGTH_SHORT).show();
                                        if (done == 1) {
                                            i++;
                                            flag = 1;
                                            continue;
                                        }

                                    } else {
                                        dbf.updateContact(c.getInt(index11), x,0);
                                    }
                                } while (c.moveToNext());
                            }
                            i=0;
                            c=dbf.getAllContacts();
                            if(c.moveToFirst() ){
                                do{
                                    int x;

                                    if(c.getInt(c.getColumnIndex(DBAdapterF.DONE))==1)
                                        continue;

                                    index9 = c.getColumnIndex(DBAdapterF.DAY);
                                    //Toast.makeText(getActivity(), "DAY IS " + c.getInt(index9) + " DAY OF MONTH is" + calendar.get(Calendar.DATE) + "", Toast.LENGTH_SHORT).show();
                                    index1 = c.getColumnIndex(DBAdapterF.SUBJECT);
                                    subName[i] = c.getString(index1);
                                    index2 = c.getColumnIndex(DBAdapterF.TEACHER);
                                    teacherName[i] = c.getString(index2);

                                    index3 = c.getColumnIndex(DBAdapterF.START_TIME);
                                    double a = Double.parseDouble(c.getString(index3));
                                    x = (int) a;
                                    a = a - x;
                                    a = a * 60;
                                    a = (double) Math.round(a * 100) / 100;
                                    int y = (int) a;
                                    if (y < 10)
                                        timings[i] = Integer.toString(x) + ":0" + Integer.toString(y);
                                    else
                                        timings[i] = Integer.toString(x) + ":" + Integer.toString(y);

                                    index4 = c.getColumnIndex(DBAdapterF.END_TIME);

                                    a = Double.parseDouble(c.getString(index4));
                                    x = (int) a;
                                    a = a - x;
                                    a = a * 60;
                                    a = (double) Math.round(a * 100) / 100;
                                    y = (int) a;
                                    if (y < 10)
                                        timings[i] = timings[i] + "-" + Integer.toString(x) + ":0" + Integer.toString(y);
                                    else
                                        timings[i] = timings[i] + "-" + Integer.toString(x) + ":" + Integer.toString(y);

                                    i++;


                                    dbf.close();
                                } while (c.moveToNext());
                            }


                            break;
                        default:

                            break;



        }


        // setting up array list
        data = new ArrayList<>();

        //String array for Subject Name
       // final String[] subName = {};

        //String array for Teacher Name
       // String[] teacherName = {};


        //String to get timings
       // String[] timings = {"8:30-9:25","9:25-10:20","10:30-11:25","11:25-12:20","2:00-2:55","2:55-3:50"};

        //Inserting some fake data
        for(i=0;i<subName.length && i<teacherName.length;i++)
        {
            if(subName[i]==null && teacherName[i]==null)
                continue;

            Info current = new Info();
            current.subName = subName[i%subName.length];
            current.teacherName = teacherName[i%teacherName.length];
            current.timings = timings[i%timings.length];
            data.add(current);
        }



        //setting recycler view
        mrecyclerView = (RecyclerView) rootView.findViewById(R.id.list);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new AttendanceAdapter(getActivity(),data);
        mrecyclerView.setAdapter(mAdapter);


        //Set out layout Manager
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.supportsPredictiveItemAnimations();
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mrecyclerView.setLayoutManager(llm);

        if(mAdapter.getItemCount()==0)
        {
            Toast.makeText(getActivity(), "Thanks for your Response ", Toast.LENGTH_SHORT).show();
        }

        //Function for swipe to dismiss
        applyToRecycler();

        all_P = (Button) rootView.findViewById(R.id.all_present);
        all_A = (Button) rootView.findViewById(R.id.all_absent);
        cancel = (ImageView) rootView.findViewById(R.id.cancelled);
        all_C = (Button) rootView.findViewById(R.id.all_cancelled);




        all_P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"All Present", Toast.LENGTH_SHORT).show();

                    Calendar calendar = Calendar.getInstance();
                    int day = calendar.get(Calendar.DAY_OF_WEEK);

                    Cursor c1;


                    switch (day) {
                        case Calendar.MONDAY:

                            final DBAdapterM dbm=new DBAdapterM(getActivity());
                            dbm.open();

                            c1=dbm.getAllContacts();

                            if(c1.moveToFirst()) {
                                do {
                                    if(c1.getInt(c1.getColumnIndex(DBAdapterM.DONE))==1)
                                        continue;
                                    final DBAttendence dbA = new DBAttendence(getActivity());
                                    dbA.open();

                                    Cursor c2 = dbA.getContact(c1.getString(c1.getColumnIndex(DBAdapterM.SUBJECT)));
                                    int present, absent, total, row;
                                    if (c2.moveToFirst()) {
                                        String subname=c1.getString(c1.getColumnIndex(DBAdapterM.SUBJECT));

                                        dbm.updateSubject(subname);

                                        present = c2.getInt(c2.getColumnIndex(DBAttendence.PRESENT));
                                        absent = c2.getInt(c2.getColumnIndex(DBAttendence.ABSENT));
                                        total = c2.getInt(c2.getColumnIndex(DBAttendence.TOTAL));
                                        row = c2.getInt(c2.getColumnIndex(DBAttendence.ROW_ID));
                                        dbA.updateContact(row, present+1, absent, total + 1);
                                    }

                                    }
                                    while (c1.moveToNext()) ;
                                }


                            dbm.close();


                            break;
                        case Calendar.TUESDAY:
                            final DBAdapterTu dbtu=new DBAdapterTu(getActivity());
                            dbtu.open();
                            // Cursor c2=dbm.getPosition(subName[position]);
                            c1=dbtu.getAllContacts();
                            if(c1.moveToFirst()) {
                                do {
                                    if(c1.getInt(c1.getColumnIndex(DBAdapterTu.DONE))==1)
                                        continue;
                                    final DBAttendence dbA = new DBAttendence(getActivity());
                                    dbA.open();

                                    Cursor c2 = dbA.getContact(c1.getString(c1.getColumnIndex(DBAdapterTu.SUBJECT)));
                                    int present, absent, total, row;
                                    if (c2.moveToFirst()) {
                                        String subname=c1.getString(c1.getColumnIndex(DBAdapterM.SUBJECT));

                                        dbtu.updateSubject(subname);
                                        present = c2.getInt(c2.getColumnIndex(DBAttendence.PRESENT));
                                        absent = c2.getInt(c2.getColumnIndex(DBAttendence.ABSENT));
                                        total = c2.getInt(c2.getColumnIndex(DBAttendence.TOTAL));
                                        row = c2.getInt(c2.getColumnIndex(DBAttendence.ROW_ID));
                                        dbA.updateContact(row, present+1, absent, total + 1);
                                    }

                                }
                                while (c1.moveToNext()) ;
                            }
                            dbtu.close();
                            break;
                        case Calendar.WEDNESDAY:
                            final DBAdapterW dbw=new DBAdapterW(getActivity());
                            dbw.open();
                            // Cursor c2=dbm.getPosition(subName[position]);
                            c1=dbw.getAllContacts();

                            if(c1.moveToFirst()) {
                                do {
                                    if(c1.getInt(c1.getColumnIndex(DBAdapterW.DONE))==1)
                                        continue;
                                    final DBAttendence dbA = new DBAttendence(getActivity());
                                    dbA.open();
                                    Cursor c2 = dbA.getContact(c1.getString(c1.getColumnIndex(DBAdapterW.SUBJECT)));
                                    int present, absent, total, row;
                                    String subname=c1.getString(c1.getColumnIndex(DBAdapterM.SUBJECT));
                                    dbw.updateSubject(subname);
                                    if (c2.moveToFirst()) {
                                        present = c2.getInt(c2.getColumnIndex(DBAttendence.PRESENT));
                                        absent = c2.getInt(c2.getColumnIndex(DBAttendence.ABSENT));
                                        total = c2.getInt(c2.getColumnIndex(DBAttendence.TOTAL));
                                        row = c2.getInt(c2.getColumnIndex(DBAttendence.ROW_ID));
                                        dbA.updateContact(row, present+1, absent, total + 1);
                                    }

                                }
                                while (c1.moveToNext()) ;
                            }
                            dbw.close();
                            break;
                        case Calendar.THURSDAY:
                            final DBAdapterTh dbth=new DBAdapterTh(getActivity());
                            dbth.open();
                            // Cursor c2=dbm.getPosition(subName[position]);
                            c1=dbth.getAllContacts();

                            if(c1.moveToFirst()) {
                                do {
                                    if(c1.getInt(c1.getColumnIndex(DBAdapterTh.DONE))==1)
                                        continue;

                                    String subname=c1.getString(c1.getColumnIndex(DBAdapterM.SUBJECT));
                                     dbth.updateSubject(subname);
                                    final DBAttendence dbA = new DBAttendence(getActivity());
                                    dbA.open();
                                    Cursor c2 = dbA.getContact(c1.getString(c1.getColumnIndex(DBAdapterTh.SUBJECT)));
                                    int present, absent, total, row;
                                    if (c2.moveToFirst()) {
                                        present = c2.getInt(c2.getColumnIndex(DBAttendence.PRESENT));
                                        absent = c2.getInt(c2.getColumnIndex(DBAttendence.ABSENT));
                                        total = c2.getInt(c2.getColumnIndex(DBAttendence.TOTAL));
                                        row = c2.getInt(c2.getColumnIndex(DBAttendence.ROW_ID));
                                        dbA.updateContact(row, present+1, absent, total + 1);
                                    }

                                }
                                while (c1.moveToNext()) ;
                            }
                            dbth.close();
                            break;
                        case Calendar.FRIDAY:
                            final DBAdapterF dbf=new DBAdapterF(getActivity());
                            dbf.open();
                            // Cursor c2=dbm.getPosition(subName[position]);
                            c1=dbf.getAllContacts();

                            if(c1.moveToFirst()) {
                                do {
                                    if(c1.getInt(c1.getColumnIndex(DBAdapterF.DONE))==1)
                                        continue;
                                    String subname=c1.getString(c1.getColumnIndex(DBAdapterM.SUBJECT));

                                    dbf.updateSubject(subname);
                                    final DBAttendence dbA = new DBAttendence(getActivity());
                                    dbA.open();
                                    Cursor c2 = dbA.getContact(c1.getString(c1.getColumnIndex(DBAdapterF.SUBJECT)));
                                    int present, absent, total, row;
                                    if (c2.moveToFirst()) {
                                        present = c2.getInt(c2.getColumnIndex(DBAttendence.PRESENT));
                                        absent = c2.getInt(c2.getColumnIndex(DBAttendence.ABSENT));
                                        total = c2.getInt(c2.getColumnIndex(DBAttendence.TOTAL));
                                        row = c2.getInt(c2.getColumnIndex(DBAttendence.ROW_ID));
                                        dbA.updateContact(row, present+1, absent, total + 1);
                                    }

                                }
                                while (c1.moveToNext()) ;
                            }
                            dbf.close();
                            break;



                    }
                }


        });

        all_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Calendar calendar = Calendar.getInstance();
                    int day = calendar.get(Calendar.DAY_OF_WEEK);

                    Cursor c1;

                    Toast.makeText(getActivity(),"All Absent", Toast.LENGTH_SHORT).show();
                    switch (day) {
                        case Calendar.MONDAY:
                            final DBAdapterM dbm=new DBAdapterM(getActivity());
                            dbm.open();

                            c1=dbm.getAllContacts();

                            if(c1.moveToFirst()) {
                                do {
                                    if(c1.getInt(c1.getColumnIndex(DBAdapterM.DONE))==1)
                                        continue;
                                    final DBAttendence dbA = new DBAttendence(getActivity());
                                    dbA.open();

                                    Cursor c2 = dbA.getContact(c1.getString(c1.getColumnIndex(DBAdapterM.SUBJECT)));
                                    int present, absent, total, row;
                                    if (c2.moveToFirst()) {
                                        String subname=c1.getString(c1.getColumnIndex(DBAdapterM.SUBJECT));

                                        dbm.updateSubject(subname);

                                        present = c2.getInt(c2.getColumnIndex(DBAttendence.PRESENT));
                                        absent = c2.getInt(c2.getColumnIndex(DBAttendence.ABSENT));
                                        total = c2.getInt(c2.getColumnIndex(DBAttendence.TOTAL));
                                        row = c2.getInt(c2.getColumnIndex(DBAttendence.ROW_ID));
                                        dbA.updateContact(row, present, absent+1, total + 1);
                                    }

                                }
                                while (c1.moveToNext()) ;
                            }


                            dbm.close();


                            break;
                        case Calendar.TUESDAY:
                            final DBAdapterTu dbtu=new DBAdapterTu(getActivity());
                            dbtu.open();
                            // Cursor c2=dbm.getPosition(subName[position]);
                            c1=dbtu.getAllContacts();
                            if(c1.moveToFirst()) {
                                do {
                                    if(c1.getInt(c1.getColumnIndex(DBAdapterTu.DONE))==1)
                                        continue;
                                    final DBAttendence dbA = new DBAttendence(getActivity());
                                    dbA.open();

                                    Cursor c2 = dbA.getContact(c1.getString(c1.getColumnIndex(DBAdapterTu.SUBJECT)));
                                    int present, absent, total, row;
                                    if (c2.moveToFirst()) {
                                        String subname=c1.getString(c1.getColumnIndex(DBAdapterM.SUBJECT));

                                        dbtu.updateSubject(subname);
                                        present = c2.getInt(c2.getColumnIndex(DBAttendence.PRESENT));
                                        absent = c2.getInt(c2.getColumnIndex(DBAttendence.ABSENT));
                                        total = c2.getInt(c2.getColumnIndex(DBAttendence.TOTAL));
                                        row = c2.getInt(c2.getColumnIndex(DBAttendence.ROW_ID));
                                        dbA.updateContact(row, present, absent+1, total + 1);
                                    }

                                }
                                while (c1.moveToNext()) ;
                            }
                            dbtu.close();
                            break;
                        case Calendar.WEDNESDAY:
                            final DBAdapterW dbw=new DBAdapterW(getActivity());
                            dbw.open();
                            // Cursor c2=dbm.getPosition(subName[position]);
                            c1=dbw.getAllContacts();

                            if(c1.moveToFirst()) {
                                do {
                                    if(c1.getInt(c1.getColumnIndex(DBAdapterW.DONE))==1)
                                        continue;
                                    final DBAttendence dbA = new DBAttendence(getActivity());
                                    dbA.open();
                                    Cursor c2 = dbA.getContact(c1.getString(c1.getColumnIndex(DBAdapterW.SUBJECT)));
                                    int present, absent, total, row;
                                    String subname=c1.getString(c1.getColumnIndex(DBAdapterM.SUBJECT));
                                    dbw.updateSubject(subname);
                                    if (c2.moveToFirst()) {
                                        present = c2.getInt(c2.getColumnIndex(DBAttendence.PRESENT));
                                        absent = c2.getInt(c2.getColumnIndex(DBAttendence.ABSENT));
                                        total = c2.getInt(c2.getColumnIndex(DBAttendence.TOTAL));
                                        row = c2.getInt(c2.getColumnIndex(DBAttendence.ROW_ID));
                                        dbA.updateContact(row, present, absent+1, total + 1);
                                    }

                                }
                                while (c1.moveToNext()) ;
                            }
                            dbw.close();
                            break;
                        case Calendar.THURSDAY:
                            final DBAdapterTh dbth=new DBAdapterTh(getActivity());
                            dbth.open();
                            // Cursor c2=dbm.getPosition(subName[position]);
                            c1=dbth.getAllContacts();

                            if(c1.moveToFirst()) {
                                do {
                                    if(c1.getInt(c1.getColumnIndex(DBAdapterTh.DONE))==1)
                                        continue;

                                    String subname=c1.getString(c1.getColumnIndex(DBAdapterM.SUBJECT));
                                    dbth.updateSubject(subname);
                                    final DBAttendence dbA = new DBAttendence(getActivity());
                                    dbA.open();
                                    Cursor c2 = dbA.getContact(c1.getString(c1.getColumnIndex(DBAdapterTh.SUBJECT)));
                                    int present, absent, total, row;
                                    if (c2.moveToFirst()) {
                                        present = c2.getInt(c2.getColumnIndex(DBAttendence.PRESENT));
                                        absent = c2.getInt(c2.getColumnIndex(DBAttendence.ABSENT));
                                        total = c2.getInt(c2.getColumnIndex(DBAttendence.TOTAL));
                                        row = c2.getInt(c2.getColumnIndex(DBAttendence.ROW_ID));
                                        dbA.updateContact(row, present, absent+1, total + 1);
                                    }

                                }
                                while (c1.moveToNext()) ;
                            }
                            dbth.close();
                            break;
                        case Calendar.FRIDAY:
                            final DBAdapterF dbf=new DBAdapterF(getActivity());
                            dbf.open();
                            // Cursor c2=dbm.getPosition(subName[position]);
                            c1=dbf.getAllContacts();

                            if(c1.moveToFirst()) {
                                do {
                                    if(c1.getInt(c1.getColumnIndex(DBAdapterF.DONE))==1)
                                        continue;
                                    String subname=c1.getString(c1.getColumnIndex(DBAdapterM.SUBJECT));

                                    dbf.updateSubject(subname);
                                    final DBAttendence dbA = new DBAttendence(getActivity());
                                    dbA.open();
                                    Cursor c2 = dbA.getContact(c1.getString(c1.getColumnIndex(DBAdapterF.SUBJECT)));
                                    int present, absent, total, row;
                                    if (c2.moveToFirst()) {
                                        present = c2.getInt(c2.getColumnIndex(DBAttendence.PRESENT));
                                        absent = c2.getInt(c2.getColumnIndex(DBAttendence.ABSENT));
                                        total = c2.getInt(c2.getColumnIndex(DBAttendence.TOTAL));
                                        row = c2.getInt(c2.getColumnIndex(DBAttendence.ROW_ID));
                                        dbA.updateContact(row, present, absent+1, total + 1);
                                    }

                                }
                                while (c1.moveToNext()) ;
                            }
                            dbf.close();
                            break;



                    }
                }


        });

        all_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
            for (int i = 0; i < subName.length; i++) {
                Toast.makeText(getActivity(), "All Cancelled", Toast.LENGTH_SHORT).show();

            }
        }
        });


        return rootView;
    }


    private void applyToRecycler(){

        SwipeDismissRecyclerViewTouchListener2 touchListener =
                new SwipeDismissRecyclerViewTouchListener2(
                        mrecyclerView,
                        new SwipeDismissRecyclerViewTouchListener2.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    Toast.makeText(getActivity()," "+subName[position]+"", Toast.LENGTH_SHORT).show();
                                    Calendar calendar = Calendar.getInstance();
                                    int day = calendar.get(Calendar.DAY_OF_WEEK);
                                    switch (day)
                                            {
                                                case Calendar.MONDAY:
                                                    final DBAdapterM dbm=new DBAdapterM(getActivity());
                                                    dbm.open();
                                                   // Cursor c2=dbm.getPosition(subName[position]);
                                                    dbm.updateSubject(subName[position]);
                                                    dbm.close();
                                                    break;
                                                case Calendar.TUESDAY:
                                                    final DBAdapterTu dbtu=new DBAdapterTu(getActivity());
                                                    dbtu.open();
                                                    // Cursor c2=dbm.getPosition(subName[position]);
                                                    dbtu.updateSubject(subName[position]);
                                                    dbtu.close();
                                                    break;
                                                case Calendar.WEDNESDAY:
                                                    final DBAdapterW dbw=new DBAdapterW(getActivity());
                                                    dbw.open();
                                                    // Cursor c2=dbm.getPosition(subName[position]);
                                                    dbw.updateSubject(subName[position]);
                                                    dbw.close();
                                                    break;
                                                case Calendar.THURSDAY:
                                                    final DBAdapterTh dbth=new DBAdapterTh(getActivity());
                                                    dbth.open();
                                                    // Cursor c2=dbm.getPosition(subName[position]);
                                                    dbth.updateSubject(subName[position]);
                                                    dbth.close();
                                                    break;
                                                case Calendar.FRIDAY:
                                                    final DBAdapterF dbf=new DBAdapterF(getActivity());
                                                    dbf.open();
                                                    // Cursor c2=dbm.getPosition(subName[position]);
                                                    dbf.updateSubject(subName[position]);
                                                    dbf.close();
                                                    break;
                                                default:



                                            }


                                    final DBAttendence dbA=new DBAttendence(getActivity());
                                    dbA.open();
                                    Cursor c2=dbA.getContact(subName[position]);
                                    int present,absent,total,row;
                                    if(c2.moveToFirst()) {
                                        present = c2.getInt(c2.getColumnIndex(DBAttendence.PRESENT));
                                        absent = c2.getInt(c2.getColumnIndex(DBAttendence.ABSENT));
                                        total = c2.getInt(c2.getColumnIndex(DBAttendence.TOTAL));
                                        row = c2.getInt(c2.getColumnIndex(DBAttendence.ROW_ID));
                                        dbA.updateContact(row, present, absent+1, total+1);
                                    }











                                      dbA.close();




                                    for(int x=position;subName[x+1]!=null;x++)
                                    {
                                        subName[x]=subName[x+1];
                                        teacherName[x]=teacherName[x+1];
                                        timings[x]=timings[x+1];
                                    }

                                    data.remove(position);
                                    Toast.makeText(getActivity(),"Swiped Left "+position+"", Toast.LENGTH_SHORT).show();





                                }
                                // do not call notifyItemRemoved for every item, it will cause gaps on deleting items
                                mAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    data.remove(position);
                                    Toast.makeText(getActivity(),"Swiped Right", Toast.LENGTH_SHORT).show();
                                    Calendar calendar = Calendar.getInstance();
                                    int day = calendar.get(Calendar.DAY_OF_WEEK);
                                    switch (day)
                                    {
                                        case Calendar.MONDAY:
                                            final DBAdapterM dbm=new DBAdapterM(getActivity());
                                            dbm.open();
                                            // Cursor c2=dbm.getPosition(subName[position]);
                                            dbm.updateSubject(subName[position]);
                                            dbm.close();
                                            break;
                                        case Calendar.TUESDAY:
                                            final DBAdapterTu dbtu=new DBAdapterTu(getActivity());
                                            dbtu.open();
                                            // Cursor c2=dbm.getPosition(subName[position]);
                                            dbtu.updateSubject(subName[position]);
                                            dbtu.close();
                                            break;
                                        case Calendar.WEDNESDAY:
                                            final DBAdapterW dbw=new DBAdapterW(getActivity());
                                            dbw.open();
                                            // Cursor c2=dbm.getPosition(subName[position]);
                                            dbw.updateSubject(subName[position]);
                                            dbw.close();
                                            break;
                                        case Calendar.THURSDAY:
                                            final DBAdapterTh dbth=new DBAdapterTh(getActivity());
                                            dbth.open();
                                            // Cursor c2=dbm.getPosition(subName[position]);
                                            dbth.updateSubject(subName[position]);
                                            dbth.close();
                                            break;
                                        case Calendar.FRIDAY:
                                            final DBAdapterF dbf=new DBAdapterF(getActivity());
                                            dbf.open();
                                            // Cursor c2=dbm.getPosition(subName[position]);
                                            dbf.updateSubject(subName[position]);
                                            dbf.close();
                                            break;
                                        default:



                                    }



                                    final DBAttendence dbA=new DBAttendence(getActivity());
                                    dbA.open();
                                    Cursor c2=dbA.getContact(subName[position]);
                                    int present,absent,total,row;
                                    if(c2.moveToFirst()) {
                                        present = c2.getInt(c2.getColumnIndex(DBAttendence.PRESENT));
                                        absent = c2.getInt(c2.getColumnIndex(DBAttendence.ABSENT));
                                        total = c2.getInt(c2.getColumnIndex(DBAttendence.TOTAL));
                                        row = c2.getInt(c2.getColumnIndex(DBAttendence.ROW_ID));
                                        dbA.updateContact(row, present+1, absent, total+1);
                                    }
                                    dbA.close();
                                    for(int x=position;subName[x+1]!=null;x++)
                                    {
                                        subName[x]=subName[x+1];
                                        teacherName[x]=teacherName[x+1];
                                        timings[x]=timings[x+1];
                                    }

                                }
                                // do not call notifyItemRemoved for every item, it will cause gaps on deleting items
                                mAdapter.notifyDataSetChanged();
                            }
                        });


        mrecyclerView.setOnTouchListener(touchListener);
        // Setting this scroll listener is required to ensure that during ListView scrolling,
        // we don't look for swipes.
        mrecyclerView.setOnScrollListener(touchListener.makeScrollListener());
        mrecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(),
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                    }
                }));
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }


    public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private OnItemClickListener mListener;

        GestureDetector mGestureDetector;

        public RecyclerItemClickListener(Context context, OnItemClickListener listener) {
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildPosition(childView));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        }
    }
}
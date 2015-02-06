package com.pyrospiral.android.tabbedtimetable;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by kaano8 on 25/1/15.
 */
public class DetailsFragment extends Fragment {

    public DetailsFragment() {
    }

    TextView subName, teacherName, timings, ca,cb;
    String check;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent intent = getActivity().getIntent();
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        if(intent != null && intent.hasExtra(Intent.EXTRA_TEXT))
        {
            check =intent.getStringExtra(Intent.EXTRA_TEXT);
        }

        //Toast.makeText(getActivity(),check,Toast.LENGTH_LONG).show();

        String subject="";
        String teacher="TEACHERS :";
        String timing="TIMINGS :";
        String timingss;
        String classAttended="TOTAL PRESENT: ";
        String classAbsent="TOTAL ABSENT :";







        subName = (TextView) rootView.findViewById(R.id.subName);
        teacherName = (TextView) rootView.findViewById(R.id.teacherName);
        timings = (TextView) rootView.findViewById(R.id.timings);
        ca = (TextView) rootView.findViewById(R.id.classesAttended);
        cb = (TextView) rootView.findViewById(R.id.classCanBunk);


        final DBAdapterM dbm=new DBAdapterM(getActivity());
        final DBAdapterTu dbtu=new DBAdapterTu(getActivity());
        final DBAdapterW dbw=new DBAdapterW(getActivity());
        final DBAdapterTh dbth=new DBAdapterTh(getActivity());
        final DBAdapterF dbf=new DBAdapterF(getActivity());
        final DBAttendence dba=new DBAttendence(getActivity());

        subject=check;




       Cursor c,c2;
        dbm.open();
        c=dbm.getPosition(check);

        if(c.moveToFirst())
        {
            do{
                //subject=subject+c.getString(c.getColumnIndex(DBAdapterM.SUBJECT))+"  ";
                teacher=teacher+c.getString(c.getColumnIndex(DBAdapterM.TEACHER))+"  ";
                int index7=c.getColumnIndex(DBAdapterM.START_TIME);
                double a=Double.parseDouble(c.getString(index7));
                int x=(int)a;
                a=a-x;
                a=a*60;
                a=(double)Math.round(a * 100)/100;
                int y=(int)a;
                if(y<10)
                   timingss=Integer.toString(x)+":0"+Integer.toString(y);
                else
                    timingss=Integer.toString(x)+":"+Integer.toString(y);

                int index8=c.getColumnIndex(DBAdapterM.END_TIME);

                a=Double.parseDouble(c.getString(index8));
                x=(int)a;
                a=a-x;
                a=a*60;
                a=(double)Math.round(a * 100)/100;
                y=(int)a;
                if(y<10)
                    timingss=timingss+"-"+Integer.toString(x)+":0"+Integer.toString(y);
                else
                    timingss=timingss+"-"+Integer.toString(x)+":"+Integer.toString(y);

                timing=timing+timingss+" ";







            }while(c.moveToNext());
        }
        dbm.close();

        dbtu.open();
        c=dbtu.getPosition(check);

        if(c.moveToFirst())
        {
            do{
                //subject=subject+c.getString(c.getColumnIndex(DBAdapterTu.SUBJECT))+"  ";
                teacher=teacher+c.getString(c.getColumnIndex(DBAdapterTu.TEACHER))+"  ";
                int index7=c.getColumnIndex(DBAdapterTu.START_TIME);
                double a=Double.parseDouble(c.getString(index7));
                int x=(int)a;
                a=a-x;
                a=a*60;
                a=(double)Math.round(a * 100)/100;
                int y=(int)a;
                if(y<10)
                    timingss=Integer.toString(x)+":0"+Integer.toString(y);
                else
                    timingss=Integer.toString(x)+":"+Integer.toString(y);

                int index8=c.getColumnIndex(DBAdapterTu.END_TIME);

                a=Double.parseDouble(c.getString(index8));
                x=(int)a;
                a=a-x;
                a=a*60;
                a=(double)Math.round(a * 100)/100;
                y=(int)a;
                if(y<10)
                    timingss=timingss+"-"+Integer.toString(x)+":0"+Integer.toString(y);
                else
                    timingss=timingss+"-"+Integer.toString(x)+":"+Integer.toString(y);

                timing=timing+timingss+" ";







            }while(c.moveToNext());
        }
        dbtu.close();



        dbw.open();
        c=dbw.getPosition(check);

        if(c.moveToFirst())
        {
            do{
               // subject=subject+c.getString(c.getColumnIndex(DBAdapterW.SUBJECT))+"  ";
                teacher=teacher+c.getString(c.getColumnIndex(DBAdapterW.TEACHER))+"  ";
                int index7=c.getColumnIndex(DBAdapterW.START_TIME);
                double a=Double.parseDouble(c.getString(index7));
                int x=(int)a;
                a=a-x;
                a=a*60;
                a=(double)Math.round(a * 100)/100;
                int y=(int)a;
                if(y<10)
                    timingss=Integer.toString(x)+":0"+Integer.toString(y);
                else
                    timingss=Integer.toString(x)+":"+Integer.toString(y);

                int index8=c.getColumnIndex(DBAdapterW.END_TIME);

                a=Double.parseDouble(c.getString(index8));
                x=(int)a;
                a=a-x;
                a=a*60;
                a=(double)Math.round(a * 100)/100;
                y=(int)a;
                if(y<10)
                    timingss=timingss+"-"+Integer.toString(x)+":0"+Integer.toString(y);
                else
                    timingss=timingss+"-"+Integer.toString(x)+":"+Integer.toString(y);

                timing=timing+timingss+" ";







            }while(c.moveToNext());
        }
        dbw.close();

        dbth.open();
        c=dbth.getPosition(check);

        if(c.moveToFirst())
        {
            do{
                //subject=subject+c.getString(c.getColumnIndex(DBAdapterTh.SUBJECT))+"  ";
                teacher=teacher+c.getString(c.getColumnIndex(DBAdapterTh.TEACHER))+"  ";
                int index7=c.getColumnIndex(DBAdapterTh.START_TIME);
                double a=Double.parseDouble(c.getString(index7));
                int x=(int)a;
                a=a-x;
                a=a*60;
                a=(double)Math.round(a * 100)/100;
                int y=(int)a;
                if(y<10)
                    timingss=Integer.toString(x)+":0"+Integer.toString(y);
                else
                    timingss=Integer.toString(x)+":"+Integer.toString(y);

                int index8=c.getColumnIndex(DBAdapterTh.END_TIME);

                a=Double.parseDouble(c.getString(index8));
                x=(int)a;
                a=a-x;
                a=a*60;
                a=(double)Math.round(a * 100)/100;
                y=(int)a;
                if(y<10)
                    timingss=timingss+"-"+Integer.toString(x)+":0"+Integer.toString(y);
                else
                    timingss=timingss+"-"+Integer.toString(x)+":"+Integer.toString(y);

                timing=timing+timingss+" ";







            }while(c.moveToNext());
        }
        dbth.close();

        dbf.open();
        c=dbf.getPosition(check);

        if(c.moveToFirst())
        {
            do{
                //subject=subject+c.getString(c.getColumnIndex(DBAdapterF.SUBJECT))+"  ";
                teacher=teacher+c.getString(c.getColumnIndex(DBAdapterF.TEACHER))+"  ";
                int index7=c.getColumnIndex(DBAdapterF.START_TIME);
                double a=Double.parseDouble(c.getString(index7));
                int x=(int)a;
                a=a-x;
                a=a*60;
                a=(double)Math.round(a * 100)/100;
                int y=(int)a;
                if(y<10)
                    timingss=Integer.toString(x)+":0"+Integer.toString(y);
                else
                    timingss=Integer.toString(x)+":"+Integer.toString(y);

                int index8=c.getColumnIndex(DBAdapterF.END_TIME);

                a=Double.parseDouble(c.getString(index8));
                x=(int)a;
                a=a-x;
                a=a*60;
                a=(double)Math.round(a * 100)/100;
                y=(int)a;
                if(y<10)
                    timingss=timingss+"-"+Integer.toString(x)+":0"+Integer.toString(y);
                else
                    timingss=timingss+"-"+Integer.toString(x)+":"+Integer.toString(y);

                timing=timing+timingss+" ";







            }while(c.moveToNext());
        }
        dbf.close();


        dba.open();
        c=dba.getContact(check);
        if(c.moveToFirst())
        {
            classAttended=classAttended+c.getString(c.getColumnIndex(DBAttendence.PRESENT));
            classAbsent=classAbsent+c.getString(c.getColumnIndex(DBAttendence.ABSENT));
        }

        dba.close();


        subName.setText(subject);
        teacherName.setText(teacher);
        timings.setText(timing);
        ca.setText(classAttended);
        cb.setText(classAbsent);


        return rootView;
    }
}
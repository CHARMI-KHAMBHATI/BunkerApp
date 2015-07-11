package com.pyrospiral.android.tabbedtimetable;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by kaano8 on 25/1/15.
 */
public class DetailsFragment extends Fragment {

    public DetailsFragment() {
    }

    TextView subName, teacherName, timings, ca,cb;
    String check;
    float density;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent intent = getActivity().getIntent();
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        if(intent != null && intent.hasExtra(Intent.EXTRA_TEXT))
        {
            check =intent.getStringExtra(Intent.EXTRA_TEXT);
        }

        density = this.getResources().getDisplayMetrics().density;
        //Toast.makeText(getActivity(),check,Toast.LENGTH_LONG).show();

        String subject="";
        String teacher="";
        String timing="";
        String timingss;
        String classAttended="Total Present: ";
        String classAbsent="Total Absent :";






        subName = (TextView) rootView.findViewById(R.id.subName);
        teacherName = (TextView) rootView.findViewById(R.id.teacherName);
        timings = (TextView) rootView.findViewById(R.id.timings);
        ca = (TextView) rootView.findViewById(R.id.classesAttended);
        cb = (TextView) rootView.findViewById(R.id.classCanBunk);


        final DBAdapter db=new DBAdapter(getActivity());

        final DBAttendence dba=new DBAttendence(getActivity());

        subject=check;




        Cursor c,c2;
        db.open();
        c=db.getPosition(check);

        if(c.moveToFirst())
        {
            do{
                //subject=subject+c.getString(c.getColumnIndex(DBAdapterM.SUBJECT))+"  ";
                teacher=teacher+c.getString(c.getColumnIndex(DBAdapter.TEACHER))+"  ";
                int index7=c.getColumnIndex(DBAdapter.START_TIME);
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

                int index8=c.getColumnIndex(DBAdapter.END_TIME);

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
        db.close();



        dba.open();
        c=dba.getContact(check);
        if(c.moveToFirst())
        {
            classAttended=classAttended+c.getString(c.getColumnIndex(DBAttendence.PRESENT));
            classAbsent=classAbsent+c.getString(c.getColumnIndex(DBAttendence.ABSENT));
        }

        dba.close();



        int[] rainbow = getActivity().getResources().getIntArray(R.array.colors);

        int[] fontColors = getActivity().getResources().getIntArray(R.array.font_colors);

        Random rand = new Random();
        int  n = rand.nextInt(11);

        Random font_rand =  new Random();
        int font_n = rand.nextInt(6);

//    Algorithm to set the size of the font
        int temp = subject.length();
        float text_size;
        int max_value = 160;

        if(temp<=5) {
            text_size = max_value - (temp * 10);
        }
        else if(temp<=10){
            text_size = max_value - (temp * 6);
        }
        else if(temp<=15){
            text_size = max_value - (temp * 5);
        }
        else{
            text_size = max_value - (temp * 4);
        }

        subName.setText(subject);
        subName.setTypeface(null, Typeface.BOLD);
        subName.setBackgroundColor(rainbow[n]);
        subName.setTextSize(TypedValue.COMPLEX_UNIT_SP, (text_size/density));
        subName.setText(subject);
        teacherName.setText(teacher);
        timings.setText(timing);
        ca.setText(classAttended);
        cb.setText(classAbsent);


        //Assigning two additional textViews for subject Details and Attendance Details

        TextView subD,attD;

        subD = (TextView) rootView.findViewById(R.id.subDetails);
        attD = (TextView) rootView.findViewById(R.id.attDetails);

        subD.setText("Subject Details");
        subD.setTypeface(Typeface.DEFAULT_BOLD, 0);
        subD.setTextColor(fontColors[font_n]);

        attD.setText("Attendance Details");
        attD.setTypeface(Typeface.DEFAULT_BOLD, 0);
        attD.setTextColor(fontColors[font_n]);


        return rootView;
    }

}
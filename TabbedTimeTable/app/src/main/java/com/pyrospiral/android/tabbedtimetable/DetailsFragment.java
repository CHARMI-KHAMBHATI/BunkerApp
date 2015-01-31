package com.pyrospiral.android.tabbedtimetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        subName = (TextView) rootView.findViewById(R.id.subName);
        teacherName = (TextView) rootView.findViewById(R.id.teacherName);
        timings = (TextView) rootView.findViewById(R.id.timings);
        ca = (TextView) rootView.findViewById(R.id.classesAttended);
        cb = (TextView) rootView.findViewById(R.id.classCanBunk);

        if(check.equals("Database Management Systems"))
        {
            subName.setText("Database Management Systems");
            teacherName.setText("Dipti Rana, Pinak Patel");
            timings.setText("11:25-12:20, 2:00-2:55");
            ca.setText("20");
            cb.setText("1");
        }
        else if(check.equals("Theory of Computer Science"))
        {
            subName.setText("Theory of Computer Science");
            teacherName.setText("Mr. M.A. Zaveri");
            timings.setText("11:25-12:20, 2:00-2:55");
            ca.setText("20");
            cb.setText("1");
        }
        else if(check.equals("Communication Systems"))
        {
            subName.setText("Communication Systems");
            teacherName.setText("Mr. Abhilash Mandloi");
            timings.setText("11:25-12:20, 2:00-2:55");
            ca.setText("20");
            cb.setText("1");
        }
        else if(check.equals("Control Systems"))
        {
            subName.setText("Control Systems");
            teacherName.setText("Mr. Hiren G. Patel");
            timings.setText("11:25-12:20, 2:00-2:55");
            ca.setText("20");
            cb.setText("1");
        }
        else if(check.equals("Engg. Maths III"))
        {
            subName.setText("Engg. Maths III");
            teacherName.setText("Mr. Jana");
            timings.setText("11:25-12:20, 2:00-2:55");
            ca.setText("20");
            cb.setText("1");
        }
        else if(check.equals("Software Tools II"))
        {
            subName.setText("Database Management Systems");
            teacherName.setText("Ammy Ma'am");
            timings.setText("11:25-12:20, 2:00-2:55");
            ca.setText("20");
            cb.setText("1");
        }

        return rootView;
    }
}
package com.pyrospiral.android.tabbedtimetable;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaano8 on 24/1/15.
 */
public class ViewAttFragment extends Fragment implements ViewAttAdapter.ClickListener {

    public RecyclerView mrecyclerView;
    private ViewAttAdapter mAdapter;

    public ViewAttFragment() {
    }

    String[] subName=new String[100];
    String[] teacherName=new String[100];
    String[] timings=new String[100];


    @Override
    public void onCreate(Bundle savedInstanceState) {

        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("Attendance");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final DBAttendence db=new DBAttendence(getActivity());
        db.open();
        Cursor c = db.getAllContacts();
        int i=0;
        if(c.moveToFirst())
        {
            do {
                int index1,index2,index3,index4;
                index1=c.getColumnIndex(DBAttendence.SUBJECT);
                index2=c.getColumnIndex(DBAttendence.PRESENT);
                //index3=c.getColumnIndex(DBAttendence.ABSENT);
                index4=c.getColumnIndex(DBAttendence.TOTAL);

                subName[i]=c.getString(index1);
                teacherName[i]="PRESENT CLASSES : "+c.getString(index2);
                timings[i]="TOTAL CLASSES : "+c.getString(index4);

                i++;


            }while(c.moveToNext());
        }
        db.close();


        View rootView = inflater.inflate(R.layout.fragment_view_attendance, container, false);


        //setting recycler view
        mrecyclerView = (RecyclerView) rootView.findViewById(R.id.list1);
        mrecyclerView.setHasFixedSize(true);
        mAdapter = new ViewAttAdapter(getActivity(), getData());
        mAdapter.setClickListener(this);
        mrecyclerView.setAdapter(mAdapter);

        //Set out layout Manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mrecyclerView.setLayoutManager(mLayoutManager);

        FloatingActionButton buttona = (FloatingActionButton) rootView.findViewById(R.id.action_a);
        buttona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FillAttendance.class));
            }
        });

      View view = inflater.inflate(R.layout.view_att_row, null);
      TextView  textview1 = (TextView) view.findViewById(R.id.subName);
      textview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(" ", "Its entering");
                Intent intent = new Intent(getActivity(), SubjectDetail.class);
                startActivity(intent);
            }
        });

        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("Attendance");
        return rootView;
    }


    //Inserting some fake data
    public List<Info> getData() {


        List<Info> data = new ArrayList<>();



        for (int i = 0; i < subName.length && i < teacherName.length; i++) {

            if(subName[i]==null && teacherName[i]==null)
                continue;

            Info current = new Info();
            current.subName = subName[i % subName.length];
            current.teacherName = teacherName[i % teacherName.length];
            current.timings = timings[i% timings.length];
            data.add(current);
        }
        return data;
    }

    @Override
    public void itemClicked(View view, int position) {
     startActivity(new Intent(getActivity(), SubjectDetail.class));
    }
}

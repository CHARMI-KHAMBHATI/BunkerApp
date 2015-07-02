package com.pyrospiral.android.tabbedtimetable;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adeshkala on 30/05/15.
 */
public class All_events_fragment extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public All_events_fragment() {

    }

    private String names_event[] = new String[100];
    private String chapter_name[] = new String[100];
    private String date_event[] = new String[100];
    private String time_event[] = new String[100];
    public ArrayList<EventData> data=new ArrayList<EventData>();

    public RecyclerView mrecyclerView;
    private AllEventsAdapter mAdapter;



    private SwipeRefreshLayout swf;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DBEvent db=new DBEvent(getActivity());
        db.open();
        Cursor c=db.getAllContacts();
        int i=0;
        if(c.moveToFirst())
        {
            do{
                EventData current=new EventData();
                current.eventName = c.getString(c.getColumnIndex(DBEvent.EVENT));
                current.chapterName = c.getString(c.getColumnIndex(DBEvent.CHAPTER));
                current.date = c.getString(c.getColumnIndex(DBEvent.DATE));
                current.time = c.getString(c.getColumnIndex(DBEvent.TIME));
                data.add(current);
            }while (c.moveToNext());
        }


        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_all_events, container, false);

        swf=(SwipeRefreshLayout)rootView.findViewById(R.id.swiper);
        swf.setOnRefreshListener(this);

        mrecyclerView = (RecyclerView) rootView.findViewById(R.id.event_view);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new AllEventsAdapter(getActivity(),data);
        mrecyclerView.setAdapter(mAdapter);


        //Set out layout Manager
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.supportsPredictiveItemAnimations();
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mrecyclerView.setLayoutManager(llm);


        mrecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), EventDetails.class);
                        startActivity(intent);
                    }
                })
        );




        return rootView;
    }


    @Override
    public void onRefresh() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("All_Events");


        try {
            query.findInBackground(new FindCallback<ParseObject>() {

                @Override
                public void done(List<ParseObject> Name_event, com.parse.ParseException e) {
                    DBEvent db = new DBEvent(getActivity());
                    db.open();
                    db.deleteEverything();
                    if (e == null) {
                        int i = 0;
                        for (ParseObject totem : Name_event) {
                            names_event[i] = totem.getString("Name_event");
                            chapter_name[i] = totem.getString("Chapter_Name");
                            date_event[i] = totem.getString("Date");
                            time_event[i] = totem.getString("Time");

                            db.insertContact(names_event[i],chapter_name[i], date_event[i],time_event[i]);
                            i++;
                        }

                        FragmentManager fragmentManager = getFragmentManager();

                        fragmentManager.beginTransaction()
                                .replace(R.id.container5, new EventFragment())
                                .commit();
                    }
                    else
                    {
                        Log.d("score", "Error: " + e.getMessage());
                        Toast.makeText(getActivity(), "ERROR, CANT CONNECT", Toast.LENGTH_SHORT).show();

                    }


                }




            });
        }
        catch (Exception es)
        {
            Toast.makeText(getActivity(),es.toString(),Toast.LENGTH_SHORT).show();
        }


    }
}



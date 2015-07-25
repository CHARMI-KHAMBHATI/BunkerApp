package com.pyrospiral.android.tabbedtimetable;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by adeshkala on 18/07/15.
 */
public class All_fav_fragment extends android.support.v4.app.Fragment {

    int doing=0;

    public All_fav_fragment(){

    }

    public RecyclerView mrecyclerView;
    private FavEventAdapter mAdapter;

    public ArrayList<EventData> data=new ArrayList<EventData>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DBEvent db=new DBEvent(getActivity());
        db.open();
        Cursor c=db.getFavEvent();
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

        doing=1;

        final View rootView = inflater.inflate(R.layout.fragment_fav_event, container, false);

        mrecyclerView = (RecyclerView) rootView.findViewById(R.id.fav_event_view);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new FavEventAdapter(getActivity(),data);
        //mAdapter.setClickListener(this);
        mrecyclerView.setAdapter(mAdapter);


        //Set out layout Manager
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.supportsPredictiveItemAnimations();
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mrecyclerView.setLayoutManager(llm);

        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(" FAV Events");

        return rootView;
    }


    }

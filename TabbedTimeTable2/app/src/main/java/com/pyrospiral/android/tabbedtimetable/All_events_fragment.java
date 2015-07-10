package com.pyrospiral.android.tabbedtimetable;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by adeshkala on 30/05/15.
 */
public class All_events_fragment extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static final String PREF_FILE = "refresh_date";

    public All_events_fragment() {



    }

    private String names_event[] = new String[100];
    private String chapter_name[] = new String[100];
    private String date_event[] = new String[100];
    private String time_event[] = new String[100];
    private String description[]=new String[100];
    private String location[]=new String[100];
    private String teamsize[]=new String[100];
    private String fee[]=new String[100];
    private String link[]=new String[100];
    private String cordi1N[]=new String[100];
    private String cordi2N[]=new String[100];
    private String cordi1NU[]=new String[100];
    private String cordi2NU[]=new String[100];


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
        //mAdapter.setClickListener(this);
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
                        Intent intent=new Intent(getActivity(), EventDetails.class).putExtra(Intent.EXTRA_TEXT,Integer.toString(position));
                        startActivity(intent);
                    }
                })
        );

        Calendar c = Calendar.getInstance();
        int date=c.get(Calendar.DAY_OF_YEAR);
        String date_saved=readFromPreferences(getActivity(),"Last_Refresh","");

        if(date_saved.equals(Integer.toString(date)))
        {

        }

        else
        {
            swf.post(new Runnable() {
                @Override
                public void run() {
                    swf.setRefreshing(true);
                    onRefresh();
                }
            });
        }
        swf.setRefreshing(false);





        return rootView;
    }


    @Override
    public void onRefresh() {

        boolean isInternetThere=isNetworkAvailable();
        if(isInternetThere) {
            Toast.makeText(getActivity(), "Just a moment..", Toast.LENGTH_SHORT).show();


            ParseQuery<ParseObject> query = ParseQuery.getQuery("EventObject");
            //query.selectKeys(Arrays.asList("Name_event", "Chapter_Name", "Date", "Time"));

            try {
                query.findInBackground(new FindCallback<ParseObject>() {

                    @Override
                    public void done(List<ParseObject> eventName, com.parse.ParseException e) {

                        if (e == null) {
                            DBEvent db = new DBEvent(getActivity());
                            db.open();
                            db.deleteEverything();
                            int i = 0;
                            for (ParseObject totem : eventName) {
                                names_event[i] = totem.getString("eventName");
                                chapter_name[i] = totem.getString("chapter");
                                date_event[i] = totem.getString("date");
                                time_event[i] = totem.getString("time");
                                description[i] = totem.getString("eventDetail");
                                location[i] = totem.getString("location");
                                fee[i] = totem.getString("regFee");
                                link[i] = totem.getString("regLink");
                                teamsize[i] = totem.getString("teamSize");
                                cordi1N[i] = totem.getString("coordOneName");
                                cordi2N[i] = totem.getString("coordTwoName");
                                cordi2NU[i] = totem.getString("coordOnePhone");
                                cordi1NU[i] = totem.getString("coordTwoPhone");


                                db.insertContact(names_event[i], chapter_name[i], date_event[i], time_event[i], location[i], teamsize[i], fee[i],
                                        link[i], cordi1N[i], cordi2N[i], cordi1NU[i], cordi2NU[i], description[i]);
                                i++;
                            }

                            Calendar c = Calendar.getInstance();
                            int date = c.get(Calendar.DAY_OF_YEAR);
                            saveToPreferences(getActivity(), "Last_Refresh", Integer.toString(date));

                            FragmentManager fragmentManager = getFragmentManager();

                            fragmentManager.beginTransaction()
                                    .replace(R.id.container5, new EventFragment())
                                    .commit();
                        } else {

                            Toast.makeText(getActivity(), "ERROR, CANT CONNECT", Toast.LENGTH_SHORT).show();
                            swf.setRefreshing(false);

                        }


                    }


                });
            } catch (Exception es) {
                Toast.makeText(getActivity(), es.toString(), Toast.LENGTH_SHORT).show();
                swf.setRefreshing(false);
            }
        }
        else
            Toast.makeText(getActivity(),"No Internet Found",Toast.LENGTH_SHORT).show();
        swf.setRefreshing(false);


    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(preferenceName, preferenceValue);
        edit.apply();
    }


    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }




}



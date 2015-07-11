package com.pyrospiral.android.tabbedtimetable;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;



public class DrawerFragment extends Fragment {


    public DrawerFragment() {
        // Required empty public constructor
    }



    public static final String PREF_FILE = "testPref";
    public static final String KEy_USER_LEARNED_DRAWER = "user_learned_drawer";

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private boolean mUserLearnedDrawer;
    private boolean mFromInstanceState;
    private View mContainerView;

    private DrawerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), KEy_USER_LEARNED_DRAWER, "false"));
        if (savedInstanceState != null) {
            mFromInstanceState = true;
        }

    }

    public void setup(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        mContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), KEy_USER_LEARNED_DRAWER, mUserLearnedDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }



        };

        if (!mUserLearnedDrawer && !mFromInstanceState) {
            mDrawerLayout.openDrawer(mContainerView);
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_drawer, container, false);

        ListView list = (ListView) layout.findViewById(R.id.drawerListView);
        DrawerAdapter adapter = new DrawerAdapter(getActivity(), getData());
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                FragmentManager fragmentManager = getFragmentManager();


                switch (position){
                    case 0:
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, new TimeTableViewPagerFragment())
                                .commit();
                        break;
                    case 1:
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, new AssignmentList())
                                .commit();
                        break;
                    case 2:
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, new ViewAttFragment())
                                .commit();
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(), SettingsActivity.class));
                        break;

                    //TODO: CHANGE THIS LATER!!!!! EventFragment instead of All_events_fragment

                    case 3:
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, new All_events_fragment())
                                .commit();
                        break;
                }




                mDrawerLayout.closeDrawers();
            }
        });


        return layout;
    }

    public static List<DrawerData> getData(){

        List<DrawerData> data = new ArrayList<>();
        int[] icons = {R.drawable.timetable, R.drawable.assignments, R.drawable.attendance,R.drawable.timetable, R.drawable.settings };
        String[] titles = {"Timetable", "Assignments", "Attendance","Events", "Settings"};
        for(int i=0;i<titles.length && i<icons.length ;i++)
        {
            DrawerData current = new DrawerData();
            current.iconId = icons[i%icons.length];
            current.title = titles[i%titles.length];
            data.add(current);
        }

        return data;
    }



}

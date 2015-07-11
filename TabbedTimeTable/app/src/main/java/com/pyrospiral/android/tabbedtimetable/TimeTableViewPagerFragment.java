package com.pyrospiral.android.tabbedtimetable;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.astuetz.PagerSlidingTabStrip;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimeTableViewPagerFragment extends Fragment {


    public static final String PREF_FILE = "testPrefs";


    private ViewPager viewPager;
    private TimeTableAdapter adapter;

    private Toolbar toolbar;

    int doing=0;
    int done=1;

    public TimeTableViewPagerFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Toast.makeText(getActivity(),"ME",Toast.LENGTH_SHORT).show();
        saveToPreferences(getActivity(), "Refresh_State_Controller", "1");
        View rootView = inflater.inflate(R.layout.fragment_time_table_view_pager, container, false);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        adapter = new TimeTableAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) rootView.findViewById(R.id.tabs);
        tabs.setViewPager(viewPager);
        //tabs.setTextColor(Color.BLUE);
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("TimeTable");
        return rootView;
    }

    @Override
    public void onResume() {

        super.onResume();
        //Toast.makeText(getActivity(),"HEY",Toast.LENGTH_SHORT).show();
        doing=Integer.parseInt(readFromPreferences(getActivity(), "Refresh_State_Controller",""));
        saveToPreferences(getActivity(), "Refresh_State_Controller", "0");


            final FragmentManager fragmentManager = getFragmentManager();

        if(doing==0) {

            fragmentManager.beginTransaction()
                    .replace(R.id.container, new TimeTableViewPagerFragment())
                    .commit();

            //    return rootView;
        }


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


}




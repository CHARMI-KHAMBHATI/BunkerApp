package com.pyrospiral.android.tabbedtimetable;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.astuetz.PagerSlidingTabStrip;

/**
 * Created by adeshkala on 29/06/15.
 */
public class EventFragment extends Fragment {


    public static final String PREF_FILE = "testPrefs";


    private ViewPager viewPager;
    private EventAdapter adapter;

    private Toolbar toolbar;

    int doing=0;
    int done=1;

    public EventFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        saveToPreferences(getActivity(), "Refresh_State_Controller", "1");
        View rootView = inflater.inflate(R.layout.event_view_pager, container, false);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        adapter = new EventAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) rootView.findViewById(R.id.tabs);
        tabs.setViewPager(viewPager);
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("Events");
        return rootView;
    }

    @Override
    public void onResume() {

        super.onResume();

        doing=Integer.parseInt(readFromPreferences(getActivity(), "Refresh_State_Controller",""));
        saveToPreferences(getActivity(), "Refresh_State_Controller", "0");

        final FragmentManager fragmentManager = getFragmentManager();

        if(doing==0) {

            fragmentManager.beginTransaction()
                   .replace(R.id.container5, new EventFragment())
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

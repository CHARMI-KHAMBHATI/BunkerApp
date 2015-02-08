package com.pyrospiral.android.tabbedtimetable;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.astuetz.PagerSlidingTabStrip;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimeTableViewPagerFragment extends Fragment {


    private ViewPager viewPager;
    private TimeTableAdapter adapter;

    private Toolbar toolbar;

    int doing=0;

    public TimeTableViewPagerFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        doing=1;
        View rootView = inflater.inflate(R.layout.fragment_time_table_view_pager, container, false);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        adapter = new TimeTableAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) rootView.findViewById(R.id.tabs);
        tabs.setViewPager(viewPager);
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("TimeTable");
        return rootView;
    }

    @Override
    public void onResume(
    ) {

        super.onResume();
        Log.e("", "onResume");
        Log.e("", "value of doing is " + doing + "");
        if (doing == 0) {

            final FragmentManager fragmentManager = getFragmentManager();


            fragmentManager.beginTransaction()
                    .replace(R.id.container, new TimeTableViewPagerFragment())
                    .commit();

            //    return rootView;
        }


    }
}




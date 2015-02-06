package com.pyrospiral.android.tabbedtimetable;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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


    private ViewPager viewPager;
    private TimeTableAdapter adapter;

    private Toolbar toolbar;

    public TimeTableViewPagerFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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


}

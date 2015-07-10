package com.pyrospiral.android.tabbedtimetable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Kush on 1/14/2015.
 */
public class TimeTableAdapter extends FragmentStatePagerAdapter {

    public TimeTableAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        //Give new instance of timetable list fragment
        return TimeTableFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        // return no of pages
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // return the page title
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        return days[position];
    }


}

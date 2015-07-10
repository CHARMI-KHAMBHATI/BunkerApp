package com.pyrospiral.android.tabbedtimetable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by adeshkala on 29/06/15.
 */
public class EventAdapter extends FragmentStatePagerAdapter {

    public EventAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                return new All_events_fragment();
            case 1:
              return new All_events_fragment();

        }

        return null;
    }

    @Override
    public int getCount() {
        // return no of pages
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // return the page title
        String[] days = {"All Events","Favourites"};
        return days[position];
    }


}

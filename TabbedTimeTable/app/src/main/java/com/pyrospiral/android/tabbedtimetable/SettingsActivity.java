package com.pyrospiral.android.tabbedtimetable;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.LayoutInflater;


public class SettingsActivity extends PreferenceActivity {


    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        addPreferencesFromResource(R.xml.pref_general);




    }


}

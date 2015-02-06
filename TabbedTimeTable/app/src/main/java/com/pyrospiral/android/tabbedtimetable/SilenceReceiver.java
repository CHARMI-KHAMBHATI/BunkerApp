package com.pyrospiral.android.tabbedtimetable;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Kush on 2/6/2015.
 */
public class SilenceReceiver extends BroadcastReceiver {

    private AudioManager mAudioManager;
    private Intent mIntent;
    private int val;


    @Override
    public void onReceive(Context context, Intent intent) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("alarmPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();

        Log.e("Receiver","received");
        Toast.makeText(context, "Alarm !!!!!!!!!!", Toast.LENGTH_LONG).show();

        mIntent = intent;

        if(mIntent != null)
        {

            val = mIntent.getIntExtra("value",5);
            Log.e("Silence service","mIntent  "+val);
        }

        mAudioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);






        if(val == 1 && sharedPreferences.getInt("silent",0)==0) {
            Log.e("Silence service","phone silent");
            Toast.makeText(context, "Phone on silent", Toast.LENGTH_SHORT).show();
            mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            edit.putInt("silent", 1);
            edit.putInt("normal", 0);
            edit.apply();

        }


        if(val == 2 && sharedPreferences.getInt("normal",0)==0) {
            Log.e("Silence service","phone normal");
            Toast.makeText(context, "Phone removed from silent", Toast.LENGTH_SHORT).show();
            mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            edit.putInt("normal", 1);
            edit.putInt("silent", 0);
            edit.apply();

        }

    }


}

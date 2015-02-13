package com.pyrospiral.android.tabbedtimetable;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Kush on 1/26/2015.
 */
public class SilenceService extends Service {

    private AudioManager mAudioManager;
    private Intent mIntent;
    private int val;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mIntent = intent;
//        Log.e("service",""+mIntent+"  "+mIntent.hasExtra(Intent.EXTRA_TEXT));

        if(mIntent != null)
        {

            val = mIntent.getIntExtra("value",5);
            Log.e("Silence service","mIntent  "+val);
        }

        mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        Log.e("Silence service","works");




        if(val == 1) {
            Toast.makeText(this, "Phone on silent", Toast.LENGTH_SHORT);
            mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        }


        if(val == 2) {
            Toast.makeText(this, "Phone removed from silent", Toast.LENGTH_SHORT);
            mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }

        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
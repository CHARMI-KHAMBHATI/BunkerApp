package com.pyrospiral.android.tabbedtimetable;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
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
        mIntent = intent;
        return null;
    }

    @Override
    public void onCreate() {


        mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);


        if(mIntent != null && mIntent.hasExtra(Intent.EXTRA_TEXT))
        {
            val = mIntent.getIntExtra("value",5);
        }


        if(val == 1) {
            Toast.makeText(this, "Phone on silent", Toast.LENGTH_SHORT);
            mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        }


        if(val == 2) {
            Toast.makeText(this, "Phone removed from silent", Toast.LENGTH_SHORT);
            mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }

        super.onCreate();
    }
}

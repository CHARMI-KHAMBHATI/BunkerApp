package com.pyrospiral.android.tabbedtimetable;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
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


        mAudioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);

        //Log.e("Receiver","received");

        mIntent = intent;

        if(mIntent != null)
        {

            val = mIntent.getIntExtra("value",5);
            //Log.e("Silence service","mIntent  "+val);
        }


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            if(val == 1 && +mAudioManager.getRingerMode()!=1) {
                Log.e("Silence service","phone silent");
                Toast.makeText(context, "Phone on silent", Toast.LENGTH_SHORT).show();
                mAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);

            }


            if(val == 2 && +mAudioManager.getRingerMode()!=2) {
                Log.e("Silence service","phone normal");
                Toast.makeText(context, "Phone removed from silent", Toast.LENGTH_SHORT).show();
                mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

            }
        }


        else {


            if (val == 1 && +mAudioManager.getRingerMode() != 0) {
                Log.e("Silence service", "phone silent");
                Toast.makeText(context, "Phone on silent", Toast.LENGTH_SHORT).show();
                mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);

            }


            if (val == 2 && +mAudioManager.getRingerMode() != 2) {
                Log.e("Silence service", "phone normal");
                Toast.makeText(context, "Phone removed from silent", Toast.LENGTH_SHORT).show();
                mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

            }
        }
    }


}

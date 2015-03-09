package com.pyrospiral.android.tabbedtimetable;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;


public class NotificationMaker extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent fill_intent;
        final String UniqueID = "123";

        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);


        //Defining the fill intent
        fill_intent = new Intent(context, FillAttendance.class).
                putExtra(Intent.EXTRA_TEXT, UniqueID);
        fill_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);



        PendingIntent pi2 = PendingIntent.getActivity(context, 0, fill_intent, 0);



        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(
                context).setSmallIcon(R.drawable.ic_launcher)
                .setTicker("Did you go to class today?")
                .setContentTitle("Bunker App")
                .setContentText("Fill today's attendance").setSound(alarmSound)
                .setAutoCancel(true)
                .setWhen(when)
                .setContentIntent(pi2);
        notificationManager.notify(0, mNotifyBuilder.build());
    }

}
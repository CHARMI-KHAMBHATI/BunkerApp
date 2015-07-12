package com.pyrospiral.android.tabbedtimetable;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Kush on 7/13/2015.
 */
public class EventNotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String chapter = intent.getStringExtra("chapter");
        String description = intent.getStringExtra("description");
        String timing = intent.getStringExtra("timing");

        //TODO: USE ID OR NAME ACCORDINGLY
        int eventId = intent.getIntExtra("id",0);

        Intent eventIntent = new Intent(context,EventDetails.class).putExtra(Intent.EXTRA_TEXT, eventId).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, eventId, eventIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);


        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(
                context)
                .setSmallIcon(R.drawable.ic_launcher)
                .setTicker(description)
                .setContentTitle(chapter)
                .setContentText(timing+"  "+description)
                .setSound(alarmSound)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent);
        notificationManager.notify(0, mNotifyBuilder.build());

    }
}

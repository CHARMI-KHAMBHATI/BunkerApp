package com.pyrospiral.android.tabbedtimetable;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


public class NotificationMaker extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        Log.e("Service","Oncreated");



        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0,intent,0);


        Uri uri =  Uri.parse("android.resource://"
                + this.getPackageName() + "/" + R.raw.navinotification);

        Notification noti = new NotificationCompat.Builder(this)
                .setContentTitle("Bunker App")
                .setContentText("Fill today's attendance").setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pIntent)
                .setSound(uri)
                .addAction(R.drawable.settings, "Fill Attendance", pIntent)
                .addAction(R.drawable.settings, "All Present", pIntent).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);



        super.onCreate();
    }




}

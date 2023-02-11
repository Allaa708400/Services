package com.example.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyService extends Service {
    private static final String CHANNEL_ID = "x_channel_id";
    MediaPlayer mp;


    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mp = MediaPlayer.create(this, R.raw.sund);

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                stopSelf();
            }
        });

        Log.d("Ziyad", "Service created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        startForeground(1, getNotificationObject());

        if (!mp.isPlaying())
            mp.start();

        Log.d("Ziyad", "Service Started");

        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mp.isPlaying()) {
            mp.stop();
            mp.release();
        }
        Log.d("Ziyad", "Service destroyed");
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    private Notification getNotificationObject() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Channel display name",
                    NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(channel);


        }

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_baseline_notifications_paused_24)
                .setContentTitle("Title")
                .setContentText("Text")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("data"))
                .addAction(R.drawable.ic_baseline_notifications_paused_24, "Replay", pi);

        NotificationManagerCompat nmc = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {




        }
        return builder.build();
    }
}



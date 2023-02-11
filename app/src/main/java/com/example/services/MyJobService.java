package com.example.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

public class MyJobService extends JobService {

    MediaPlayer mp;

    @Override
    public boolean onStartJob(JobParameters params) {
        mp = MediaPlayer.create(this, R.raw.sund);
        mp.start();

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                jobFinished(params,false);
            }
        });


        Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();
       // jobFinished(params,false);
        Log.d("Ziyad", "Service started");

        return true;
    }



    @Override
    public boolean onStopJob(JobParameters params) {

        Toast.makeText(this, "Stopped", Toast.LENGTH_SHORT).show();

        Log.d("Ziyad", "Service stopped");

        if (mp != null && mp.isPlaying())
            mp.stop();
        return false;
    }
}

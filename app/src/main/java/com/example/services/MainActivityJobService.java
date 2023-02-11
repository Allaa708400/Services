package com.example.services;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.services.databinding.ActivityMainBinding;

public class MainActivityJobService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        setContentView(R.layout.activity_main_job_service);


        Button btnStart = findViewById(R.id.btn_start);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ComponentName cn = new ComponentName(getBaseContext(),MyJobService.class);
                JobInfo info ;

                if (Build.VERSION.SDK_INT<=Build.VERSION_CODES.N) {

                    info = new JobInfo.Builder(10, cn)
                            .setPeriodic(5000)
                            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                            .build();
                }
                else {

                    info = new JobInfo.Builder(10, cn)
                            .setMinimumLatency(5000)
                            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                            .build();
                }

                JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
                scheduler.schedule(info);
            }
        });

    }
}
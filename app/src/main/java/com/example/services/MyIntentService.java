package com.example.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.File;


public class MyIntentService extends IntentService {


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d("Ziyad", "Service started");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("Ziyad", "Service created");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("Ziyad", "Service destroyed");
    }
}


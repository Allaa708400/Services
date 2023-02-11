package com.example.services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.services.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        binding.startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  Intent intent = new Intent(getBaseContext(), MyService.class);
                Intent intent = new Intent(getBaseContext(), MyService.class);
             //  startService(intent);


                    ContextCompat.startForegroundService(getBaseContext(),intent);


            }
        });



        binding.stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent intent = new Intent(getBaseContext(), MyService.class);
                Intent intent = new Intent(getBaseContext(), MyService.class);
               stopService(intent);

            }
        });

    }
}
package com.example.groupupv2.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.groupupv2.homepage.NotificationMaker;
import com.example.groupupv2.interfaces.CustomNotification;
import com.example.groupupv2.R;

public class SplashScreen extends AppCompatActivity {

    final private int SPLASH_SCREEN_TIME = 2000;
    CustomNotification channelMaker = new NotificationMaker();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ((NotificationMaker)channelMaker).createChannel(this);

       new Handler().postDelayed(() -> {
           Intent i = new Intent(SplashScreen.this, MainActivity.class);
           startActivity(i);
       }, SPLASH_SCREEN_TIME);
    }





}
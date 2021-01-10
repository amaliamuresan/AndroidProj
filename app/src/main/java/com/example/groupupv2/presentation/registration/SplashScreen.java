package com.example.groupupv2.presentation.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.groupupv2.presentation.NotificationMaker;
import com.example.groupupv2.interfaces.NotificationInterface;
import com.example.groupupv2.R;

import timber.log.Timber;

public class SplashScreen extends AppCompatActivity {

    final private int SPLASH_SCREEN_TIME = 2000;
    NotificationInterface channelMaker = new NotificationMaker();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ((NotificationMaker)channelMaker).createChannel(this);
        Timber.i("Timber from Splash Screen");

       new Handler().postDelayed(() -> {
           Intent i = new Intent(SplashScreen.this, SignUpActivity.class);
           startActivity(i);
       }, SPLASH_SCREEN_TIME);
    }





}
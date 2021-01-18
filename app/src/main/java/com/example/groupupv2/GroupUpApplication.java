package com.example.groupupv2;

import android.app.Application;
import android.util.Log;

import androidx.annotation.Nullable;

import com.facebook.stetho.Stetho;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.database.annotations.NotNull;

import timber.log.Timber;

public class GroupUpApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        Stetho.initializeWithDefaults(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}

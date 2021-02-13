package com.example.groupupv2.presentation;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import static com.example.groupupv2.presentation.NotificationMaker.NOTIFICATION_ID;

public class NotificationService extends Service {

    public static int TYPE_BASIC = 0;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(NOTIFICATION_ID, NotificationMaker.createNotification(
                this, "Service Notification",
                "Notification from foreground service")
        );
        return super.onStartCommand(intent, flags, startId);
    }

    private static PendingIntent createContentIntent(Context context)
    {
        Intent intent = new Intent(context, NavigationDrawerActivity.class);
        return PendingIntent.getActivity(context, 0, intent, 0);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

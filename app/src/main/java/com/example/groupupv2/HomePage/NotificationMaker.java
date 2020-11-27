package com.example.groupupv2.HomePage;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.groupupv2.Interfaces.CustomNotification;
import com.example.groupupv2.R;
import com.example.groupupv2.Registration.LoginActivity;

public class NotificationMaker implements CustomNotification {
    public static String CHANNEL_ID = "Chanel1";




    public void createNotification(Context context, String title, String text)
    {
        createChannel(context);
        Intent intent = new Intent(context, context.getClass());// just for login for now
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.add)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(123, builder.build());
    }

    public void createChannel(Context context)
    {
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Channel 1", NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription("This is chanel1");
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);


    }
}

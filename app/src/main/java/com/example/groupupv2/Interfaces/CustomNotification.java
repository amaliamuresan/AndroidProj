package com.example.groupupv2.Interfaces;

import android.content.Context;

public interface CustomNotification {

    void createNotification(Context context, String title, String text);
    void createChannel(Context context);
}

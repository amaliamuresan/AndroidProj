package com.example.groupupv2.homepage;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.groupupv2.interfaces.NotificationInterface;
import com.example.groupupv2.presentation.NotificationMaker;

public class CustomWorker extends Worker {
    public CustomWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Data data = getInputData();
        String title = data.getString("title");
        String description = data.getString("description");

        NotificationInterface notification = new NotificationMaker();
        ((NotificationMaker) notification).createNotification(getApplicationContext(), title, description);

        return Result.success();
    }
}

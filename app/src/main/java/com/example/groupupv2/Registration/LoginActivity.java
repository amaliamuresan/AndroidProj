package com.example.groupupv2.Registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.groupupv2.HomePage.NavigationDrawerActivity;
import com.example.groupupv2.HomePage.NotificationMaker;
import com.example.groupupv2.Interfaces.CustomNotification;
import com.example.groupupv2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class LoginActivity extends AppCompatActivity {

    //String CHANNEL_ID = "mynot";
    EditText passwordET;
    EditText emailET;
    Button loginBtn;
    CustomNotification notificationMaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        notificationMaker = new NotificationMaker();

        notificationMaker.createNotification(this, "My notification", "Description Login");

/*
        NotificationChannel channel  = new NotificationChannel(CHANNEL_ID, "Channel1",NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);
        createNotification(this, "Title1", "Description");
*/

        passwordET = findViewById(R.id.Login_PasswordET);
        passwordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passwordET.setTypeface(Typeface.DEFAULT);

        emailET = findViewById(R.id.Login_EmailET);
        loginBtn = findViewById(R.id.Login_LogInBtn);

        loginBtn.setOnClickListener(view -> {

            String email = emailET.getText().toString();
            String password  = passwordET.getText().toString();

           loginUser(email, password);

           Log.i("Login", "Login button pressed");

        });




    }

    private boolean emptyFields(String email, String password)
    {
        boolean rez = false;

        if (email == null || email.equals("")) {
            emailET.setError("Complete the field!");
            rez = true;
        }

        if (password == null || password.equals("")) {
            passwordET.setError("Complete the field!");
            rez = true;
        }

        return rez;
    }

    void loginUser(String email, String password)
    {
        FirebaseAuth user = FirebaseAuth.getInstance();

        if(!emptyFields(email, password))
        {
            user.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, NavigationDrawerActivity.class));
                            }
                            else
                            {
                                FirebaseAuthException e = (FirebaseAuthException) task.getException();
                                Toast.makeText(LoginActivity.this, "Login failed because " + e.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
        }
    }

    /*public void createNotification(Context context, String title, String text)
    {
        Intent intent = new Intent(context, LoginActivity.class);
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
    }*/




}
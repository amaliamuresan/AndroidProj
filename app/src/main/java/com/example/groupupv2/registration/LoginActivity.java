package com.example.groupupv2.registration;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.groupupv2.homepage.NavigationDrawerActivity;
import com.example.groupupv2.homepage.NotificationMaker;
import com.example.groupupv2.interfaces.CustomNotification;
import com.example.groupupv2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class LoginActivity extends AppCompatActivity {

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
                    .addOnCompleteListener(task -> {
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

                    });
        }
    }

}
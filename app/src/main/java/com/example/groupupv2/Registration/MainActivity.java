package com.example.groupupv2.Registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.groupupv2.HomePage.NavigationDrawerActivity;
import com.example.groupupv2.HomePage.User;
import com.example.groupupv2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    TextInputEditText passwordET;
    TextInputEditText emailET;
    TextInputEditText nameET;
    TextInputEditText usernameET;

    TextView LoginTV;
    Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginTV = findViewById(R.id.Main_LoginTV);
        emailET = findViewById(R.id.Main_EmailET);
        nameET = findViewById(R.id.Main_NamelET);
        usernameET = findViewById(R.id.Main_usernameET);
        signUpBtn = findViewById(R.id.Main_SignUpBtn);
        passwordET = findViewById(R.id.Main_PasswordET);
        passwordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passwordET.setTypeface(Typeface.DEFAULT);

        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        LoginTV.setOnClickListener((View view) -> {

                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);

        });

      signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = emailET.getText().toString();
                final String name = nameET.getText().toString();
                final String username = usernameET.getText().toString();
                final String password = passwordET.getText().toString();

                if (emptyAuthFields(email, name, username, password) == false) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                writeNewUser(username, name, email);
                                startActivity(new Intent(MainActivity.this, NavigationDrawerActivity.class));
                            } else {
                                FirebaseAuthException e = (FirebaseAuthException) task.getException();
                                Toast.makeText(MainActivity.this, "Registration failed because " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    private boolean emptyAuthFields(String email, String name, String username, String password) {
        boolean rez = false;
        if (email == null || email.equals("")) {
            emailET.setError("Complete the field!");
            rez = true;
        }

        if (name == null || name.equals("")) {
            nameET.setError("Complete the field!");
            rez = true;
        }

        if (password == null || password.equals("")) {
            passwordET.setError("Complete the field!");
            rez = true;
        }

        if (username == null || username.equals("")) {
            usernameET.setError("Complete the field!");
            rez = true;
        }
        return rez;
    }

    private void writeNewUser(String username, String name, String email) {

        User user = new User(email, name, username);
        mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user);
    }
}
package com.example.groupupv2.domain;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserDetails {
    static String name = "";
    static String email = "";
    static String username = "";
    static String currentUserUid = "";

    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = mAuth.getCurrentUser();


    public static String getEmail() {
        return email;
    }

    public static String getUsername() {
        return username;
    }

    public static String getCurrentUserUid() {
        return currentUserUid;
    }

    public static String getName() {
        return name;
    }

    public void setUName() {

        currentUserUid = currentUser.getUid();
        mDatabase.child(currentUserUid).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void setCurrentUserUid()
    {
        currentUserUid = currentUser.getUid();
    }


    public void getUserDetails()
    {
        setUName();
        setCurrentUserUid();
    }


}

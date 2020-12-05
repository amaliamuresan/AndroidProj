package com.example.groupupv2.presentation;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;

import com.example.groupupv2.R;
import com.example.groupupv2.domain.Post;
import com.example.groupupv2.domain.PostsUseCase;
import com.example.groupupv2.presentation.homefragments.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NavigationDrawerViewModel extends ViewModel {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    public void setHeaderNameAndEmail(TextView emailTV, TextView nameTV) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String id = currentUser.getUid();
            emailTV.setText(currentUser.getEmail());


            mDatabase.child(id).child("name").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String name = dataSnapshot.getValue(String.class);
                    nameTV.setText(name);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    public <T> void setFragment(Class<T> classType, Context context) throws InstantiationException, IllegalAccessException {
        T fragment = classType.newInstance();
        fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame, (Fragment) fragment);
        fragmentTransaction.commit();
    }

    public void setDefaultFragment(Context context)
    {
        fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame, new HomeFragment());
        fragmentTransaction.commit();
    }

    public void makeToast(Context context) {
        Toast.makeText(context,  "Toast from view model", Toast.LENGTH_SHORT).show();
    }

}
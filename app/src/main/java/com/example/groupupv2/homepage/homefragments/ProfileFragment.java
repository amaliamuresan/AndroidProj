package com.example.groupupv2.homepage.homefragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.groupupv2.R;
import com.example.groupupv2.homepage.NavigationDrawerViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    private FirebaseUser currentUser;
    private DatabaseReference mDatabase;
    private String currentUserUid;
    private FirebaseAuth mAuth;
    private NavigationDrawerViewModel viewModel;

    TextView emailTV;
    TextView nameTV;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if (currentUser != null)
            currentUserUid = currentUser.getUid();

        emailTV = view.findViewById(R.id.profile_email);
        nameTV = view.findViewById(R.id.profile_name);

        viewModel = new ViewModelProvider(requireActivity()).get(NavigationDrawerViewModel.class);
        viewModel.makeToast(view.getContext());
        setNameAndEmail();
        return view;
    }

    private void setNameAndEmail()
    {
        Thread thread = new Thread(() ->
        {
            if (currentUser != null) {
                emailTV.setText(currentUser.getEmail());

                mDatabase.child(currentUserUid).child("name").addValueEventListener(new ValueEventListener() {
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
        });
        thread.start();

    }
}

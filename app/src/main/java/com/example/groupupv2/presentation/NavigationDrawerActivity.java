package com.example.groupupv2.presentation;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.groupupv2.R;
import com.example.groupupv2.data.PostDataSource;
import com.example.groupupv2.databinding.NavigationViewBinding;
import com.example.groupupv2.databinding.PostDetailsBinding;
import com.example.groupupv2.domain.PostRepository;
import com.example.groupupv2.domain.PostsUseCase;
import com.example.groupupv2.presentation.homefragments.HomeFragment;
import com.example.groupupv2.presentation.homefragments.ProfileFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView nav_view;
    TextView nameTV;
    TextView emailTV;
    View headerView;

    private FirebaseAuth mAuth;

    private FirebaseUser currentUser;
    private DatabaseReference mDatabase;
    private String currentUserUid;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            NavigationDrawerViewModel viewModel = (NavigationDrawerViewModel) new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                PostRepository repository = new PostDataSource();
                PostsUseCase useCase = new PostsUseCase(repository);

                return (T) new NavigationDrawerViewModel();
            }
        }).get(NavigationDrawerViewModel.class);



        NavigationViewBinding binding = DataBindingUtil.setContentView(this, R.layout.navigation_view);
        binding.setNavigationDrawerViewModel(viewModel);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if (currentUser != null)
            currentUserUid = currentUser.getUid();
        nav_view = findViewById(R.id.menu_allMenu);
        nav_view.setNavigationItemSelectedListener(this);

        drawer = findViewById(R.id.menu_drawerLayout);
        toolbar = findViewById(R.id.menu_toolbarr);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        headerView = nav_view.getHeaderView(0);

        nameTV = headerView.findViewById(R.id.menu_nameTV);
        emailTV = headerView.findViewById(R.id.menu_emailTV);

        setHeaderNameAndEmail();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame, new HomeFragment());
        fragmentTransaction.commit();

    }

    private void setHeaderNameAndEmail() {
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
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.menu_home) {
            try {
                setFragment(HomeFragment.class);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                Log.e("Fragment", "Error setting fragment" + HomeFragment.class.getName());
            }
        }
        if (menuItem.getItemId() == R.id.menu_profile) {
            try {
                setFragment(ProfileFragment.class);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                Log.e("Fragment", "Error setting fragment" + ProfileFragment.class.getName());
            }
        }

        return true;
    }

    public <T> void setFragment(Class<T> classType) throws InstantiationException, IllegalAccessException {
        T fragment = classType.newInstance();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame, (Fragment) fragment);
        fragmentTransaction.commit();
    }

}
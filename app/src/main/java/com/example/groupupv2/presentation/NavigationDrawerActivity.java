package com.example.groupupv2.presentation;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
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
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.groupupv2.R;
import com.example.groupupv2.data.remote.PostDataSource;
import com.example.groupupv2.data.remote.PostsAPI;
import com.example.groupupv2.databinding.NavigationViewBinding;
import com.example.groupupv2.domain.PostMediator;
import com.example.groupupv2.domain.PostRepository;
import com.example.groupupv2.domain.PostsUseCase;
import com.example.groupupv2.domain.UserDetails;
import com.example.groupupv2.presentation.homefragments.HomeFragment;
import com.example.groupupv2.presentation.homefragments.ProfileFragment;
import com.google.android.material.navigation.NavigationView;

import timber.log.Timber;


public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView nav_view;
    TextView nameTV;
    TextView emailTV;
    View headerView;

    NavigationDrawerViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                PostRepository repository = new PostDataSource(PostsAPI.createApi());
                PostMediator postMediator = new PostMediator(repository);
                PostsUseCase useCase = new PostsUseCase(postMediator);

                return (T) new NavigationDrawerViewModel();
            }
        }).get(NavigationDrawerViewModel.class);


        NavigationViewBinding binding = DataBindingUtil.setContentView(this, R.layout.navigation_view);
        binding.setNavigationDrawerViewModel(viewModel);


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

        viewModel.setHeaderNameAndEmail(emailTV, nameTV);

        UserDetails usr = new UserDetails();
        usr.getUserDetails();
        viewModel.setDefaultFragment(this);

        //NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //notificationManager.notify(NotificationMaker.NOTIFICATION_ID, NotificationMaker.createNotification(this, "Title", "Description"));
        Intent serviceIntent = new Intent(this, NotificationService.class);
        startService(serviceIntent);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.menu_home) {
            try {
                viewModel.setFragment(HomeFragment.class, this);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                Timber.e("Error setting fragment %s", HomeFragment.class.getName());
            }
        }
        if (menuItem.getItemId() == R.id.menu_profile) {
            try {
                viewModel.setFragment(ProfileFragment.class, this);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                Timber.e("Error setting fragment %s", ProfileFragment.class.getName());
            }
        }

        return true;
    }



}
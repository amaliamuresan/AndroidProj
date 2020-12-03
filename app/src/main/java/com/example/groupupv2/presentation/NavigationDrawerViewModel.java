package com.example.groupupv2.presentation;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

public class NavigationDrawerViewModel extends ViewModel {

    public void makeToast(Context context)
    {
        Toast.makeText(context,  "Toast from view model", Toast.LENGTH_SHORT).show();
    }

}
package com.example.groupupv2.presentation;

import android.content.Context;
import android.widget.Toast;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModel;

import com.example.groupupv2.domain.Post;
import com.example.groupupv2.domain.PostsUseCase;

public class NavigationDrawerViewModel extends ViewModel {
    public ObservableArrayList<Post> posts = new ObservableArrayList<>();

    private final PostsUseCase postsUseCase;

    public NavigationDrawerViewModel(PostsUseCase postsUseCase) {
        this.postsUseCase = postsUseCase;
    }

    public void makeToast(Context context)
    {
        Toast.makeText(context,  "Toast from view model", Toast.LENGTH_SHORT).show();
    }

}
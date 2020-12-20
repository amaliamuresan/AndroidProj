package com.example.groupupv2.presentation.homefragments;

import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModel;

import com.example.groupupv2.domain.Post;
import com.example.groupupv2.domain.PostsUseCase;

import java.util.List;

import timber.log.Timber;

public class HomeFragmentViewModel extends ViewModel {
    public ObservableArrayList<Post> posts = new ObservableArrayList<>();

    private final PostsUseCase postsUseCase;

    public HomeFragmentViewModel(PostsUseCase postsUseCase) {
        this.postsUseCase = postsUseCase;
    }

    public void addPosts() {
        Timber.i("addPosts() called");
        List<Post> posts = postsUseCase.execute();
        this.posts.addAll(posts);
    }


}

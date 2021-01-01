package com.example.groupupv2.presentation.homefragments;

import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.groupupv2.data.PostDto;
import com.example.groupupv2.domain.Post;
import com.example.groupupv2.domain.PostsUseCase;

import java.util.List;

import timber.log.Timber;

public class HomeFragmentViewModel extends ViewModel {
    public ObservableArrayList<Post> posts = new ObservableArrayList<>();
    MutableLiveData<Class> navigationLiveData = new MutableLiveData<>();
    private final PostsUseCase postsUseCase;

    public HomeFragmentViewModel(PostsUseCase postsUseCase) {
        this.postsUseCase = postsUseCase;
    }

    public void addPosts() {
        Timber.i("addPosts() called");
        LiveData<List<Post>> liveData = postsUseCase.execute();
        liveData.observeForever(items -> this.posts.addAll(items));
        navigationLiveData.setValue(HomeFragment.class);
    }







}

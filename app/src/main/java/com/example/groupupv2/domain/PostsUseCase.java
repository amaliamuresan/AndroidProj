package com.example.groupupv2.domain;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.groupupv2.data.PostDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import timber.log.Timber;

public class PostsUseCase {
    private final PostMediator mediator;

    public PostsUseCase(PostMediator mediator) {
        this.mediator = mediator;
    }

    public LiveData<List<Post>> execute() {
        return mediator.getItems();
    }
    public void postItem(PostDto post) {
        mediator.postItem(post);
    }

}

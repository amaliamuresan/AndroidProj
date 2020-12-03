package com.example.groupupv2.presentation;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupupv2.domain.Post;

import java.util.List;

public class MainBindingAdapter {

    @BindingAdapter("post")
    public static void setPosts(RecyclerView recyclerView, List<Post> posts) {
        RecyclerView.Adapter<?> adapter = recyclerView.getAdapter();
        if(adapter != null)
        {
            adapter = new PostAdapter();
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        }
        if(posts != null)
            ((PostAdapter) adapter).updatePosts(posts);
    }

}

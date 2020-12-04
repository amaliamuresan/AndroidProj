package com.example.groupupv2.domain;

import android.util.Log;

import com.example.groupupv2.data.PostDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PostsUseCase {
    private final PostRepository repository;

    public PostsUseCase(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> execute() {
        try {
            Log.d("PostUseCase", "Execute() called");

            ArrayList<Post> posts = new ArrayList<>();
            for (PostDto dto : repository.getItems()) {
                posts.add(new Post(dto.getAutorID(), dto.getImage(), dto.getData(), dto.getDescription(), dto.getDomain()));
            }
            return posts;
        } catch (Exception e) {
            Log.d("PoatUseCase", "Error " + e.getMessage());
            return Collections.emptyList();
        }

    }
}

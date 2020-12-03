package com.example.groupupv2.domain;

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
            ArrayList<Post> posts = new ArrayList<>();
            for (PostDto dto : repository.getItems()) {
                posts.add(new Post(dto.getAutorID(), dto.getImage(), dto.getData(), dto.getDescription(), dto.getDomain()));
            }
            return posts;
        }catch (Exception e)
        {
            return Collections.emptyList();
        }

    }
}

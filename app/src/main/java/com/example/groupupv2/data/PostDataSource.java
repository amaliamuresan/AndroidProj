package com.example.groupupv2.data;

import com.example.groupupv2.domain.PostRepository;

import java.util.Arrays;
import java.util.List;

public class PostDataSource implements PostRepository {
    private List<PostDto> postsDto;
    @Override
    public List<PostDto> getItems() {
        postsDto = Arrays.asList(new PostDto("saddad", 12, "data", "description", "domain"));

        return postsDto;
    }
}

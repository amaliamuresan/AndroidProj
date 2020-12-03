package com.example.groupupv2.domain;

import com.example.groupupv2.data.PostDto;

import java.util.List;

public interface PostRepository {
    List<PostDto> getItems();
}

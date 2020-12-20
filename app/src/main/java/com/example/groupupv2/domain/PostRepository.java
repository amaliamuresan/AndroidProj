package com.example.groupupv2.domain;

import com.example.groupupv2.data.PostDto;

import java.io.IOException;
import java.util.List;

public interface PostRepository {
    List<PostDto> getItems() throws IOException;
}

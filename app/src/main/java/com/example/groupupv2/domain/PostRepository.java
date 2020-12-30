package com.example.groupupv2.domain;

import com.example.groupupv2.data.ListDtos;
import com.example.groupupv2.data.PostDto;

import java.io.IOException;
import java.util.List;

public interface PostRepository {
    ListDtos getItems();
    int getLastItem();
    void postItem(PostDto postdto);
}

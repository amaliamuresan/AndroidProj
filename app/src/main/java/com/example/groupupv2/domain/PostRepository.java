package com.example.groupupv2.domain;

import com.example.groupupv2.data.PostDto;
import com.example.groupupv2.data.remote.PostDataSource;
import com.example.groupupv2.data.remote.PostDataSource.ICallBack;

import java.util.List;

public interface PostRepository {
    List<PostDto> getItems(PostDataSource.ICallBack iCallBack);
    void postItem(PostDto postdto);
}

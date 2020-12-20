package com.example.groupupv2.data.remote;

import android.util.Log;

import com.example.groupupv2.data.PostDto;
import com.example.groupupv2.domain.PostRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class PostDataSource implements PostRepository {
    private List<PostDto> postsDto = new ArrayList<>();
    private final PostsAPI api;

    public PostDataSource(PostsAPI api) {
        this.api = api;
    }


    @Override
    public List<PostDto> getItems()  {
        Timber.d("getItem() called");
        try {
            return api.getItems().execute().body();
        }
        catch (IOException e)
        {
            Timber.tag("PostDataSource").w("getItems failed");
        }

       // postsDto.add(new PostDto("id1", 12, "12.12.2020", "description1", "domain"));
      //  postsDto.add(new PostDto("id2", 12, "12.12.2021", "description2", "domain"));

        return postsDto;
    }
}

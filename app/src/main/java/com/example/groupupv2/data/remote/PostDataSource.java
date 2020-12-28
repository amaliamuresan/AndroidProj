package com.example.groupupv2.data.remote;

import android.util.Log;

import com.example.groupupv2.data.PostDto;
import com.example.groupupv2.domain.PostRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Response;
import timber.log.Timber;

public class PostDataSource implements PostRepository {
    private final PostsAPI api;

    public PostDataSource(PostsAPI api) {
        this.api = api;
    }


    @Override
    public List<PostDto> getItems()  {
        Timber.d("getItem() called");
        try {
            Response<List<PostDto>> response = api.getItems().execute();
            return response.body();
        }
        catch (IOException e)
        {
            Timber.tag("PostDataSource").w("getItems failed");
            return Collections.emptyList();
        }

       // postsDto.add(new PostDto("id1", 12, "12.12.2020", "description1", "domain"));
      //  postsDto.add(new PostDto("id2", 12, "12.12.2021", "description2", "domain"));


    }
}

package com.example.groupupv2.data.remote;

import android.util.Log;

import com.example.groupupv2.data.ListDtos;
import com.example.groupupv2.data.PostDto;
import com.example.groupupv2.domain.Post;
import com.example.groupupv2.domain.PostRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Response;
import timber.log.Timber;

public class PostDataSource implements PostRepository {
    private final PostsAPI api;
    Response<ListDtos> response = null;



    public PostDataSource(PostsAPI api) {
        this.api = api;
    }


    @Override
    public ListDtos getItems() {
        Timber.d("getItem() called");
        try {
            response = api.getItems().execute();
            return response.body();
        } catch (IOException e) {
            Timber.tag("PostDataSource").w("getItems failed");
            return null;
        }
    }

    @Override
    public void postItem(PostDto post) {
        try {
            api.postItem(post).execute();
            //api.postItem(3, post).execute();
        } catch (Exception e) {
            Timber.w("PostItem(Post post) failed");
        }
    }

    @Override
    public int getLastItem() {
        /*PostDto postDto = new PostDto();
        Timber.d("getItem() called");
        try {
            response = api.getLastItem().execute();
            List<PostDto> list = response.body();
            postDto = list.get(list.size() - 1);
            Timber.d(Integer.toString(postDto.getId()));
            return postDto.getId();
        } catch (IOException e) {
            Timber.tag("PostDataSource").w("getItems failed");
            return 5;
        }
*/
        return 0;
    }
}

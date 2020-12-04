package com.example.groupupv2.data;

import android.util.Log;

import com.example.groupupv2.domain.PostRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostDataSource implements PostRepository {
    private List<PostDto> postsDto = new ArrayList<>();


    @Override
    public List<PostDto> getItems() {
        Log.d("PostDAtaSource", "getItem() called");

        /*postsDto = Arrays.asList(
                new PostDto("saddad", 12, "data", "description", "domain"),
                new PostDto("saddad1", 12, "data1", "description1", "domain1")
        );*/

        postsDto.add(new PostDto("id1", 12, "12.12.2020", "description1", "domain"));
        postsDto.add(new PostDto("id2", 12, "12.12.2021", "description2", "domain"));

        return postsDto;
    }
}

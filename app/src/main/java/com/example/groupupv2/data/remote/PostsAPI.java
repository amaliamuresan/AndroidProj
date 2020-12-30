package com.example.groupupv2.data.remote;

import com.example.groupupv2.data.ListDtos;
import com.example.groupupv2.data.PostDto;
import com.example.groupupv2.domain.Post;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PostsAPI {



    String BASE_URL = "https://group-up-822d1.firebaseio.com/";

    @GET("posts.json")
    Call<ListDtos> getItems();

    @POST("posts.json")
    //Call<PostDto> postItem(@Body PostDto postDto, @Path("id") int postID);
    Call<PostDto> postItem(@Body PostDto post);

    @GET("posts.json")
    Call<List<PostDto>> getLastItem();

    static PostsAPI createApi() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PostsAPI.class);
    }
}

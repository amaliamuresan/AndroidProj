package com.example.groupupv2.data.remote;

import com.example.groupupv2.data.PostDto;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface PostsAPI {

    String BASE_URL = "https://group-up-822d1.firebaseio.com";

    @GET("users.json")
    Call<List<PostDto>> getItems();

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

package com.zeroes.retrofitexample.interfaces;

import com.zeroes.retrofitexample.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiCalls {

    @GET("posts")
    Call<List<Post>> getPost();

}

package com.zeroes.retrofitexample.interfaces;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.zeroes.retrofitexample.model.Post;

import java.util.List;

public interface PostNetworkCallInterface<T> {

    MutableLiveData<List<Post>> getPosts();

}

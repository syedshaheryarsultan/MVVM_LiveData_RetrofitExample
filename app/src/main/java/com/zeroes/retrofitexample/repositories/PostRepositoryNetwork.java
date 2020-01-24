package com.zeroes.retrofitexample.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.zeroes.retrofitexample.interfaces.ApiCalls;
import com.zeroes.retrofitexample.interfaces.PostNetworkCallInterface;
import com.zeroes.retrofitexample.model.Post;
import com.zeroes.retrofitexample.retrofit.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepositoryNetwork implements PostNetworkCallInterface {

    private static PostRepositoryNetwork instance;

    public static PostRepositoryNetwork getInstance(){
        if(instance == null){
            instance = new PostRepositoryNetwork();
        }
        return instance;
    }

    private final ApiCalls apiCalls = RetrofitInstance.getServices();

    @Override
    public MutableLiveData<List<Post>> getPosts() {
        final MutableLiveData<List<Post>> liveData = new MutableLiveData<>();
        Call<List<Post>> call = apiCalls.getPost();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful()){
                    liveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });


        return liveData;
    }
}

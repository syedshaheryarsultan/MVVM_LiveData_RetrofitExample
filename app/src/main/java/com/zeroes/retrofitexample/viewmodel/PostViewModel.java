package com.zeroes.retrofitexample.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zeroes.retrofitexample.model.Post;
import com.zeroes.retrofitexample.repositories.PostRepositoryNetwork;

import java.util.List;

public class PostViewModel extends ViewModel {

    private PostRepositoryNetwork mRepo;

    private MutableLiveData<List<Post>> mPost;

    public void init(){
        if(mPost != null){
            return;
        }
        mRepo = PostRepositoryNetwork.getInstance();
        mPost = mRepo.getPosts();
    }

    public LiveData<List<Post>> getPosts(){
        return mPost;
    }
}

package com.zeroes.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zeroes.retrofitexample.interfaces.ApiCalls;
import com.zeroes.retrofitexample.model.Post;
import com.zeroes.retrofitexample.retrofit.RetrofitInstance;
import com.zeroes.retrofitexample.viewmodel.PostViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;
    private ApiCalls apiCalls;
    ProgressBar progressBar;
    private PostViewModel postViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_bar);
        tvResult = findViewById(R.id.tv_result);

        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        postViewModel.init();

        postViewModel.getPosts().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);

                        for (Post post : posts) {
                            StringBuilder content2 = new StringBuilder();
                            content2.append("ID: " + post.getId() + "\n");
                            content2.append("User Id: " + post.getUserId() + "\n");
                            content2.append("Title: " + post.getTitle() + "\n");
                            content2.append("Body: " + post.getText() + "\n\n");

                            tvResult.append(content2.toString());
                        }
                    }
                },3000);
            }
        });

//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
////                getApiResults();
//            }
//        },3000);

    }

    private void getApiResults(){

        apiCalls = RetrofitInstance.getServices();

        Call<List<Post>> call = apiCalls.getPost();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                progressBar.setVisibility(View.GONE);

                if(!response.isSuccessful()){
                    tvResult.setText("Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for(Post post : posts){

//                    String content = "";
//                    content += "ID: " + post.getId() + "\n";
//                    content += "User Id: " + post.getUserId() + "\n";
//                    content += "Title: " + post.getTitle() + "\n";g
//                    content += "Body: " + post.getText() + "\n\n";

                    StringBuilder content2 = new StringBuilder();
                    content2.append("ID: " + post.getId() + "\n");
                    content2.append("User Id: " + post.getUserId() + "\n");
                    content2.append("Title: " + post.getTitle() + "\n");
                    content2.append("Body: " + post.getText() + "\n\n");

                    tvResult.append(content2.toString());
//                    content2.delete(0,content2.length());
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                tvResult.setText(t.getMessage());
            }
        });

    }
}

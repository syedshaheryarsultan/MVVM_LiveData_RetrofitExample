package com.zeroes.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.zeroes.retrofitexample.model.Post;
import com.zeroes.retrofitexample.viewmodel.PostViewModel;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;
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

    }

}

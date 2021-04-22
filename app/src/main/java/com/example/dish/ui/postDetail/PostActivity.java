package com.example.dish.ui.postDetail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dish.R;
import com.example.dish.ui.home.Post;
import com.google.android.material.progressindicator.LinearProgressIndicator;

public class PostActivity extends AppCompatActivity {
    private ImageView ivPostPicture;
    private TextView txtTitle, txtStart, txtEnd, txtDescription, txtLocation, txtHost, txtGoal, txtCurrentProgress;
    private Button btAccept, btShare, btDonate;
    private LinearProgressIndicator progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        initViews();
        //TODO: Get the data from recycler view
        // data for testing
        Post post = new Post("Amy", 1, "NEED FISH ON DISH", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).", "#FISH", "donation", "https", 99);
        setData(post);
    }

    private void setData(Post post) {
        txtTitle.setText(post.getTitle());
        txtDescription.setText(post.getBody());
        txtHost.setText(post.getCreator());
        txtGoal.setText(String.valueOf(post.getGoal()));
    }
    private void initViews() {
        ivPostPicture = findViewById(R.id.ivPostPicture);
        txtTitle = findViewById(R.id.txtTitle);
        txtStart = findViewById(R.id.txtStartingTime);
        txtEnd = findViewById(R.id.txtEndingTime);
        txtDescription = findViewById(R.id.txtDescription);
        txtLocation = findViewById(R.id.txtLocation);
        txtHost = findViewById(R.id.txtHost);
        txtGoal = findViewById(R.id.txtGoal);
        txtCurrentProgress = findViewById(R.id.txtCurrentProgress);
        btAccept = findViewById(R.id.btAccept);
        btDonate = findViewById(R.id.btDonate);
        btShare = findViewById(R.id.btShare);
        progressBar = findViewById(R.id.progressBar);
    }
}
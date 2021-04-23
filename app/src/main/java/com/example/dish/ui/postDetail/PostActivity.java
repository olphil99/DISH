package com.example.dish.ui.postDetail;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dish.R;
import com.example.dish.ui.home.Post;
import com.google.android.material.progressindicator.LinearProgressIndicator;

public class PostActivity extends AppCompatActivity {
    private ImageView ivPostPicture;
    private TextView txtTitle, txtStart, txtEnd, txtDescription, txtLocation, txtHost, txtGoal, txtCurrentProgress;
    private Button btAccept, btShare, btDonate;
    private LinearProgressIndicator progressBar;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        initViews();
        //TODO: Get the data from recycler view
        // data for testing
        Post post = new Post("Amy", 1, "NEED FISH ON DISH", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).", "#FISH", "event", "https", 99);
        setData(post);
        if(post.getType().equals("donation")) {
            btAccept.setVisibility(View.GONE);
        }
        else {
            btDonate.setVisibility(View.GONE);
        }

        btAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btAccept.getText().toString().equals("ACCEPT")) {
                    btAccept.setText("QUIT");
                    btAccept.setBackgroundColor(getResources().getColor(R.color.red));
                }
                else{
                    btAccept.setText("ACCEPT");
                    btAccept.setBackgroundColor(getResources().getColor(R.color.green));
                }

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setData(Post post) {
        txtTitle.setText(post.getTitle());
        txtDescription.setText(post.getBody());
        txtHost.setText(post.getCreator());
        if(post.getType().equals("donation")) {
            txtGoal.setText("$ " + String.valueOf(post.getGoal()));
            txtCurrentProgress.setText("$ 30");
        }
        else {
            txtGoal.setText(String.valueOf((int) post.getGoal()) + " volunteers");
            txtCurrentProgress.setText("500 people are going");
        }
        progressBar.setProgress(30, true);
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
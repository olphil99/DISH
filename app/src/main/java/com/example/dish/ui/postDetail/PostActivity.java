package com.example.dish.ui.postDetail;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dish.MainActivity;
import com.example.dish.R;
import com.example.dish.ui.home.Post;
import com.example.dish.ui.home.Utils;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class PostActivity extends AppCompatActivity {
    private ImageView ivPostPicture;
    private TextView txtTitle, txtStart, txtEnd, txtDescription, txtLocation, txtHost, txtGoal, txtCurrentProgress;
    private Button btAccept, btShare, btDonate;
    private LinearProgressIndicator progressBar;
    private ProgressBar progressBar2;
    private TextInputLayout txtInputLayout;
    private TextInputEditText txtInputAmount;


    private int prog;
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        initViews();
        Intent intent = getIntent();
        if(intent != null) {
            int postId = intent.getIntExtra("id", -1);
            Post post = Utils.getInstance().getPostById(postId);
            if (post != null) {
                setData(post);
                if (post.getType().equals("donation")) {
                    btAccept.setVisibility(View.GONE);
                    ivPostPicture.setImageResource(R.mipmap.donation);
                } else {
                    btDonate.setVisibility(View.GONE);
                    txtInputLayout.setVisibility(View.GONE);
                    ivPostPicture.setImageResource(R.mipmap.vlt);
                    handleAcceptedPost(post);
                }

                btAccept.setOnClickListener(v -> {
                    if (btAccept.getText().toString().equals("ACCEPT")) {
                        btAccept.setText("QUIT");
                        btAccept.setBackgroundColor(getResources().getColor(R.color.red));
                        if(Utils.getInstance().addToRegisteredPosts(post))
                            Toast.makeText(PostActivity.this, "Registered", Toast.LENGTH_SHORT);
                        else
                            Toast.makeText(PostActivity.this, "Err...something wrong", Toast.LENGTH_SHORT);

                    } else {
                        btAccept.setText("ACCEPT");
                        btAccept.setBackgroundColor(getResources().getColor(R.color.teal_200));
                        if(Utils.getInstance().removeRegisteredPosts(post))
                            Toast.makeText(PostActivity.this, "Removed", Toast.LENGTH_SHORT);
                        else
                            Toast.makeText(PostActivity.this, "Err...something wrong", Toast.LENGTH_SHORT);
                    }
                    txtCurrentProgress.setText((int) post.getCurrentProgress() + " people are going");
                    prog = (int) (post.getCurrentProgress() / post.getGoal() * 100);
                    progressBar.setProgress(prog, true);
                });

                btDonate.setOnClickListener(v -> {
                    if (txtInputAmount.getText().toString().length() > 0) {
                        double amount = Double.parseDouble(String.valueOf(txtInputAmount.getText()));
                        txtInputAmount.setText("");
                        post.setCurrentProgress(post.getCurrentProgress() + amount);
                        prog = (int) (post.getCurrentProgress() / post.getGoal() * 100);
                        txtCurrentProgress.setText("$" + post.getCurrentProgress());
                        progressBar.setProgress(prog, true);
                    }
                    else {
                        Toast.makeText(PostActivity.this, "Please be nice!", Toast.LENGTH_SHORT).show();
                    }
                });

                btShare.setOnClickListener(v -> Toast.makeText(PostActivity.this, "SHARED", Toast.LENGTH_SHORT).show());
            }
        }
    }

    private void handleAcceptedPost(Post post) {
        ArrayList<Post> acceptedPosts = Utils.getInstance().getRegisteredPosts();
        boolean alreadyAccepted = false;
        for(Post p : acceptedPosts) {
            if(p.getID() == post.getID()) {
                alreadyAccepted = true;
            }
        }
        if(alreadyAccepted) {
            btAccept.setText("QUIT");
            btAccept.setBackgroundColor(getResources().getColor(R.color.red));
        }
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setData(Post post) {
        txtTitle.setText(post.getTitle());
        txtDescription.setText(post.getBody());
        txtHost.setText(post.getCreator());
        txtStart.setText(post.getStart());
        txtEnd.setText(post.getEnd());
        if(post.getType().equals("donation")) {
            txtGoal.setText("$" + post.getGoal());
            txtCurrentProgress.setText("$" + post.getCurrentProgress());
        }
        else {
            txtGoal.setText((int) post.getGoal() + " volunteers");
            txtCurrentProgress.setText((int) post.getCurrentProgress() + " people are going");
        }
        prog = (int) (post.getCurrentProgress() / post.getGoal() * 100);
        progressBar.setProgress(prog, true);
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
        txtInputLayout = findViewById(R.id.oTxtFieldAmount);
        txtInputAmount = findViewById(R.id.txtInputAmount);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PostActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
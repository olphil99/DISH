package com.example.dish.ui.postDetail;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class PostActivity extends AppCompatActivity {
    private ImageView ivPostPicture;
    private TextView txtTitle, txtStart, txtEnd, txtDescription, txtLocation, txtHost, txtGoal, txtCurrentProgress;
    private Button btAccept, btShare, btDonate;
    private LinearProgressIndicator progressBar;
    private TextInputLayout txtInputLayout;
    private TextInputEditText txtInputAmount;

    private int prog;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        initViews();

        String d = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).";
        Intent intent = getIntent();
        if(intent != null) {

            String title = intent.getStringExtra("title");
            double goal = intent.getDoubleExtra("goal", 0);
            String type = intent.getStringExtra("type");
            String creator = intent.getStringExtra("creator");
            Post post = new Post(creator, 1, title, d,"#FISH", type, "https", goal );
            setData(post);
            if(post.getType().equals("donation")) {
                btAccept.setVisibility(View.GONE);
            }
            else {
                btDonate.setVisibility(View.GONE);
                txtInputLayout.setVisibility(View.GONE);
            }

            btAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(btAccept.getText().toString().equals("ACCEPT")) {
                        btAccept.setText("QUIT");
                        btAccept.setBackgroundColor(getResources().getColor(R.color.red));
                        post.setCurrentProgress(post.getCurrentProgress() + 1);
                        txtCurrentProgress.setText(String.valueOf((int)post.getCurrentProgress()) + " people are going");
                    }
                    else{
                        btAccept.setText("ACCEPT");
                        btAccept.setBackgroundColor(getResources().getColor(R.color.teal_200));
                        post.setCurrentProgress(post.getCurrentProgress() - 1);
                        txtCurrentProgress.setText(String.valueOf((int)post.getCurrentProgress()) + " people are going");
                    }
                    prog = (int) (post.getCurrentProgress()/post.getGoal() * 100);
                    progressBar.setProgress(prog, true);
                }
            });

            btDonate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Double amount = Double.valueOf(String.valueOf(txtInputAmount.getText()));
                    txtInputAmount.setText("");
                    post.setCurrentProgress(post.getCurrentProgress() + amount);
                    prog = (int) (post.getCurrentProgress()/post.getGoal() * 100);
                    txtCurrentProgress.setText("$"+String.valueOf(post.getCurrentProgress()));
                    progressBar.setProgress(prog, true);
                }
            });
            //TODO: Handle the btShare

            btShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(PostActivity.this, "SHARE SELECTED", Toast.LENGTH_SHORT);
                }
            });
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setData(Post post) {
        txtTitle.setText(post.getTitle());
        txtDescription.setText(post.getBody());
        txtHost.setText(post.getCreator());
        if(post.getType().equals("donation")) {
            txtGoal.setText("$" + String.valueOf(post.getGoal()));
            txtCurrentProgress.setText("$" + String.valueOf(post.getCurrentProgress() ));
        }
        else {
            txtGoal.setText(String.valueOf((int) post.getGoal()) + " volunteers");
            txtCurrentProgress.setText(String.valueOf((int)post.getCurrentProgress()) + " people are going");
        }
        prog = (int)(post.getCurrentProgress()/post.getGoal());
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
}
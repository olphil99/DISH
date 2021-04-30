package com.example.dish.ui.dashboard;

import android.os.Bundle;

import com.example.dish.ui.home.Post;
import com.example.dish.ui.home.PostsRecViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.example.dish.R;

import java.util.ArrayList;

public class GoalActivity extends AppCompatActivity {

    private RecyclerView goalRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        goalRecView = this.findViewById(R.id.goalRv);
        ArrayList<String> goals = new ArrayList<>();
        goals.add("Donate to BLM");
        goals.add("Donate $100 this year");

        GoalAdapater adapter = new GoalAdapater(goals);
        goalRecView.setAdapter(adapter);
        goalRecView.setLayoutManager(new LinearLayoutManager(this));
    }
}
package com.example.dish.ui.dashboard;

import android.content.Context;
import android.os.Bundle;

import com.example.dish.MainActivity;
import com.example.dish.ui.home.Post;
import com.example.dish.ui.home.PostsRecViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dish.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class GoalActivity extends AppCompatActivity {

    private RecyclerView goalRecView;
    boolean click = true;
    PopupWindow popUp;
    Button saveBtn;
    TextInputEditText text;
    RelativeLayout rl;
    ArrayList<String> goals = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        popUp = new PopupWindow(this);
        rl = (RelativeLayout) findViewById(R.id.rl);
        LayoutInflater layoutInflater = (LayoutInflater) GoalActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.new_goal,null);
        saveBtn = (Button) customView.findViewById(R.id.newTaskButton);
        text = (TextInputEditText) customView.findViewById(R.id.goal);
        goals.add("Donate to BLM");
        goals.add("Donate $100 this year");



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUp = new PopupWindow(customView, Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
                popUp.showAtLocation(view, Gravity.CENTER, 0, 0);
                popUp.setFocusable(true);
                popUp.update();

                saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goals.add((text.getText().toString()));
                        popUp.dismiss();
                    }
                });
            }
        });


        goalRecView = this.findViewById(R.id.goalRv);
        GoalAdapater adapter = new GoalAdapater(goals);
        goalRecView.setAdapter(adapter);
        goalRecView.setLayoutManager(new LinearLayoutManager(this));
    }
}
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

public class DonationsActivity extends AppCompatActivity {
    private RecyclerView donationsRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donations);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        donationsRv = this.findViewById(R.id.donationsRv);
        ArrayList<Donation> donations = new ArrayList<>();
        donations.add(new Donation("BLM", "10/04/2020", "$100.00"));
        donations.add(new Donation("Food Bank", "1/24/2021", "$150.00"));


        DonationsAdapter adapter = new DonationsAdapter(donations);
        donationsRv.setAdapter(adapter);
        donationsRv.setLayoutManager(new LinearLayoutManager(this));
    }
}
package com.example.dish.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dish.R;
import com.example.dish.ui.createPost.CreatePostActivity;
import com.example.dish.ui.login.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    //private HomeViewModel homeViewModel;
    private RecyclerView postsRecView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        postsRecView = root.findViewById(R.id.postsRecView);

        PostsRecViewAdapter adapter = new PostsRecViewAdapter(root.getContext());
        adapter.setPosts(Utils.getInstance().getAllPosts());
        postsRecView.setAdapter(adapter);
        postsRecView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        // create/add post button
        FloatingActionButton addPostButton = root.findViewById(R.id.add_post_button);
        addPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), CreatePostActivity.class));
            }
        });

        return root;
    }
}
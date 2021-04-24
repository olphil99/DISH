package com.example.dish.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dish.R;

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
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post("Amy", 1, "NEED FISH ON DISH", "This is a very long description", "#FISH", "donation", "https", 99));
        posts.add(new Post("Food Bank", 2, "FOOD FOR MOOD", "This is a very long description", "#FOOD", "event", "https", 100));
        posts.add(new Post("TTP", 3, "SHOW ME THE MONEY", "This is a very long description", "#MONEY", "donation", "https", 10000));
        posts.add(new Post("Brian", 4, "FREE MASKS", "This is a very long description", "#MASK", "event", "https", 10));
        posts.add(new Post("Emily", 5, "STUDIO", "This is a very long description", "#AAA", "event", "https", 10));
        posts.add(new Post("Emily2", 6, "TEST", "This is a very long description", "#AAA", "event", "https", 10));

        PostsRecViewAdapter adapter = new PostsRecViewAdapter(root.getContext());
        adapter.setPosts(posts);
        postsRecView.setAdapter(adapter);
        postsRecView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        return root;
    }
}
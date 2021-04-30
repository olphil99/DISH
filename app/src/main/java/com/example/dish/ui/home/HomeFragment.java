package com.example.dish.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dish.R;
import com.example.dish.ui.createPost.CreatePostActivity;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        // set filters invisible by default
        ChipGroup filterCategories = root.findViewById(R.id.filter_chip_group);
        filterCategories.setVisibility(View.GONE);
        // filter dropdown
        Button categoryDropdown = root.findViewById(R.id.filter_categories_dropdown);
        categoryDropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filterCategories.getVisibility() == View.GONE) {
                    filterCategories.setVisibility(View.VISIBLE);
                    categoryDropdown.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_up_black, 0);
                }
                else if (filterCategories.getVisibility() == View.VISIBLE) {
                    filterCategories.setVisibility(View.GONE);
                    categoryDropdown.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_down_black, 0);
                }
            }
        });

        return root;
    }
}
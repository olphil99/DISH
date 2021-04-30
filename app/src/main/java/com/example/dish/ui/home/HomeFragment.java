package com.example.dish.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dish.R;
import com.example.dish.ui.createPost.CreatePostActivity;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
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

        // tag filter
        ArrayList<Boolean> tagFilterBooleanList = new ArrayList<>();
        ChipGroup categories =  root.findViewById(R.id.filter_category_group);
        for (int i = 0; i < categories.getChildCount(); i++) {
            tagFilterBooleanList.add(false);
            Chip c = (Chip) categories.getChildAt(i);
            c.setTag(i);
            c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton button, boolean b) {
                    int tag = (int) button.getTag();
                    tagFilterBooleanList.set(tag, b);
                }
            });
        }
        Chip donationFilter = root.findViewById(R.id.filter_chip_donation);
        Chip eventFilter = root.findViewById(R.id.filter_chip_event);
        Button applyFilters = root.findViewById(R.id.apply_filter_button);
        applyFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Post> allPosts = Utils.getInstance().getAllPosts();
                ArrayList<Post> filteredPosts = new ArrayList<Post>();
                // check if any categories selected
                Boolean hasCategories = false;
                for (int i = 0; i < tagFilterBooleanList.size(); i++) {
                    if (tagFilterBooleanList.get(i))
                        hasCategories = true;
                    if (hasCategories)
                        break;
                }
                for (int i = 0; i < allPosts.size(); i++) {

                    for (int j = 0; j < tagFilterBooleanList.size(); j++) {
                        if (tagFilterBooleanList.get(j) && allPosts.get(i).getTags().get(j)) {
                            if (allPosts.get(i).getType().equals("donation") && donationFilter.isChecked())
                                filteredPosts.add(allPosts.get(i));
                            else if (allPosts.get(i).getType().equals("event") && eventFilter.isChecked())
                                filteredPosts.add(allPosts.get(i));
                            else if (!donationFilter.isChecked() && !eventFilter.isChecked()) {
                                filteredPosts.add(allPosts.get(i));
                            }
                        }
                    }
                    // filter donations
//                    if (allPosts.get(i).getType().equals("donation") && donationFilter.isChecked()) {
//                        for (int j = 0; j < tagFilterBooleanList.size(); j++) {
//                            if (tagFilterBooleanList.get(j) && allPosts.get(i).getTags().get(j)) {
//                                filteredPosts.add(allPosts.get(i));
//                            }
//                        }
//                    }
//                    // filter events
//                    if (allPosts.get(i).getType().equals("event") && eventFilter.isChecked()) {
//                        for (int j = 0; j < tagFilterBooleanList.size(); j++) {
//                            if (tagFilterBooleanList.get(j) && allPosts.get(i).getTags().get(j)) {
//                                filteredPosts.add(allPosts.get(i));
//                            }
//                        }
//                    }
                    // no categories selected
                    if (!hasCategories) {
                        if (allPosts.get(i).getType().equals("donation") && donationFilter.isChecked())
                            filteredPosts.add(allPosts.get(i));
                        else if (allPosts.get(i).getType().equals("event") && eventFilter.isChecked())
                            filteredPosts.add(allPosts.get(i));
                    }
                    // no post type selected
//                    if (!donationFilter.isChecked() && !eventFilter.isChecked()) {
//                        for (int j = 0; j < tagFilterBooleanList.size(); j++) {
//                            if (tagFilterBooleanList.get(j) && allPosts.get(i).getTags().get(j)) {
//                                if (!postAdded) {
//                                    filteredPosts.add(allPosts.get(i));
//                                }
//                            }
//                        }
//                    }
                }
                adapter.setPosts(filteredPosts);
                postsRecView.setAdapter(adapter);
                postsRecView.setLayoutManager(new LinearLayoutManager(root.getContext()));
            }
        });

        // create/add post button
        FloatingActionButton addPostButton = root.findViewById(R.id.add_post_button);
        addPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), CreatePostActivity.class));
            }
        });

        // set filters invisible by default
        LinearLayout filterType = root.findViewById(R.id.filter_type_section);
        LinearLayout filterCategories = root.findViewById(R.id.filter_category_section);
        filterType.setVisibility(View.GONE);
        filterCategories.setVisibility(View.GONE);
        // filter dropdown
        Button categoryDropdown = root.findViewById(R.id.filter_categories_dropdown);
        categoryDropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filterCategories.getVisibility() == View.GONE) {
                    filterType.setVisibility(View.VISIBLE);
                    filterCategories.setVisibility(View.VISIBLE);
                    categoryDropdown.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_up_black, 0);
                }
                else if (filterCategories.getVisibility() == View.VISIBLE) {
                    filterType.setVisibility(View.GONE);
                    filterCategories.setVisibility(View.GONE);
                    categoryDropdown.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_down_black, 0);
                }
            }
        });

        return root;
    }
}
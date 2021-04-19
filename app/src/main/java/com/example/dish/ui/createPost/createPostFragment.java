package com.example.dish.ui.createPost;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dish.R;


public class createPostFragment extends Fragment {

    private createPostViewModel createPostVM;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        createPostVM =
                new ViewModelProvider(this).get(createPostViewModel.class);
        View root = inflater.inflate(R.layout.fragment_create_post, container, false);
        /*
        final TextView textView = root.findViewById(R.id.create_post);
        createPostVM.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}

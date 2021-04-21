package com.example.dish.ui.forum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dish.R;

public class ForumFragment extends Fragment {

    private ForumViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(ForumViewModel.class);
        View root = inflator.inflate(R.layout.fragment_forum, container, false);
        return root;
    }
}

package com.example.dish.ui.forum;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dish.R;

public class ForumFragment extends Fragment {

    private ForumViewModel viewModel;

    private String[] data;

    public View onCreateView(@NonNull LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(ForumViewModel.class);
        View root = inflator.inflate(R.layout.fragment_forum, container, false);

        // FETCH DB ELEMENTS
        data = new String[] {"forum1", "forum2", "forum3"};

        // ADD DB ELEMENTS DYNAMICALLY
        LinearLayout forumMain = root.findViewById(R.id.forumMain);
        for (int i = 0; i < data.length; i++) {
            String forumTitle = data[i];
            TextView tv = new TextView(getActivity());
            // layout params
            float factor = getContext().getResources().getDisplayMetrics().density;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) (300 * factor), (int)(60 * factor));
            layoutParams.setMargins(0, (int) (20 * factor), 0, 0);
            tv.setLayoutParams(layoutParams);

            // other params
            tv.setText(forumTitle);
            tv.setTextSize(18);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundResource(R.drawable.forum_border);
            tv.setTextColor(Color.BLACK);
            tv.setClickable(true);
            tv.setFocusable(true);

            // add to screen
            forumMain.addView(tv);
        }

        return root;
    }
}

package com.example.dish.ui.forum;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dish.R;
import com.example.dish.ui.forum.forumActivity;

import org.w3c.dom.Text;

public class ForumFragment extends Fragment {

    private ForumViewModel viewModel;
    private String forumTitle;
    private String[] allForums, subedForums;

    public View onCreateView(@NonNull LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(ForumViewModel.class);
        View root = inflator.inflate(R.layout.fragment_forum, container, false);

        // FETCH DB ELEMENTS
        subedForums = new String[]{"Champaign Humane Society", "Food Bank", "BLM"};
        allForums = new String[]{"forum1", "forum2", "forum3"};

        // ADD DB ELEMENTS DYNAMICALLY
        LinearLayout forumSubscribed = root.findViewById(R.id.subscribed_forums);
        for (int i = 0; i < subedForums.length; i++) {
            forumTitle = subedForums[i];
            // add to screen
            forumSubscribed.addView(createForum(forumTitle));
        }
        LinearLayout forumMain = root.findViewById(R.id.forumMain);
        for (int i = 0; i < allForums.length; i++) {
            forumTitle = allForums[i];
            // add to screen
            forumMain.addView(createForum(forumTitle));
        }

        return root;
    }

    protected TextView createForum(String forumTitle) {
        TextView tv = new TextView(getActivity());
        // layout params
        float factor = getContext().getResources().getDisplayMetrics().density;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) (300 * factor), (int) (60 * factor));
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

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendM = new Intent(v.getContext(), forumActivity.class);
                sendM.putExtra("name", forumTitle);
                startActivity(sendM);
            }
        });

        return tv;
    }
}

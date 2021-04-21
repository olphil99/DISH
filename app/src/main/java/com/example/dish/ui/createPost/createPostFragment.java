package com.example.dish.ui.createPost;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dish.R;


public class createPostFragment extends Fragment {
    //private createPostViewModel createPostVM;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //createPostVM = new ViewModelProvider(this).get(createPostViewModel.class);
        View root = inflater.inflate(R.layout.fragment_create_post, container, false);
        /*
        final TextView textView = root.findViewById(R.id.create_post);
        createPostVM.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        final RadioGroup postTypeRadioGroup = root.findViewById(R.id.rgDonationEvent);
        final RadioButton donationButton = root.findViewById(R.id.rbDonation);
        final RadioButton eventButton = root.findViewById(R.id.rbEvent);
        final LinearLayout postEntries = (LinearLayout) root.findViewById(R.id.create_post_entries);
        final LinearLayout donationEntries = (LinearLayout) root.findViewById(R.id.create_post_donation_entries);
        final LinearLayout eventEntries = (LinearLayout) root.findViewById(R.id.create_post_event_entries);
        // donation selected by default
        donationEntries.setVisibility(View.VISIBLE);
        eventEntries.setVisibility(View.GONE);
        donationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(root.getContext(), "clicked donation", Toast.LENGTH_LONG).show();
                if (donationEntries.getVisibility() != View.VISIBLE) {
                    donationEntries.setVisibility(View.VISIBLE);
                    eventEntries.setVisibility(View.GONE);
                }
            }
        });
        eventButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(root.getContext(), "clicked event", Toast.LENGTH_LONG).show();
                if (eventEntries.getVisibility() != View.VISIBLE) {
                    eventEntries.setVisibility(View.VISIBLE);
                    donationEntries.setVisibility(View.GONE);
                }
            }
        });


        final Button btCreatePost = root.findViewById(R.id.btCreatePost);
        btCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(root.getContext(), "Posted", Toast.LENGTH_LONG).show();
            }
        });
        return root;
    }
}

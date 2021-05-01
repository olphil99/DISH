package com.example.dish.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dish.R;
import com.example.dish.ui.login.LoginActivity;
import com.example.dish.ui.login.LoginData;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    ProgressBar progressBar;
    Button btnGoals;
    Button btnDonations;
    int count=0;

    @Override
    public void onAttachFragment(@NonNull Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        btnGoals = (Button) root.findViewById(R.id.btnGoals);
        btnDonations = (Button) root.findViewById(R.id.btnDonations);

        ArrayList<String> usernames = LoginData.getInstance().getUsernames();
        String username = usernames.get(usernames.size()-1);
        TextView tvUsername = root.findViewById(R.id.user_profile_name);
        tvUsername.setText(username);

//        GridView gridview = (GridView) root.findViewById(R.id.Gv);
//        gridview.setAdapter(new ImageAdapter(this.getActivity()));

        btnGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GoalActivity.class);
                startActivity(intent);
            }
        });

        btnDonations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DonationsActivity.class);
                startActivity(intent);
            }
        });

        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

        Button logout = (Button) root.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                Toast.makeText(view.getContext(), "you are now logged out ^_^", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

//            button.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            count=count+10;
//            progressBar.setProgress(count);
//        }
//    });
//        return registrationFragmentView;
}
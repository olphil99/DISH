package com.example.dish.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dish.R;

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

//        GridView gridview = (GridView) root.findViewById(R.id.Gv);
//        gridview.setAdapter(new ImageAdapter(this.getActivity()));

        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

//        FloatingActionButton loginButton = root.findViewById(R.id.loginButton);
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(v.getContext(), LoginActivity.class));
//            }
//        });

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
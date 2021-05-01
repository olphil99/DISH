package com.example.dish.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dish.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

import android.widget.TextView;

public class DonationsAdapter extends RecyclerView.Adapter<DonationsAdapter.ViewHolder> {
    private ArrayList<Donation> localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView date;
        private final TextView amount;


        public TextView getName() {
            return name;
        }

        public TextView getDate() {
            return date;
        }

        public TextView getAmount() {
            return amount;
        }

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            name = (TextView) view.findViewById(R.id.name);
            date = (TextView) view.findViewById(R.id.date);
            amount = (TextView) view.findViewById(R.id.amount);
        }


    }


    public DonationsAdapter(ArrayList<Donation> donations) {
        localDataSet = donations;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.donation_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getName().setText(localDataSet.get(position).getName());
        viewHolder.getDate().setText(localDataSet.get(position).getDate());
        viewHolder.getAmount().setText("$"+Float.toString(localDataSet.get(position).getAmount()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

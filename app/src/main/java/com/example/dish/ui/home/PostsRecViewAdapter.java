package com.example.dish.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dish.R;
import com.example.dish.ui.postDetail.PostActivity;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.ArrayList;
import java.util.Objects;

public class PostsRecViewAdapter extends RecyclerView.Adapter<PostsRecViewAdapter.ViewHolder> {

    private ArrayList<Post> posts;
    private final Context context;
    public PostsRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.posts_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(posts.get(position).getTitle());
        if(posts.get(position).getType().equals("donation")) {
            holder.tvGoal.setText("$" + posts.get(position).getGoal());
            holder.imgView.setImageResource(R.mipmap.donation);
        }
        else{
            holder.tvGoal.setText((int) posts.get(position).getGoal() + " persons");
            holder.imgView.setImageResource(R.mipmap.vlt);
        }
        holder.tvDueDate.setText(posts.get(position).getEnd());
        holder.parent.setOnClickListener(v -> {
            Intent intent = new Intent(context, PostActivity.class);
            intent.putExtra("id", posts.get(position).getID());
            context.startActivity(intent);
            notifyDataSetChanged();
        });
        int prog = (int) (posts.get(position).getCurrentProgress() / posts.get(position).getGoal() * 100);
        Objects.requireNonNull(holder).progressBar.setProgress(prog);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitle;
        private final TextView tvGoal;
        private final TextView tvDueDate;
        private final CardView parent;
        private final LinearProgressIndicator progressBar;
        private final ImageView imgView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //TODO : initialize more textview
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvGoal = itemView.findViewById(R.id.tvGoal);
            parent = itemView.findViewById(R.id.cvPost);
            progressBar = itemView.findViewById(R.id.progressBar2);
            tvDueDate = itemView.findViewById(R.id.tvDueDate);
            imgView = itemView.findViewById(R.id.postImage);
        }
    }
}

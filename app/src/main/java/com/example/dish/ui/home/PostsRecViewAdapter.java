package com.example.dish.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dish.R;
import com.example.dish.ui.postDetail.PostActivity;

import java.util.ArrayList;

public class PostsRecViewAdapter extends RecyclerView.Adapter<PostsRecViewAdapter.ViewHolder> {

    private ArrayList<Post> posts = new ArrayList<>();
    private Context context;
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
        //TODO: setText for more textView
        holder.tvTitle.setText(posts.get(position).getTitle());
        holder.tvGoal.setText(String.valueOf(posts.get(position).getGoal()));
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PostActivity.class);
                intent.putExtra("id", posts.get(position).getID());
                context.startActivity(intent);
            }
        });
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
        private TextView tvTitle, tvGoal;
        private CardView parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //TODO : initialize more textview
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvGoal = itemView.findViewById(R.id.tvGoal);
            parent = itemView.findViewById(R.id.cvPost);
        }
    }
}

package com.example.dish.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.dish.R;
import com.example.dish.ui.home.Post;
import com.example.dish.ui.home.Utils;
import com.example.dish.ui.postDetail.PostActivity;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Post> ids = new ArrayList<>();
    private Utils utils = new Utils();

    // Constructor
    public ImageAdapter(Context c) {
        mContext = c;
        ids.add(utils.getPostById(5));
        ids.add(utils.getPostById(6));
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(400, 400));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(2, 2, 2, 2);
        }
        else
        {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PostActivity.class);
                intent.putExtra("id", ids.get(position).getID());
                mContext.startActivity(intent);
                notifyDataSetChanged();
            }
        });
        return imageView;
    }

    // Keep all Images in array
    public Integer[] mThumbIds = new Integer[]{
            R.mipmap.donation, R.mipmap.vlt
    };
}

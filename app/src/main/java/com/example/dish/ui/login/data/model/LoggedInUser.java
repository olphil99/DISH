package com.example.dish.ui.login.data.model;
import com.example.dish.ui.home.Post;
import com.example.dish.ui.home.Utils;

import java.util.ArrayList;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userId;
    private String displayName;
    private ArrayList<Post> posts;

    private ArrayList<Post> getPostByUser(String user) {
        ArrayList<Post> posts = new ArrayList<Post>();
        for (Post p : Utils.getAllPosts()) {
            if (p.getCreator() == user) {
                posts.add(p);
            }
        }
        return posts;
    }

    public LoggedInUser(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
        this.posts = getPostByUser(displayName);
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
}
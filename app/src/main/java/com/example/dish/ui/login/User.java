package com.example.dish.ui.login;
import com.example.dish.ui.home.Post;
import com.example.dish.ui.home.Utils;

import java.util.ArrayList;


public class User {

    private String username;
    private String password;
//    private ArrayList<Post> posts;

//    private ArrayList<Post> getPostByUser(String user) {
//        ArrayList<Post> posts = new ArrayList<Post>();
//        for (Post p : Utils.getAllPosts()) {
//            if (p.getCreator() == user) {
//                posts.add(p);
//            }
//        }
//        return posts;
//    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
//        this.posts = getPostByUser(username);
    }

    public String getUsername() {
        return username;
    }

//    public ArrayList<Post> getPosts() {
//        return posts;
//    }
}
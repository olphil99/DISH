package com.example.dish.ui.home;

import java.util.ArrayList;

public class Utils {
    private static Utils instance;
    private static ArrayList<Post> allPosts;
    private static ArrayList<Post> registeredPosts;

    public Utils() {
        if (allPosts == null) {
            allPosts = new ArrayList<>();
            initData();
        }
        if(registeredPosts == null) {
            registeredPosts = new ArrayList<>();
        }
    }

    private void initData() {
        String d = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).";
        ArrayList<Boolean> tag1 = new ArrayList<Boolean>() {{add(true);add(false);add(true);add(false);add(false);add(false);add(false);add(false);add(false);add(false);add(false);}};
        ArrayList<Boolean> tag2 = new ArrayList<Boolean>() {{add(false);add(false);add(false);add(false);add(false);add(false);add(true);add(false);add(false);add(false);add(false);}};
        ArrayList<Boolean> tag3 = new ArrayList<Boolean>() {{add(false);add(false);add(false);add(false);add(false);add(false);add(false);add(true);add(false);add(false);add(false);}};
        ArrayList<Boolean> tag4 = new ArrayList<Boolean>() {{add(false);add(false);add(true);add(false);add(false);add(false);add(false);add(false);add(false);add(false);add(false);}};
        ArrayList<Boolean> tag5 = new ArrayList<Boolean>() {{add(false);add(false);add(false);add(false);add(false);add(false);add(false);add(false);add(false);add(false);add(true);}};
        ArrayList<Boolean> tag6 = new ArrayList<Boolean>() {{add(false);add(false);add(false);add(false);add(false);add(false);add(false);add(false);add(false);add(false);add(true);}};
        allPosts.add(new Post("Amy", 1, "NEED FISH ON DISH", d, tag1, "donation", null, 99, "05/13/2021", "05/13/2021"));
        allPosts.add(new Post("Food Bank", 2, "FOOD FOR MOOD", d, tag2, "event", null, 100, "02/16/2021", "05/16/2021"));
        allPosts.add(new Post("TTP", 3, "SHOW ME THE MONEY", "This is a very long description", tag3, "donation", null, 10000));
        allPosts.add(new Post("Brian", 4, "FREE MASKS", "This is a very long description", tag4, "event", null, 10));
        allPosts.add(new Post("Emily", 5, "STUDIO", "This is a very long description", tag5, "event", null, 10));
        allPosts.add(new Post("Emily2", 6, "TEST", "This is a very long description", tag6, "event", null, 10));
    }

    public static Utils getInstance() {
        if(instance != null) {
            return instance;
        }
        else {
            instance = new Utils();
            return  instance;
        }
    }

    public static ArrayList<Post> getAllPosts() {
        return allPosts;
    }

    public static ArrayList<Post> getRegisteredPosts() {
        return registeredPosts;
    }

    public Post getPostById (int id) {
        for (Post p: allPosts) {
            if(p.getID() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean addToRegisteredPosts(Post post) {
        post.setCurrentProgress(post.getCurrentProgress() + 1);
        return registeredPosts.add(post);
    }
    public boolean removeRegisteredPosts(Post post) {
        post.setCurrentProgress(post.getCurrentProgress() - 1);
        return registeredPosts.remove(post); }

    public int totalPosts() {
        return allPosts.size();
    }

    public boolean addNewPost(Post post) {
        return allPosts.add(post);
    }
}

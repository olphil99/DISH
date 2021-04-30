package com.example.dish.ui.home;

import android.net.Uri;

import java.util.ArrayList;

public class Post {
    private String creator;
    private int ID;
    private String title;
    private String body;
    private ArrayList<Boolean> tags;
    private String type;
    private Uri picture_url;
    private double goal;
    private double currentProgress;
    private String start;
    private String end;
    private String postUrl;
    private String location;
  
    public Post(String creator, int ID, String title, String body, ArrayList<Boolean> tags, String type, Uri picture_url, double goal) {
        this.creator = creator;
        this.ID = ID;
        this.title = title;
        this.body = body;
        this.tags = tags;
        this.type = type;
        this.picture_url = picture_url;
        this.goal = goal;
        this.currentProgress = 0;
        this.start = "xx/xx/xx";
        this.end = "xx/xx/xx";
        this.postUrl = "";
        this.location = "UIUC";
    }
    public Post(String creator, int ID, String title, String body, ArrayList<Boolean> tags, String type, Uri picture_url, double goal, String start, String end) {
        this.creator = creator;
        this.ID = ID;
        this.title = title;
        this.body = body;
        this.tags = tags;
        this.type = type;
        this.picture_url = picture_url;
        this.goal = goal;
        this.currentProgress = 0;
        this.start = start;
        this.end = end;
        this.postUrl = "";
        this.location = "UIUC";
    }


    public Post(String creator, int ID, String title, String body, ArrayList<Boolean> tags, String type, Uri picture_url, double goal, String start, String end, String postUrl , String location) {
        this.creator = creator;
        this.ID = ID;
        this.title = title;
        this.body = body;
        this.tags = tags;
        this.type = type;
        this.picture_url = picture_url;
        this.goal = goal;
        this.currentProgress = 0;
        this.start = "xx/xx/xx";
        this.end = "xx/xx/xx";
        this.postUrl = postUrl;
        this.location = location;
    }
    public PostString creator, int ID, String title, String body, ArrayList<Boolean> tags, String type, Uri picture_url, double goal, String start, String end, String postUrl , String location) {
        this.creator = creator;
        this.ID = ID;
        this.title = title;
        this.body = body;
        this.tags = tags;
        this.type = type;
        this.picture_url = picture_url;
        this.goal = goal;
        this.currentProgress = 0;
        this.start = start;
        this.end = end;
        this.postUrl = "";
        this.start = start;
        this.end = end;
        this.postUrl = postUrl;
        this.location = location;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTags(ArrayList<Boolean> tags) {
        this.tags = tags;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPicture_url(Uri picture_url) {
        this.picture_url = picture_url;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getCreator() {
        return creator;
    }

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public ArrayList<Boolean> getTags() {
        return tags;
    }

    public String getType() {
        return type;
    }

    public Uri getPicture_url() {
        return picture_url;
    }

    public double getGoal() {
        return goal;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getPostUrl() {
        return postUrl;
    }


    public String getLocation() {
        return location;
    }


    public double getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(double currentProgress) {
        this.currentProgress = currentProgress;
    }
}

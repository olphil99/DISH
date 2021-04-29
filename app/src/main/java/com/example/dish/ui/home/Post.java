package com.example.dish.ui.home;

public class Post {
    private String creator;
    private int ID;
    private String title;
    private String body;
    private String tags;
    private String type;
    private String picture_url;
    private double goal;
    private double currentProgress;
    private String start;
    private String end;
    private String postUrl;


    public Post(String creator, int ID, String title, String body, String tags, String type, String picture_url, double goal) {
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
    }
    public Post(String creator, int ID, String title, String body, String tags, String type, String picture_url, double goal, String start, String end) {
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

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPicture_url(String picture_url) {
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

    public String getTags() {
        return tags;
    }

    public String getType() {
        return type;
    }

    public String getPicture_url() {
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


    public double getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(double currentProgress) {
        this.currentProgress = currentProgress;
    }
}

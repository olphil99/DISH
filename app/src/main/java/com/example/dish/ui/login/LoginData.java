package com.example.dish.ui.login;


import com.example.dish.ui.home.Utils;

import java.util.ArrayList;

public class LoginData {

    private static LoginData instance;
    private static ArrayList<User> users;

    private ArrayList<String> getUsernames() {
        ArrayList<String> usernames = new ArrayList<String>();
        for (User u: users) {
            usernames.add(u.getUsername());
        }

        return usernames;
    }

    public static LoginData getInstance() {
        if (instance != null) {
            return instance;
        }
        else {
            instance = new LoginData();
            return  instance;
        }
    }

    private User getUser(String username) {
        for (User u: users) {
            if (u.getUsername() == username) {
                return u;
            }
        }
        return null;
    }

    public boolean addUser(User u) {
        users.add(u);
        return true;
    }
}


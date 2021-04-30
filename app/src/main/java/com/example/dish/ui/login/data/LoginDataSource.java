package com.example.dish.ui.login.data;

import com.example.dish.ui.home.Post;
import com.example.dish.ui.login.data.model.LoggedInUser;
import com.example.dish.ui.home.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */

public class LoginDataSource {

    private ArrayList<LoggedInUser> users;

    private ArrayList<String> getUsernames() {
        ArrayList<String> usernames = new ArrayList<String>();
        for (LoggedInUser u: users) {
            usernames.add(u.getDisplayName());
        }
        
        return usernames;
    }

    private LoggedInUser getUser(String username) {
        for (LoggedInUser u: users) {
            if (u.getDisplayName() == username) {
                return u;
            }
        }

        return null;
    }

    public Result<LoggedInUser> login(String username, String password) {

        try {
            if(getUsernames().contains(username)) {
                return new Result.Success<>(getUser(username));

            } else {
                LoggedInUser newUser = new LoggedInUser(password, username);
                users.add(newUser);
                return new Result.Success<>(newUser);
            }
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
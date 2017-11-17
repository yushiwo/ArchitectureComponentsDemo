package com.example.netease.architecturecomponentsdemo.data.remote.model;


import com.example.netease.architecturecomponentsdemo.data.local.db.entity.User;

/**
 * Created by netease on 17/11/14.
 */

public class UserData {
    private boolean error;
    private User user;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

package com.example.netease.architecturecomponentsdemo.data.local.datasource;

import android.arch.lifecycle.LiveData;

import com.example.netease.architecturecomponentsdemo.data.local.db.entity.User;


/**
 * Created by netease on 17/11/15.
 */

public interface LocalDataSource {
    LiveData<User> loadUser(String id);
    void save(User user);
}

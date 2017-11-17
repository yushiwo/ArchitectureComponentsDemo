package com.example.netease.architecturecomponentsdemo.data.remote.datasource;

import android.arch.lifecycle.LiveData;

import com.example.netease.architecturecomponentsdemo.data.local.db.entity.User;
import com.example.netease.architecturecomponentsdemo.data.remote.model.State;

/**
 * Created by netease on 17/11/14.
 */

public interface UserDataSource {
    LiveData<User> getUserLiveData(String address);
    LiveData<State> getState(String address);
}

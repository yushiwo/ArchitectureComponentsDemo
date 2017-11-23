package com.example.netease.architecturecomponentsdemo.demo.datasource;

import android.arch.lifecycle.LiveData;

import com.example.netease.architecturecomponentsdemo.aacbase.net.Resource;
import com.example.netease.architecturecomponentsdemo.demo.db.entity.User;

/**
 * Created by netease on 17/11/14.
 */

public interface UserDataSource {
    LiveData<Resource<User>> getUserResourceLiveData(String address);
}

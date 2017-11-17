package com.example.netease.architecturecomponentsdemo.data.local.datasource.impl;

import android.arch.lifecycle.LiveData;

import com.example.netease.architecturecomponentsdemo.data.local.datasource.LocalDataSource;
import com.example.netease.architecturecomponentsdemo.data.local.db.AppDatabaseManager;
import com.example.netease.architecturecomponentsdemo.data.local.db.entity.User;

/**
 * Created by netease on 17/11/15.
 */

public class LocalDataSourceImpl implements LocalDataSource {
    private static LocalDataSourceImpl INSTANCE = null;

    private LocalDataSourceImpl() {
    }

    public static LocalDataSourceImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (LocalDataSourceImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocalDataSourceImpl();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public LiveData<User> loadUser(String id) {
        return AppDatabaseManager.getInstance().loadUser(id);
    }

    @Override
    public void save(User user) {
        AppDatabaseManager.getInstance().saveUser(user);
    }
}

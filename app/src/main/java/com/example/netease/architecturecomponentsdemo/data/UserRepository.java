package com.example.netease.architecturecomponentsdemo.data;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.netease.architecturecomponentsdemo.data.local.datasource.LocalDataSource;
import com.example.netease.architecturecomponentsdemo.data.local.db.entity.User;
import com.example.netease.architecturecomponentsdemo.data.remote.datasource.UserDataSource;
import com.example.netease.architecturecomponentsdemo.data.remote.model.State;

/**
 * Created by netease on 17/11/14.
 */

public class UserRepository {
    private static UserRepository INSTANCE = null;

    private UserDataSource mUserDataSource;
    private LocalDataSource mLocalDataSource;

    public UserRepository(UserDataSource dataSource, LocalDataSource localDataSource) {
        mUserDataSource = dataSource;
        mLocalDataSource = localDataSource;
    }

    public static UserRepository getInstance(@NonNull UserDataSource remoteDataSource, @NonNull LocalDataSource localDataSource) {
        if (INSTANCE == null) {
            synchronized (UserRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserRepository(remoteDataSource, localDataSource);
                }
            }
        }
        return INSTANCE;
    }

    public LiveData<User> getUserLiveData(String address) {
//        if (Util.isNetworkConnected(MyApplication.getInstance())) {
//            return mUserDataSource.getUserLiveData(address);
//        } else {
//            return mLocalDataSource.loadUser(address);
//        }
        return mUserDataSource.getUserLiveData(address);
    }

    public LiveData<State> getState(String address) {
        return mUserDataSource.getState(address);
    }
}

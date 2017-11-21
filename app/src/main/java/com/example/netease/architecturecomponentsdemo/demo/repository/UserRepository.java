package com.example.netease.architecturecomponentsdemo.demo.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.netease.architecturecomponentsdemo.aacbase.repository.IRepository;
import com.example.netease.architecturecomponentsdemo.demo.db.entity.User;
import com.example.netease.architecturecomponentsdemo.demo.datasource.UserDataSource;
import com.example.netease.architecturecomponentsdemo.demo.model.Product;
import com.example.netease.architecturecomponentsdemo.demo.model.dto.State;

/**
 * Created by netease on 17/11/14.
 */

public class UserRepository implements IRepository {
    private static UserRepository INSTANCE = null;

    private UserDataSource mUserDataSource;

    public UserRepository(UserDataSource dataSource) {
        mUserDataSource = dataSource;
    }

    public static UserRepository getInstance(@NonNull UserDataSource remoteDataSource) {
        if (INSTANCE == null) {
            synchronized (UserRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserRepository(remoteDataSource);
                }
            }
        }
        return INSTANCE;
    }

    public LiveData<User> getUserLiveData(String address) {
        return mUserDataSource.getUserLiveData(address);
    }

    public LiveData<Product> getProductLiveData() {
        return mUserDataSource.getProductLiveData();
    }

    public LiveData<State> getProductState() {
        return mUserDataSource.getProductState();
    }

    @Override
    public LiveData<State> getState() {
        return mUserDataSource.getState();
    }
}

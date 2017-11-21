package com.example.netease.architecturecomponentsdemo.demo.datasource;

import android.arch.lifecycle.LiveData;

import com.example.netease.architecturecomponentsdemo.aacbase.datasource.AbDataSource;
import com.example.netease.architecturecomponentsdemo.aacbase.datasource.IDataSource;
import com.example.netease.architecturecomponentsdemo.demo.db.entity.User;
import com.example.netease.architecturecomponentsdemo.demo.model.Product;
import com.example.netease.architecturecomponentsdemo.demo.model.dto.State;

/**
 * Created by netease on 17/11/14.
 */

public interface UserDataSource extends IDataSource {
    LiveData<User> getUserLiveData(String address);
    LiveData<Product> getProductLiveData();

    LiveData<State> getProductState();
}

package com.example.netease.architecturecomponentsdemo.demo.datasource.impl;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.example.netease.architecturecomponentsdemo.aacbase.net.Resource;
import com.example.netease.architecturecomponentsdemo.app.AppExecutors;
import com.example.netease.architecturecomponentsdemo.demo.db.UserDatabaseManager;
import com.example.netease.architecturecomponentsdemo.demo.db.entity.User;
import com.example.netease.architecturecomponentsdemo.demo.datasource.UserDataSource;

import java.util.List;

/**
 * Created by netease on 17/11/14.
 */

public class UserDataSourceImpl implements UserDataSource {
    private static long TIME = 2;
    private static UserDataSourceImpl INSTANCE = null;

    public static UserDataSourceImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (UserDataSourceImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserDataSourceImpl();
                }
            }
        }
        return INSTANCE;
    }

    MutableLiveData<Resource<User>> dataResource = new MutableLiveData<>();

    @Override
    public LiveData<Resource<User>> getUserResourceLiveData(String address) {
        dataResource.postValue(Resource.loading(null));
        switch (address) {
            case "1":
                AppExecutors.getInstance().networkIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        User user = new User();
                        user.setId(address);
                        user.setName("小雪");
                        user.setAge(18);
                        user.setLastName("盛");
                        dataResource.postValue(Resource.success(user));

                        UserDatabaseManager.getInstance().saveUser(user);
                    }
                });
                break;

            case "2" :
                AppExecutors.getInstance().networkIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        User user = new User();
                        user.setId(address);
                        user.setName("威少");
                        user.setAge(18);
                        user.setLastName("陈");
                        dataResource.postValue(Resource.success(user));

                        UserDatabaseManager.getInstance().saveUser(user);
                    }
                });
                break;

            case "3" :
//                AppExecutors.getInstance().networkIO().execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            Thread.sleep(3000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//
//                        dataResource.postValue(Resource.error("请求失败啦", null));
//
//                        List<User> userList = UserDatabaseManager.getInstance().getAllUsers();
//                        for (User user : userList) {
//                            System.out.println(user.toString());
//                        }
//                    }
//                });

                LiveData<List<User>> userList = UserDatabaseManager.getInstance().getAllUsers();
                userList.observeForever(new Observer<List<User>>() {
                    @Override
                    public void onChanged(@Nullable List<User> users) {
                        if (users == null || users.size() == 0) {
                            System.out.println("没有数据啦");
                        } else {
                            System.out.println("长度 ＝ " + users.size());
                            for (User user : users) {
                                System.out.println(user.toString());
                            }
                        }
                    }
                });

                break;

            default:
                UserDatabaseManager.getInstance().clear();
                break;
        }

         return dataResource;
    }

}

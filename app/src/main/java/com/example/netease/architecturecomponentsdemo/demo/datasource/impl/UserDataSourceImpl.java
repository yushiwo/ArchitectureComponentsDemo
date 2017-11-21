package com.example.netease.architecturecomponentsdemo.demo.datasource.impl;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.netease.architecturecomponentsdemo.aacbase.datasource.AbDataSource;
import com.example.netease.architecturecomponentsdemo.demo.db.UserDatabaseManager;
import com.example.netease.architecturecomponentsdemo.demo.db.entity.User;
import com.example.netease.architecturecomponentsdemo.demo.datasource.UserDataSource;
import com.example.netease.architecturecomponentsdemo.demo.model.dto.State;
import com.example.netease.architecturecomponentsdemo.demo.util.ExecutorServiceManager;

import java.util.concurrent.TimeUnit;

/**
 * Created by netease on 17/11/14.
 */

public class UserDataSourceImpl extends AbDataSource implements UserDataSource {
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

    MutableLiveData<User> data = new MutableLiveData<>();

    @Override
    public LiveData<User> getUserLiveData(String address) {

        data = (MutableLiveData<User>) UserDatabaseManager.getInstance().loadUser(address);
        State s = new State();
        s.setStatus("loading");
        s.setMessage("加载中...");
        state.postValue(s);
        switch (address) {
            case "1":
                ExecutorServiceManager.getInstance().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            TimeUnit.SECONDS.sleep(TIME);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        User user = new User();
                        user.setId(address);
                        user.setName("小雪" + (int)(Math.random()*10));
                        user.setAge(15);
                        user.setLastName("盛");
                        UserDatabaseManager.getInstance().saveUser(user);
                        data.postValue(user);

                        State s = new State();
                        s.setStatus("success");
                        s.setMessage("成功！");
                        state.postValue(s);
                    }
                });
                break;

            case "2":
                ExecutorServiceManager.getInstance().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            TimeUnit.SECONDS.sleep(TIME);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        State s = new State();
                        s.setStatus("failed");
                        s.setMessage("失败啦！");
                        state.postValue(s);
                    }
                });
                break;

            default:
                ExecutorServiceManager.getInstance().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            TimeUnit.SECONDS.sleep(TIME);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        User user = new User();
                        user.setId(address);
                        user.setName("威少");
                        user.setAge(18);
                        user.setLastName("陈");
                        UserDatabaseManager.getInstance().saveUser(user);
                        data.postValue(user);

                        State s = new State();
                        s.setStatus("success");
                        s.setMessage("成功！");
                        state.postValue(s);

                    }
                });
                break;
        }


        return data;
    }

}

package com.example.netease.architecturecomponentsdemo.demo.datasource.impl;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.netease.architecturecomponentsdemo.aacbase.datasource.AbDataSource;
import com.example.netease.architecturecomponentsdemo.app.AppExecutors;
import com.example.netease.architecturecomponentsdemo.demo.db.UserDatabaseManager;
import com.example.netease.architecturecomponentsdemo.demo.db.entity.User;
import com.example.netease.architecturecomponentsdemo.demo.datasource.UserDataSource;
import com.example.netease.architecturecomponentsdemo.demo.model.Product;
import com.example.netease.architecturecomponentsdemo.demo.model.dto.ProductDto;
import com.example.netease.architecturecomponentsdemo.demo.model.dto.State;
import com.example.netease.architecturecomponentsdemo.demo.model.impl.ProductImpl;
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
    MutableLiveData<Product> productMutableLiveData = new MutableLiveData<>();
    MutableLiveData<State> productState = new MutableLiveData<>();

    @Override
    public LiveData<User> getUserLiveData(String address) {

        data = (MutableLiveData<User>) UserDatabaseManager.getInstance().loadUser(address);
        notifyLoading();
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

                        notifySuccess();
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
                        notifyError();
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

                       notifySuccess();

                    }
                });
                break;
        }


        return data;
    }

    @Override
    public LiveData<Product> getProductLiveData() {
        State s = new State(State.LOADING, "loading...");
        productState.postValue(s);

        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int index = (int) (Math.random() * 10);
                Product product = new ProductImpl("产品" , index);
                State s = new State(State.SUCCESS, "成功...");

                productState.postValue(s);
                productMutableLiveData.postValue(product);
            }
        });
        return productMutableLiveData;
    }

    @Override
    public LiveData<State> getProductState() {
        return productState;
    }

}

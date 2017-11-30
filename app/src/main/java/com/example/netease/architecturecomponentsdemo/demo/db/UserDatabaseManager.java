package com.example.netease.architecturecomponentsdemo.demo.db;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.example.netease.architecturecomponentsdemo.aacbase.dbmanager.AbDatabaseManager;
import com.example.netease.architecturecomponentsdemo.app.AppExecutors;
import com.example.netease.architecturecomponentsdemo.demo.db.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzzhengrui on 17/11/18.
 */


public class UserDatabaseManager extends AbDatabaseManager {

    private static UserDatabaseManager INSTANCE = null;
    public static UserDatabaseManager getInstance() {
        if (INSTANCE == null) {
            synchronized (UserDatabaseManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserDatabaseManager();
                }
            }
        }
        return INSTANCE;
    }

    private static final MutableLiveData<User> mUser = new MutableLiveData<>();


    @SuppressLint("StaticFieldLeak")
    public void saveUser(User user) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDB.beginTransaction();
                try {
                    mDB.userDao().save(user);
                    mDB.setTransactionSuccessful();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    mDB.endTransaction();
                }
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    public LiveData<User> loadUser(String id) {
        new AsyncTask<Void, Void, User>() {
            @Override
            protected User doInBackground(Void... voids) {
                User user = new User();
                mDB.beginTransaction();
                try {
                    user = mDB.userDao().loadUser(id);
                    mDB.setTransactionSuccessful();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    mDB.endTransaction();
                }
                return user;
            }

            @Override
            protected void onPostExecute(User aVoid) {
                super.onPostExecute(aVoid);
                mUser.setValue(aVoid);
            }

            @Override
            protected void onCancelled(User aVoid) {
                super.onCancelled(aVoid);
            }
        }.execute();
        return mUser;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        mDB.beginTransaction();
        try {
            userList = mDB.userDao().getAll();
            mDB.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mDB.endTransaction();
        }
        return userList;
    }

    public void clear() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDB.beginTransaction();
                try {
                    mDB.userDao().deleteAll();
                    mDB.setTransactionSuccessful();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    mDB.endTransaction();
                }
            }
        });

    }
}

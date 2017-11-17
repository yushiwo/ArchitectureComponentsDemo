package com.example.netease.architecturecomponentsdemo.data.local.db;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;


import com.example.netease.architecturecomponentsdemo.data.local.db.entity.User;

/**
 * AppDatabaseManager.java
 * <p>
 * Created by lijiankun on 17/7/5.
 */

public class AppDatabaseManager {

    private static final String DATABASE_NAME = "architecture-practice-db";


    private final MutableLiveData<User> mUser;


    private static AppDatabaseManager INSTANCE = null;

    private AppDatabase mDB = null;{
        mUser = new MutableLiveData<>();
    }

    private AppDatabaseManager() {
    }

    public static AppDatabaseManager getInstance() {
        if (INSTANCE == null) {
            synchronized (AppDatabaseManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppDatabaseManager();
                }
            }
        }
        return INSTANCE;
    }

    @SuppressLint("StaticFieldLeak")
    public void createDB(Context context) {
        new AsyncTask<Context, Void, Void>() {
            @Override
            protected Void doInBackground(Context... params) {
                Context context = params[0].getApplicationContext();
                mDB = Room.databaseBuilder(context,
                        AppDatabase.class, DATABASE_NAME).build();
                return null;
            }
        }.execute(context.getApplicationContext());
    }

    @SuppressLint("StaticFieldLeak")
    public void saveUser(User user) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mDB.beginTransaction();
                try {
                    mDB.userDao().save(user);
                    mDB.setTransactionSuccessful();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    mDB.endTransaction();
                }
                return null;
            }
        }.execute();

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

}

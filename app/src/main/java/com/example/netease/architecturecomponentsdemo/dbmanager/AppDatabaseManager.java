package com.example.netease.architecturecomponentsdemo.dbmanager;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.os.AsyncTask;


import com.example.netease.architecturecomponentsdemo.demo.db.entity.User;

/**
 * AppDatabaseManager.java
 * <p>
 * Created by lijiankun on 17/7/5.
 */
@Deprecated
public class AppDatabaseManager {

    private static final String DATABASE_NAME = "architecture-practice-db";


    private final MutableLiveData<User> mUser;


    private static AppDatabaseManager INSTANCE = null;

    private AppDatabase mDB = null;
    {
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

    public AppDatabase getmDB() {
        return mDB;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE users "
                    + " ADD COLUMN last_name TEXT");
        }
    };


    @SuppressLint("StaticFieldLeak")
    public void createDB(Context context) {
        new AsyncTask<Context, Void, Void>() {
            @Override
            protected Void doInBackground(Context... params) {
                Context context = params[0].getApplicationContext();
                mDB = Room.databaseBuilder(context,
                        AppDatabase.class, DATABASE_NAME)
                        .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                        .build();
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

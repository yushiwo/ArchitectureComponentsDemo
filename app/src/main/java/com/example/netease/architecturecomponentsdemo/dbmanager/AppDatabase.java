package com.example.netease.architecturecomponentsdemo.dbmanager;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.netease.architecturecomponentsdemo.demo.db.dao.UserDao;
import com.example.netease.architecturecomponentsdemo.demo.db.entity.User;

/**
 * Created by netease on 17/11/15.
 */
@Database(entities = {User.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}

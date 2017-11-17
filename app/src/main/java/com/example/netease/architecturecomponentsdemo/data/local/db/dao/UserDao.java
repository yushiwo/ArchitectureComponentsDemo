package com.example.netease.architecturecomponentsdemo.data.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.netease.architecturecomponentsdemo.data.local.db.entity.User;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by netease on 17/11/15.
 */
@Dao
public interface UserDao {
    @Insert(onConflict = REPLACE)
    void save(User user);
    @Query("SELECT * FROM users WHERE _id = :id")
    User loadUser(String id);
}

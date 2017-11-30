package com.example.netease.architecturecomponentsdemo.demo.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.netease.architecturecomponentsdemo.demo.db.entity.User;

import java.util.List;

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

    @Query("SELECT * FROM users")
    List<User> getAll();

    @Query("DELETE FROM users")
    void deleteAll();
}

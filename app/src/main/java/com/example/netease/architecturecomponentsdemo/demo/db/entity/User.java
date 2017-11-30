package com.example.netease.architecturecomponentsdemo.demo.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.netease.architecturecomponentsdemo.demo.model.dto.UserDto;

/**
 * Created by netease on 17/11/15.
 */
@Entity(tableName = "users")
public class User implements IUser {
    @PrimaryKey
    @NonNull
    private String _id;
    private String name;
    private int age;
    @ColumnInfo(name = "last_name")
    private String lastName;

    public User() {
    }

    public User(UserDto userData) {
    }

    @Override
    public String getId() {
        return _id;
    }

    @Override
    public void setId(String id) {
        this._id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

package com.example.netease.architecturecomponentsdemo.demo.db.entity;

/**
 * Created by hzzhengrui on 17/11/17.
 */

public interface IUser {
    String getId();

    void setId(String id);

    String getName();

    void setName(String name);

    int getAge();

    void setAge(int age);

    String getLastName();

    void setLastName(String lastName);
}

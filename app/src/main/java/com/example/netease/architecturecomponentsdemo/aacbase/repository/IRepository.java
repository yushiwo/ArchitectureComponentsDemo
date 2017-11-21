package com.example.netease.architecturecomponentsdemo.aacbase.repository;

import android.arch.lifecycle.LiveData;

import com.example.netease.architecturecomponentsdemo.demo.model.dto.State;

/**
 * Created by hzzhengrui on 17/11/21.
 */

public interface IRepository {
    LiveData<State> getState();
}

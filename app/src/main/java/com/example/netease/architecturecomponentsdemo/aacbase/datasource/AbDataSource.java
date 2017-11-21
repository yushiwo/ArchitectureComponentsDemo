package com.example.netease.architecturecomponentsdemo.aacbase.datasource;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.netease.architecturecomponentsdemo.demo.model.dto.State;

/**
 * Created by hzzhengrui on 17/11/21.
 */

public abstract class AbDataSource {
    public MutableLiveData<State> state = new MutableLiveData<>();
    public LiveData<State> getState() {
        return state;
    }
}

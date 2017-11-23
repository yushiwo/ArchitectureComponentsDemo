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

    public void notifySuccess() {
        State s = new State(State.SUCCESS, "成功");
        state.postValue(s);
    }

    public void notifyLoading() {
        State s = new State(State.LOADING, "loading");
        state.postValue(s);
    }

    /**
     * 可以传入一个error参数
     */
    public void notifyError() {
        State s = new State(State.FAILED, "失败啦！");
        state.postValue(s);
    }
}

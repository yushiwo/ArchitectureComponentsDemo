package com.example.netease.architecturecomponentsdemo.aacbase.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.netease.architecturecomponentsdemo.aacbase.repository.IRepository;
import com.example.netease.architecturecomponentsdemo.demo.model.dto.State;

/**
 * Created by hzzhengrui on 17/11/21.
 */

public abstract class AbViewModel extends ViewModel {

    public IRepository repository;

    public final MutableLiveData<String> stateInput = new MutableLiveData();

    public final LiveData<State> state =
            Transformations.switchMap(stateInput, (String input) -> {
                return repository.getState();
            });

    public AbViewModel(IRepository repository) {
        this.repository = repository;
    }


    public LiveData<State> getState() {
        stateInput.setValue(null);
        return state;
    }

}

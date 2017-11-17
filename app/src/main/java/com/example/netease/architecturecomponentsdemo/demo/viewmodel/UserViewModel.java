package com.example.netease.architecturecomponentsdemo.demo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.netease.architecturecomponentsdemo.demo.model.dto.State;
import com.example.netease.architecturecomponentsdemo.demo.repository.UserRepository;
import com.example.netease.architecturecomponentsdemo.demo.db.entity.User;

/**
 * Created by netease on 17/11/14.
 */

public class UserViewModel extends AndroidViewModel {

    private UserRepository repository;
    private final MutableLiveData<String> addressInput = new MutableLiveData();
    public final LiveData<User> mUser =
            Transformations.switchMap(addressInput, (String address) -> {
                return repository.getUserLiveData(address);
            });

    public final LiveData<State> state =
            Transformations.switchMap(addressInput, (String address) -> {
                return repository.getState(address);
            });

    public UserViewModel(Application application, UserRepository repository) {
        super(application);
        this.repository = repository;
    }

    public LiveData<User> getUserLiveData() {
        return mUser;
    }

    public LiveData<State> getState() {
        return state;
    }

    private void setInput(String address) {
        addressInput.setValue(address);
    }

    public void loadUserData(String address) {
        setInput(address);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final UserRepository userRepository;

        public Factory(@NonNull Application application, UserRepository userRepository) {
            mApplication = application;
            this.userRepository = userRepository;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new UserViewModel(mApplication, userRepository);
        }
    }
}

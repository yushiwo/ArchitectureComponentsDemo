package com.example.netease.architecturecomponentsdemo.demo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.netease.architecturecomponentsdemo.aacbase.repository.IRepository;
import com.example.netease.architecturecomponentsdemo.aacbase.viewmodel.AbViewModel;
import com.example.netease.architecturecomponentsdemo.demo.model.dto.State;
import com.example.netease.architecturecomponentsdemo.demo.repository.UserRepository;
import com.example.netease.architecturecomponentsdemo.demo.db.entity.User;

/**
 * Created by netease on 17/11/14.
 */

public class UserViewModel extends AbViewModel {
    public UserRepository repository;

    public final MutableLiveData<String> addressInput = new MutableLiveData();

    public final LiveData<User> mUser =
            Transformations.switchMap(addressInput, (String address) -> {
                return repository.getUserLiveData(address);
            });

    public UserViewModel(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public LiveData<User> getUserLiveData() {
        return mUser;
    }

    private void setInput(String address) {
        addressInput.setValue(address);
    }

    public void loadUserData(String address) {
        setInput(address);
    }

    public static class Factory implements ViewModelProvider.Factory {

        private final UserRepository userRepository;

        public Factory(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new UserViewModel(userRepository);
        }
    }
}

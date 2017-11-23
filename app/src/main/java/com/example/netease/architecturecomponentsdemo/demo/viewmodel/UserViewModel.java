package com.example.netease.architecturecomponentsdemo.demo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.netease.architecturecomponentsdemo.aacbase.net.Resource;
import com.example.netease.architecturecomponentsdemo.demo.repository.UserRepository;
import com.example.netease.architecturecomponentsdemo.demo.db.entity.User;

/**
 * Created by netease on 17/11/14.
 */

public class UserViewModel extends ViewModel {
    public UserRepository repository;

    public final MutableLiveData<String> addressInput = new MutableLiveData();


    public final LiveData<Resource<User>> mUserResource =
            Transformations.switchMap(addressInput, (String address) -> {
                return repository.getUserResourceLiveData(address);
            });


    public UserViewModel(UserRepository repository) {
        this.repository = repository;
    }

    public LiveData<Resource<User>> getUserResource() {
        return mUserResource;
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

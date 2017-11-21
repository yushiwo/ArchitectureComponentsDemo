package com.example.netease.architecturecomponentsdemo.demo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.example.netease.architecturecomponentsdemo.aacbase.viewmodel.AbViewModel;
import com.example.netease.architecturecomponentsdemo.demo.model.Product;
import com.example.netease.architecturecomponentsdemo.demo.model.dto.State;
import com.example.netease.architecturecomponentsdemo.demo.repository.UserRepository;
import com.example.netease.architecturecomponentsdemo.demo.db.entity.User;

/**
 * Created by netease on 17/11/14.
 */

public class UserViewModel extends AbViewModel {
    public UserRepository repository;

    public final MutableLiveData<String> addressInput = new MutableLiveData();
    public final LiveData<User> mUser = Transformations.switchMap(addressInput, (String address) -> repository.getUserLiveData(address));

    public final MutableLiveData<String> productInput = new MutableLiveData();
    public final LiveData<Product> mProduct = Transformations.switchMap(productInput, (String input) -> repository.getProductLiveData());
    public final LiveData<State> mProductState = Transformations.switchMap(productInput, (String input) -> repository.getProductState());

    public UserViewModel(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public LiveData<User> getUser() {
        return mUser;
    }

    public void requestUser(String address) {
        addressInput.setValue(address);
        observeState();
    }

    public void requestProduct() {
        productInput.setValue(null);
    }

    public LiveData<Product> getProduct() {
        return mProduct;
    }

    public LiveData<State> getProductState() {
        return mProductState;
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

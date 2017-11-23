package com.example.netease.architecturecomponentsdemo.demo.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.netease.architecturecomponentsdemo.R;
import com.example.netease.architecturecomponentsdemo.demo.datasource.impl.UserDataSourceImpl;
import com.example.netease.architecturecomponentsdemo.demo.model.Product;
import com.example.netease.architecturecomponentsdemo.demo.model.dto.State;
import com.example.netease.architecturecomponentsdemo.demo.repository.UserRepository;
import com.example.netease.architecturecomponentsdemo.demo.viewmodel.UserViewModel;
import com.example.netease.architecturecomponentsdemo.demo.db.entity.User;

import static com.example.netease.architecturecomponentsdemo.demo.model.dto.State.FAILED;
import static com.example.netease.architecturecomponentsdemo.demo.model.dto.State.LOADING;
import static com.example.netease.architecturecomponentsdemo.demo.model.dto.State.SUCCESS;

public class MainActivity extends AppCompatActivity {

    private TextView mUserTextView;
    private TextView mProductTextView;
    private TextView mStateTextView;
    private TextView mProductStateTextView;
    private EditText editText;
    private Button button;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();
        observeDataChange();

    }

    private void initView() {
        mUserTextView = (TextView) findViewById(R.id.tv_user);
        mProductTextView = (TextView) findViewById(R.id.tv_product);
        mStateTextView = (TextView) findViewById(R.id.tv_state);
        mProductStateTextView = (TextView) findViewById(R.id.tv_product_state);
        editText = (EditText) findViewById(R.id.et);
        button = (Button) findViewById(R.id.btn);
    }

    private void setListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputStr = editText.getText().toString();
                userViewModel.requestUser(inputStr);
                userViewModel.requestProduct();
            }
        });
    }

    private void observeDataChange() {
        UserViewModel.Factory factory = new UserViewModel.Factory( UserRepository.getInstance(UserDataSourceImpl.getInstance()));
        userViewModel = ViewModelProviders.of(this, factory).get(UserViewModel.class);
        userViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if (user != null) {
                    mUserTextView.setText(user.getLastName() + user.getName());
                }
            }
        });

        userViewModel.getProduct().observe(this, new Observer<Product>() {
            @Override
            public void onChanged(@Nullable Product product) {
                mProductTextView.setText(String.valueOf(product.getWeight()));
            }
        });

        userViewModel.getState().observe(this, new Observer<State>() {
            @Override
            public void onChanged(@Nullable State state) {
                switch (state.getStatus()) {
                    case SUCCESS:
                        mStateTextView.setText(state.getMessage());
                        break;
                    case LOADING:
                        mStateTextView.setText(state.getMessage());
                        break;
                    case FAILED:
                        mStateTextView.setText(state.getMessage());
                        break;
                }
            }
        });

        userViewModel.getProductState().observe(this, new Observer<State>() {
            @Override
            public void onChanged(@Nullable State state) {
                mProductStateTextView.setText(state.getMessage());
            }
        });
    }
}

package com.example.netease.architecturecomponentsdemo.ui;

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
import com.example.netease.architecturecomponentsdemo.data.UserRepository;
import com.example.netease.architecturecomponentsdemo.data.local.datasource.impl.LocalDataSourceImpl;
import com.example.netease.architecturecomponentsdemo.data.local.db.entity.User;
import com.example.netease.architecturecomponentsdemo.data.remote.datasource.impl.UserDataSourceImpl;
import com.example.netease.architecturecomponentsdemo.data.remote.model.State;
import com.example.netease.architecturecomponentsdemo.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    private TextView mResultTextView;
    private TextView mStateTextView;
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
        mResultTextView = (TextView) findViewById(R.id.tv_result);
        mStateTextView = (TextView) findViewById(R.id.tv_state);
        editText = (EditText) findViewById(R.id.et);
        button = (Button) findViewById(R.id.btn);
    }

    private void setListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputStr = editText.getText().toString();
                userViewModel.loadUserData(inputStr);
            }
        });
    }

    private void observeDataChange() {
        UserViewModel.Factory factory = new UserViewModel.Factory(
                getApplication(), UserRepository.getInstance(UserDataSourceImpl.getInstance(), LocalDataSourceImpl.getInstance()));
        userViewModel = ViewModelProviders.of(this, factory).get(UserViewModel.class);
        userViewModel.getUserLiveData().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if (user != null) {
                    mResultTextView.setText(user.getName());
                }
            }
        });
        userViewModel.getState().observe(this, new Observer<State>() {
            @Override
            public void onChanged(@Nullable State state) {
                if (state != null || state.getStatus().equals("failed")) {
                    mStateTextView.setText(state.getMessage());
                } else if (state != null || state.getStatus().equals("success")) {
                    mStateTextView.setText(state.getMessage());
                } else if (state != null || state.getStatus().equals("loading")) {
                    mStateTextView.setText(state.getMessage());
                }
            }
        });
    }
}

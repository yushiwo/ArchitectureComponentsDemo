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
import com.example.netease.architecturecomponentsdemo.aacbase.net.Resource;
import com.example.netease.architecturecomponentsdemo.aacbase.net.Status;
import com.example.netease.architecturecomponentsdemo.demo.datasource.impl.UserDataSourceImpl;
import com.example.netease.architecturecomponentsdemo.demo.repository.UserRepository;
import com.example.netease.architecturecomponentsdemo.demo.viewmodel.UserViewModel;
import com.example.netease.architecturecomponentsdemo.demo.db.entity.User;

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
        UserViewModel.Factory factory = new UserViewModel.Factory( UserRepository.getInstance(UserDataSourceImpl.getInstance()));
        userViewModel = ViewModelProviders.of(this, factory).get(UserViewModel.class);

        userViewModel.getUserResource().observe(this, new Observer<Resource<User>>() {
            @Override
            public void onChanged(@Nullable Resource<User> userResource) {
                System.out.println(userResource.toString());
                switch (userResource.getStatus()) {
                    case Status.SUCCESS:
                        mStateTextView.setText(userResource.getData().getName());
                        break;

                    case Status.ERROR:
                        mStateTextView.setText(userResource.getMessage());
                        break;

                    case Status.LOADING:
                        mStateTextView.setText("加载中");
                        break;
                }
            }
        });
    }
}

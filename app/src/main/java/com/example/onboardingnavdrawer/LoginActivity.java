package com.example.onboardingnavdrawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.onboardingnavdrawer.model.User;
import com.example.onboardingnavdrawer.viewmodel.MainViewModel;

public class LoginActivity extends AppCompatActivity {
    EditText mUsername, mPassword;
    Button mlogin;

    MainViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mUsername = findViewById(R.id.et_username);
        mPassword = findViewById(R.id.et_password);
        mlogin = findViewById(R.id.btn_login);

        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User();
                user.setUsername(mUsername.getText().toString());
                user.setPassword(mPassword.getText().toString());


                viewModel.onLoginClick(user);

                viewModel.getLoginRequestStatus().observe(LoginActivity.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if (aBoolean){
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });

            }
        });

    }
}
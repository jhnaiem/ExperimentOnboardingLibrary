package com.example.onboardingnavdrawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.onboardingnavdrawer.model.SessionData;
import com.example.onboardingnavdrawer.model.SessionManagement;
import com.example.onboardingnavdrawer.model.User;
import com.example.onboardingnavdrawer.viewmodel.MainViewModel;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class LoginActivity extends AppCompatActivity {

    private Realm mRealm = null;
    private EditText mUsername, mPassword;
    private Button mlogin;

    MainViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mUsername = findViewById(R.id.et_username);
        mPassword = findViewById(R.id.et_password);
        mlogin = findViewById(R.id.btn_login);


        //Config Realm
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration
                .Builder()
                .name("userInfoTopModule.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);


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
                        if (aBoolean) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });


                viewModel.sessionDataMutableLiveData.observe(LoginActivity.this, new Observer<SessionData>() {
                    @Override
                    public void onChanged(SessionData sessionData) {
                        SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
                        sessionManagement.saveSession(sessionData);

                        //
                        moveToMainactivity();
                    }
                });

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
        int userLoggedinID = sessionManagement.getSession();

        //check if user is logged in or not?
        if (userLoggedinID != -1) {
            //user is logged in so move to mainactivity
            moveToMainactivity();
        } else {
            //nothing to do
        }

    }

    //method to move to main activity
    private void moveToMainactivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
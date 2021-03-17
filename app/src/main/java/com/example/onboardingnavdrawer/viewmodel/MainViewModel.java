package com.example.onboardingnavdrawer.viewmodel;

import android.content.Intent;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.onboardingnavdrawer.NetworkRelatedClass.MyApiService;
import com.example.onboardingnavdrawer.NetworkRelatedClass.NetworkCall;
import com.example.onboardingnavdrawer.NetworkRelatedClass.ResponseCallback;
import com.example.onboardingnavdrawer.model.User;

import org.json.JSONException;
import org.json.JSONObject;

public class MainViewModel extends ViewModel {



    private MutableLiveData<Boolean> loginRequestStatus = new MutableLiveData<>();



    public void onLoginClick(User user){
        MyApiService myApiService = new NetworkCall();


        myApiService.userValidityCheck(user, new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {

                loginRequestStatus.setValue(Boolean.valueOf(data));

            }

            @Override
            public void onError(Throwable th) {

            }
        });





    }


    public MutableLiveData<Boolean> getLoginRequestStatus() {
        return loginRequestStatus;
    }
}

package com.example.onboardingnavdrawer.NetworkRelatedClass;


import com.example.onboardingnavdrawer.model.User;

public interface MyApiService {
    void userValidityCheck(User userLoginCredential, ResponseCallback<String> callback);
}

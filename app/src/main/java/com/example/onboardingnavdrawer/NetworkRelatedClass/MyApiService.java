package com.example.onboardingnavdrawer.NetworkRelatedClass;


import com.example.onboardingnavdrawer.model.LoginResponseBody;
import com.example.onboardingnavdrawer.model.User;

public interface MyApiService {
    void userValidityCheck(User userLoginCredential, ResponseCallback<LoginResponseBody> callback);
    void userImageFetch(String url,ResponseCallback<LoginResponseBody> callback);
}

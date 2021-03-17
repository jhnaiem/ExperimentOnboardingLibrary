package com.example.onboardingnavdrawer.NetworkRelatedClass;

import com.example.onboardingnavdrawer.model.LoginResponseBody;
import com.example.onboardingnavdrawer.model.ServerResponse;
import com.example.onboardingnavdrawer.model.User;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("/api/login/")
    @Headers({
            "content-type:application/json",
    })
    Observable<LoginResponseBody> getUserValidity(@Body RequestBody user);


}

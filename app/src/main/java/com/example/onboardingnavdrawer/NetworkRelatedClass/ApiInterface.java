package com.example.onboardingnavdrawer.NetworkRelatedClass;

import com.example.onboardingnavdrawer.model.LoginResponseBody;

import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("/api/login/")
    @Headers({

            "content-type:application/json",
    })
    Single<LoginResponseBody> getUserValidity(@Body RequestBody user);


}

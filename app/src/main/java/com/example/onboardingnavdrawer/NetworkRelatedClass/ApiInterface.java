package com.example.onboardingnavdrawer.NetworkRelatedClass;

import com.example.onboardingnavdrawer.model.LoginResponseBody;

import io.reactivex.Single;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiInterface {

    @POST("/api/login/")
    @Headers({

            "content-type:application/json",
    })
    Single<LoginResponseBody> getUserValidity(@Body RequestBody user);

    @GET
    Single<ResponseBody>fetchImage(@Url String url);


}

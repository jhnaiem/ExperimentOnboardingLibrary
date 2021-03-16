package com.example.onboardingnavdrawer.NetworkRelatedClass;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiClient {

    private static final String BASE_URL = "http://bloomhn.fisdev.com";
    private static ApiInterface api = null;

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private RetrofitApiClient() {
    } // So that nobody can create an object with constructor

    public static ApiInterface getClient() {
        if (api == null) {
            synchronized (RetrofitApiClient.class) { //thread safe Singleton implementation
                if (api == null) {
                    api = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build()
                            .create(ApiInterface.class);
                }
            }
        }

        return api;
    }


}

package com.example.onboardingnavdrawer.NetworkRelatedClass;

import android.util.Log;

import com.example.onboardingnavdrawer.model.ServerResponse;
import com.example.onboardingnavdrawer.model.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class NetworkCall implements MyApiService {

    private CompositeDisposable disposable = new CompositeDisposable();


    @Override
    public void userValidityCheck(User userLoginCredential, ResponseCallback<String> callback) {


        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("username",userLoginCredential.getUsername());
            jsonObject.put("password", userLoginCredential.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();

        }


        disposable.add(RetrofitApiClient.getClient().getUserValidity(RequestBody.create(MediaType.parse("Content-type:application/json"),jsonObject.toString() ))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ServerResponse>() {
                    @Override
                    public void onNext(ServerResponse value) {

                        callback.onSuccess(value.toString());
                        Log.d("Response",value.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));



    }

}

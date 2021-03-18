package com.example.onboardingnavdrawer.NetworkRelatedClass;

import androidx.lifecycle.MutableLiveData;

import com.example.onboardingnavdrawer.model.LoginResponseBody;
import com.example.onboardingnavdrawer.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class NetworkCall implements MyApiService {

    private static final String TAG = NetworkCall.class.getSimpleName();
    private CompositeDisposable disposable = new CompositeDisposable();

    private MutableLiveData<LoginResponseBody> loginResponseBodyMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loginRequestStatus = new MutableLiveData<>();



    @Override
    public void userValidityCheck(User userLoginCredential, ResponseCallback<LoginResponseBody> callback) {


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
                .subscribeWith(new DisposableSingleObserver<LoginResponseBody>() {
                    @Override
                    public void onSuccess(LoginResponseBody loginResponseBody) {
                        loginResponseBodyMutableLiveData.setValue(loginResponseBody);
                        callback.onSuccess(loginResponseBody);

//                       try {
//                           JSONObject bodyJson = new JSONObject(loginResponseBody.toString());
//
//                           Log.e(TAG,bodyJson.getString("user_id"));
//                       } catch (JSONException e) {
//                           e.printStackTrace();
//                       }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);

                    }
                }));



    }

}

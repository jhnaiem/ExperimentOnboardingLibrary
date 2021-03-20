package com.example.onboardingnavdrawer.NetworkRelatedClass;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.lifecycle.MutableLiveData;

import com.example.onboardingnavdrawer.model.LoginResponseBody;
import com.example.onboardingnavdrawer.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class NetworkCall implements MyApiService {

    private static final String TAG = NetworkCall.class.getSimpleName();
    private CompositeDisposable disposable = new CompositeDisposable();

    private MutableLiveData<LoginResponseBody> loginResponseBodyMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loginRequestStatus = new MutableLiveData<>();


    @Override
    public void userValidityCheck(User userLoginCredential, ResponseCallback<LoginResponseBody> callback) {


        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("username", userLoginCredential.getUsername());
            jsonObject.put("password", userLoginCredential.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();

        }


        disposable.add(RetrofitApiClient.getClient().getUserValidity(RequestBody.create(MediaType.parse("Content-type:application/json"), jsonObject.toString()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<LoginResponseBody>() {
                    @Override
                    public void onSuccess(LoginResponseBody loginResponseBody) {
                        loginResponseBodyMutableLiveData.setValue(loginResponseBody);
                        callback.onSuccess(loginResponseBody);

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);

                    }
                }));


    }

    @Override
    public void userImageFetch(String url,ResponseCallback<LoginResponseBody> callback) {

        disposable.add(RetrofitApiClient.getClient().fetchImage(url)
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<ResponseBody>() {
                    @Override
                    public void onSuccess(@NonNull ResponseBody responseBody) {
                        if (responseBody!= null) {
                            // display the image data in a ImageView or save it
                            Bitmap bmp = BitmapFactory.decodeStream(responseBody.byteStream());
                            callback.onImageSuccess(bmp);
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                }));


    }


}

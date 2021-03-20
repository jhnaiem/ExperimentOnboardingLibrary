package com.example.onboardingnavdrawer.NetworkRelatedClass;

import android.graphics.Bitmap;

public interface ResponseCallback<T> {
    void onSuccess(T data);
    void onError(Throwable th);
    void onImageSuccess(Bitmap bitmap);
}

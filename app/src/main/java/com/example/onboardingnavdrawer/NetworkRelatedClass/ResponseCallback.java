package com.example.onboardingnavdrawer.NetworkRelatedClass;

public interface ResponseCallback<T> {
    void onSuccess(T data);
    void onError(Throwable th);
}

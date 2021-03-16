package com.example.onboardingnavdrawer.model;

import com.google.gson.annotations.SerializedName;

public class ServerResponse {

    @SerializedName("success")
    boolean statusString;

    public boolean isSuccess() {
        return statusString;
    }
}

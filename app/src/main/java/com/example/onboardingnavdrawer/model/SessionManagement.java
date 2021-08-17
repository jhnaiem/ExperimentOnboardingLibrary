package com.example.onboardingnavdrawer.model;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "session_user";

    public SessionManagement(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(SessionData sessionData) {
        //save session of user whenever user is logged in
        long id = sessionData.getUserId();
        String orgName = sessionData.getOrganization_name();
        sessionData.getUserName();

        editor.putLong(SESSION_KEY, id).commit();
        editor.putString("OrgName", orgName).commit();
        editor.putString("Username",sessionData.getUserName()).commit();
        editor.putString("ImgUrl",sessionData.getImageUrl()).commit();

    }

    public String getUserName(){

        return sharedPreferences.getString("Username","Not Available");
    }

    public String getImgUrl(){

        return sharedPreferences.getString("ImgUrl","Url is not valid");
    }

    public long getSession() {
        //return user id whose session is saved
        return sharedPreferences.getLong(SESSION_KEY, 0);
    }

    public void removeSession() {
        editor.putLong(SESSION_KEY, 0).commit();
    }
}

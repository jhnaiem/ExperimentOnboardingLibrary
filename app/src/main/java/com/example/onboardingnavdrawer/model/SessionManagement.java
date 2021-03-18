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

        editor.putLong(SESSION_KEY, id).commit();
        editor.putString("OrgName", orgName).commit();

    }

    public int getSession() {
        //return user id whose session is saved
        return sharedPreferences.getInt(SESSION_KEY, -1);
    }

    public void removeSession() {
        editor.putInt(SESSION_KEY, -1).commit();
    }
}

package com.example.onboardingnavdrawer.viewmodel;

import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.onboardingnavdrawer.NetworkRelatedClass.MyApiService;
import com.example.onboardingnavdrawer.NetworkRelatedClass.NetworkCall;
import com.example.onboardingnavdrawer.NetworkRelatedClass.ResponseCallback;
import com.example.onboardingnavdrawer.model.AppModuleAssignment;
import com.example.onboardingnavdrawer.model.LoginResponseBody;
import com.example.onboardingnavdrawer.model.SessionData;
import com.example.onboardingnavdrawer.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.exceptions.RealmMigrationNeededException;

public class MainViewModel extends ViewModel {


    private MutableLiveData<Boolean> loginRequestStatus = new MutableLiveData<>();
    public MutableLiveData<SessionData> sessionDataMutableLiveData = new MutableLiveData<>();
    private static Realm mRealm;


    public void onLoginClick(User user) {
        MyApiService myApiService = new NetworkCall();


        myApiService.userValidityCheck(user, new ResponseCallback<LoginResponseBody>() {


            @Override
            public void onSuccess(LoginResponseBody data) {

                long userId = data.getAuth_info().getUserId();

                String orgName = data.getOrganization_name();
                if (data.isSuccess()) {

                    SessionData sessionData = new SessionData();
                    sessionData.setUserId(userId);
                    sessionData.setOrganization_name(orgName);
                    //loginRequestStatus.setValue(data.isSuccess());
                    sessionDataMutableLiveData.setValue(sessionData);
                    storeInRealm(data.getApp_top_module_assignment(), userId);
                }


            }

            @Override
            public void onError(Throwable th) {

            }
        });


    }

    private void storeInRealm(List<AppModuleAssignment> app_top_module_assignment, long userId) {
        try {
            initRealm();
            mRealm = Realm.getDefaultInstance();
            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm mRealm) {

                    mRealm.insertOrUpdate(app_top_module_assignment);
                    RealmResults<AppModuleAssignment> userTopModule = mRealm.where(AppModuleAssignment.class).isNull("user_id").findAll();
                    userTopModule.setLong("user_id", userId);

                }
            });
        } catch (RealmMigrationNeededException e) {
            Log.d("==>", "RealmExMigration" + e);
        }


    }


    public MutableLiveData<Boolean> getLoginRequestStatus() {
        return loginRequestStatus;
    }


    private void initRealm() {
        if (mRealm == null || mRealm.isClosed()) {

            RealmConfiguration realmConfiguration = new RealmConfiguration
                    .Builder()
                    .name("userInfoTopModule.realm")
                    .deleteRealmIfMigrationNeeded()
                    .build();
            mRealm = Realm.getInstance(realmConfiguration);
        }
    }
}

package com.example.onboardingnavdrawer.viewmodel;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.onboardingnavdrawer.NetworkRelatedClass.MyApiService;
import com.example.onboardingnavdrawer.NetworkRelatedClass.NetworkCall;
import com.example.onboardingnavdrawer.NetworkRelatedClass.ResponseCallback;
import com.example.onboardingnavdrawer.model.AppModuleAssignment;
import com.example.onboardingnavdrawer.model.AuthenticationInfoBody;
import com.example.onboardingnavdrawer.model.LoginResponseBody;
import com.example.onboardingnavdrawer.model.SessionData;
import com.example.onboardingnavdrawer.model.User;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.exceptions.RealmMigrationNeededException;

public class MainViewModel extends ViewModel {


    private MutableLiveData<Boolean> loginRequestStatus = new MutableLiveData<>();
    public MutableLiveData<SessionData> sessionDataMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<List<String>> moduleLabelLiveData = new MutableLiveData<>();
    //private MutableLiveData<AuthenticationInfoBody> authenticationInfoBodyMutableLiveData = new MutableLiveData<>();

    private static Realm mRealm;
    private long userId;


    public void onLoginClick(User user) {
        MyApiService myApiService = new NetworkCall();


        myApiService.userValidityCheck(user, new ResponseCallback<LoginResponseBody>() {


            @Override
            public void onSuccess(LoginResponseBody data) {

                userId = data.getAuth_info().getUserId();
                String userName = data.getAuth_info().getName();
                String imageUrl = data.getAuth_info().getUserPhoto();
                String orgName = data.getOrganization_name();

                //If response success is true
                if (data.isSuccess()) {

                    SessionData sessionData = new SessionData();
                    sessionData.setUserId(userId);
                    sessionData.setOrganization_name(orgName);
                    sessionData.setUserName(userName);
                    sessionData.setImageUrl(imageUrl);
                    //loginRequestStatus.setValue(data.isSuccess());
                    sessionDataMutableLiveData.setValue(sessionData);
                    storeInRealm(data.getApp_top_module_assignment(), userId, userName, imageUrl);
                }


            }

            @Override
            public void onError(Throwable th) {

            }
        });


    }

    //To store AppModule and userId in realmDB
    private void storeInRealm(List<AppModuleAssignment> app_top_module_assignment, long userId, String userName, String imageUrl) {
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

    //To get the item for the navigation drawer
    public void getMenuItem(long userId) {

        try {
            initRealm();
            mRealm = Realm.getDefaultInstance();
            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm mRealm) {

                    RealmResults<AppModuleAssignment> menuTopModule = mRealm.where(AppModuleAssignment.class)
                            .equalTo("user_id", userId)
                            .equalTo("access", 1)
                            .isNotEmpty("label")
                            .sort("weight", Sort.ASCENDING).findAll();

                    List<AppModuleAssignment> listModule = new ArrayList<>(menuTopModule);

                    List<String> listModuleLabel = new ArrayList<>();

                    for (AppModuleAssignment appModuleAssignment : listModule) {
                        listModuleLabel.add(appModuleAssignment.getLabel());

                    }

                    moduleLabelLiveData.setValue(listModuleLabel);

                }
            });
        } catch (RealmMigrationNeededException e) {
            Log.d("==>", "RealmExMigration" + e);
        }

    }


    //To get Toolbar's navigation icon view reference
    public static View getToolbarNavigationIcon(Toolbar toolbar){
        //check if contentDescription previously was set
        boolean hadContentDescription = !TextUtils.isEmpty(toolbar.getNavigationContentDescription());
        String contentDescription = hadContentDescription ? (String) toolbar.getNavigationContentDescription() : "navigationIcon";
        toolbar.setNavigationContentDescription(contentDescription);
        ArrayList<View> potentialViews = new ArrayList<View>();
        //find the view based on it's content description, set programatically or with android:contentDescription
        toolbar.findViewsWithText(potentialViews,contentDescription, View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
        //Nav icon is always instantiated at this point because calling setNavigationContentDescription ensures its existence
        View navIcon = null;
        if(potentialViews.size() > 0){
            navIcon = potentialViews.get(0); //navigation icon is ImageButton
        }
        //Clear content description if not previously present
        if(!hadContentDescription)
            toolbar.setNavigationContentDescription(null);
        return navIcon;
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

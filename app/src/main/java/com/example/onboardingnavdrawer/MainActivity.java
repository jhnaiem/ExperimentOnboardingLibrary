package com.example.onboardingnavdrawer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import com.example.onboardingnavdrawer.model.AppModuleAssignment;
import com.example.onboardingnavdrawer.model.SessionManagement;
import com.example.onboardingnavdrawer.view.MenuFragment;
import com.example.onboardingnavdrawer.viewmodel.MainViewModel;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private View navHeader;
    private Toolbar toolbar;

    private MainViewModel mainViewModel;
    private Realm mRealm;
    private TextView txtName;
    private ImageView imageViewProfile;

    private ShowcaseView showcaseView;

    private String userName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        setSupportActionBar(toolbar);

        long userId = getIntent().getExtras().getLong("UserId");
        userName = getIntent().getExtras().getString("Username");
        String imgUrl = getIntent().getExtras().getString("imgUrl");


        targetedPromptsChecker(userName);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);

                } else {
                    mDrawerLayout.openDrawer((int) Gravity.START);
                    if (showcaseView != null)
                        showcaseView.hide();

                }
            }

        });


        navigationView = findViewById(R.id.nav_view);
        // Navigation view header
        navHeader = navigationView.getHeaderView(0);
        txtName = navHeader.findViewById(R.id.name);
        imageViewProfile = navHeader.findViewById(R.id.imageViewPro);

        mainViewModel = new MainViewModel();


        mainViewModel.getMenuItem(userId);
        //observeMenuItem(userId);
        loadNavHeader(userName, imgUrl);

        mainViewModel.moduleLabelLiveData.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {

                if (!strings.isEmpty()) {
                    addMenuItemInNavMenuDrawer(strings);
                }
            }
        });


//        Target target = new Target() {
//            @Override
//            public Point getPoint() {
//                return new Point(100, 100);
//            }
//        };

        //Check if targeted prompts have been shown or not
        //To check if targeted prompts has shown or not


        setupDrawerContent(navigationView);


    }

    //To check if targeted prompts has shown or not
    private void targetedPromptsChecker(String userName) {
        if (!getDefaults(userName, this)) {
            showcaseView = new ShowcaseView.Builder(this)
                    .setTarget(new ViewTarget(mainViewModel.getToolbarNavigationIcon(toolbar)))
                    .setContentTitle("Click here")
                    .hideOnTouchOutside()
                    .build();
            setDefaults(userName, true, this);
        }

    }


    private void setDefaults(String key, boolean value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    private boolean getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(key, false);
    }

    //Set name and profile pic in the navigation header
    private void loadNavHeader(String userName, String imgUrl) {
        txtName.setText(userName);

        String url = "http://bloomhn.fisdev.com" + imgUrl;
        Picasso.with(MainActivity.this).load(url).into(imageViewProfile);


    }


    //To add menu item dynamically in the navigation drawer
    private void addMenuItemInNavMenuDrawer(List<String> strings) {
        //NavigationView navView = findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();

        for (String menuItem : strings) {
            menu.add(menuItem);
        }
        menu.add("Logout");
        navigationView.invalidate();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(this);
    }

    //To clear or delete realmDB of a user
    private void clearRealm() {

        initRealm();
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });


    }

    //To move back to login activity
    private void moveToLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment RegisteredFarmersFragments = new MenuFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (item != null) {
            switch (item.toString()) {
                case "Registered Farmers":
                    View view = findViewById(R.id.fragment_placeholder);
                    view.setBackgroundResource(R.color.white);
                    fragmentTransaction.replace(R.id.fragment_placeholder, RegisteredFarmersFragments);
                    fragmentTransaction.commit();
                    break;
                case "Logout":
                    SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
                    sessionManagement.removeSession();

                    //CLear realm before logout
                    clearRealm();
                    //and move back to login activity
                    moveToLogin();
                    break;
                default:
                    break;

            }

        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.closeDrawers();

        return true;
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

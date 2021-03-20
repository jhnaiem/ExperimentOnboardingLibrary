package com.example.onboardingnavdrawer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;

import com.example.onboardingnavdrawer.model.AppModuleAssignment;
import com.example.onboardingnavdrawer.model.SessionManagement;
import com.example.onboardingnavdrawer.viewmodel.MainViewModel;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.exceptions.RealmMigrationNeededException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private NavigationView navigationView;
    private View navHeader;

    private MainViewModel mainViewModel;
    private Realm mRealm;
    private TextView txtName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        long userId = getIntent().getExtras().getLong("UserId");
        String userName = getIntent().getExtras().getString("Username");
        String imgUrl = getIntent().getExtras().getString("imgUrl");


        navigationView = findViewById(R.id.nav_view);
        // Navigation view header
        navHeader = navigationView.getHeaderView(0);
        txtName = navHeader.findViewById(R.id.name);

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

    }

    private void loadNavHeader(String userName, String imgUrl) {
        txtName.setText(userName);




    }

    public Bitmap getBitmapFromURL(String src) {
        String url = "http://bloomhn.fisdev.com"+src;
        try {
            java.net.URL urlFinal = new java.net.URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlFinal
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }





    private void addMenuItemInNavMenuDrawer(List<String> strings) {
        NavigationView navView = findViewById(R.id.nav_view);
        Menu menu = navView.getMenu();

        for (String menuItem : strings) {
            menu.add(menuItem);
        }
        menu.add("Logout").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
                sessionManagement.removeSession();

                clearRealm();
                moveToLogin();

                return false;
            }
        });
        navView.invalidate();
    }

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

    private void moveToLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
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

package com.example.gdptquangtri;

import android.app.Application;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeGDPT extends Application {
    static HomeGDPT wifiInstance;

    public static synchronized HomeGDPT getInstance() {
        return wifiInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        wifiInstance = this;
        if (firebaseUser != null) {
            startActivity(new Intent(HomeGDPT.this, BottomNavActivity.class));
        }

    }

}

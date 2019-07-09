package com.example.gdptquangtri;

import android.app.Application;

public class WifiApp extends Application {
    static WifiApp wifiInstance;

    public static synchronized WifiApp getInstance() {
        return wifiInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        wifiInstance = this;
    }
}

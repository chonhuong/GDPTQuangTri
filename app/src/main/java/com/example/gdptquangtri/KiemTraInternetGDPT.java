package com.example.gdptquangtri;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class KiemTraInternetGDPT extends AppCompatActivity {
    private Button btnKiemTra;

    private boolean isOnline;
    private String title = "";
    private ActionBar actionBar;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kiemtrainternet);
        Intent intent = getIntent();
        actionBar = getSupportActionBar();

        title = intent.getStringExtra("title");
        actionBar.setTitle(title);

        // btnKiemTra = (Button) findViewById(R.id.btnKiemTraInternet);

    }

    public void Load() {

        Intent intent = new Intent(this, ViewNewsPhatSu.class);
        //  intent.putExtra("link",link);
        startActivity(intent);

    }

    //hàm làm mới internet để load qua layout tin tucws ps
    public void OnClickInternet(View v) {
        isOnline = ConnectionReceiver.isConnected();
        if (isOnline == false) {
            dialog = new Dialog(this);
            dialog.setTitle("Kết nối mạng");
            dialog.setContentView(R.layout.dialog_layout);
            dialog.show();


        } else Load();


    }

    //Cấp quyền bật wifi
    public void OnClickWiFi(View view) {
        WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(true);
        new LoadFragmentNewsPS().start();

        dialog.dismiss();
    }

    //Cấp quyền mạng di động
    public void OnClickMangDD(View view) {
        new LoadFragmentNewsPS().start();


        dialog.dismiss();
    }

    //Đóng dialog
    public void OnClickEndDialog(View view) {
        dialog.dismiss();
    }

    //load FragmentNewsPS khi có mạng lại.
    private class LoadFragmentNewsPS extends Thread {

        @Override
        public void run() {

            while (ConnectionReceiver.isConnected() == false) {
                try {

                    LoadFragmentNewsPS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Load();
        }
    }
}

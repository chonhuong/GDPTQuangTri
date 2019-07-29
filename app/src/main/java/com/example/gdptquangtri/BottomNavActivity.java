package com.example.gdptquangtri;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class BottomNavActivity extends AppCompatActivity {
    Fragment fragment;
    int count = 1;


    //private TextView mTextMessage;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    //------------------------------------------------------------------------------------------------
    TextView textview;
    RelativeLayout.LayoutParams layoutparams;
    private ActionBar actionbar;
    //------------------------------------------------------------------------------------------------
    //Chuyển layout trong bottomNavigation
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_newsGDPT:
                    fragment = new FragmentNewsGDPT();
                    // fragment = new FragmentNewsPS();
                    ActionBarTitleGravity("Tin tức GĐPT");
                    loadFragment(fragment);

                    return true;
                case R.id.navigation_newsPS:

                    fragment = new FragmentNewsPS();
                    ActionBarTitleGravity("Tin tức Phật Sự");

                    loadFragment(fragment);
                    return true;
//                case R.id.navigation_TuHoc:
//
//                    ActionBarTitleGravity("Tu học - Huấn luyện");
//                    fragment = new FragmentTuHoc();
//
//                    loadFragment(fragment);
//                    return true;
                case R.id.navigation_TroChoi:

                    fragment = new FragmentTroChoi();

                    ActionBarTitleGravity("Trò chơi sinh hoạt");
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_Them:
                    ActionBarTitleGravity("Tài khoản của tôi");
                    if (firebaseUser != null) {
                    fragment = new FragmentThem();

                        loadFragment(fragment);
                    } else {
                        fragment = new FragmentThemOff();
                        loadFragment(fragment);
                    }
                    return true;
            }
            return false;
        }
    };

    //------------------------------------------------------------------------------------------------
    private void loadFragment(Fragment fragment) {
        // load fragment

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_nav_activity);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        //mTextMessage = findViewById(R.id.message);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        fragment = new FragmentNewsGDPT();
        ActionBarTitleGravity("Tin tức GĐPT");


        loadFragment(fragment);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        int seletedItemId = bottomNavigationView.getSelectedItemId();

        if (R.id.navigation_newsGDPT == seletedItemId) {
            if (count == 2) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                if (firebaseUser != null) {
                    Intent intent1 = new Intent(getApplicationContext(), BottomNavActivity.class);
                    startActivity(intent1);

                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    startActivity(intent);
                    finish();
                } else finish();

            } else {
                Toast.makeText(this, "Nhấn lần nữa để thoát", Toast.LENGTH_SHORT).show();
                count += 1;
            }


        } else {
            count = 1;
            bottomNavigationView.setSelectedItemId(R.id.navigation_newsGDPT);
        }
    }

    private void ActionBarTitleGravity(String title) {
        // TODO Auto-generated method stub

        actionbar = getSupportActionBar();

        textview = new TextView(getApplicationContext());

        layoutparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        textview.setLayoutParams(layoutparams);

        textview.setText(title);

        textview.setTextColor(Color.WHITE);

        textview.setGravity(Gravity.CENTER);

        textview.setTextSize(20);


        actionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        actionbar.setCustomView(textview);

    }
}

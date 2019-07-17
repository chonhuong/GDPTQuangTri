package com.example.gdptquangtri;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BottomNavActivity extends AppCompatActivity {
    Fragment fragment;
    int count = 1;
    TextView textview;
    RelativeLayout.LayoutParams layoutparams;
    MainActivity mainActivity = new MainActivity();
    private DrawerLayout dr;
    private ActionBarDrawerToggle abdr;
    //private TextView mTextMessage;
    private boolean isOnline;
    //------------------------------------------------------------------------------------------------
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
                case R.id.navigation_TuHoc:

                    ActionBarTitleGravity("Tu học - Huấn luyện");
                    fragment = new FragmentTuHoc();

                    loadFragment(fragment);
                    return true;
                case R.id.navigation_TroChoi:

                    fragment = new FragmentTroChoi();

                    ActionBarTitleGravity("Trò chơi sinh hoạt");
                    loadFragment(fragment);
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
//    public void DrawerNavigation(){
//        actionbar = getSupportActionBar();
//        dr=(DrawerLayout)findViewById(R.id.drawerlayout);
//
//        abdr = new ActionBarDrawerToggle(this,dr,R.string.open,R.string.close);
//        abdr.setDrawerIndicatorEnabled(true);
//        dr.addDrawerListener(abdr);
//        abdr.syncState();
//        actionbar.setDisplayHomeAsUpEnabled(true);
//        final NavigationView na=(NavigationView)findViewById(R.id.nav_drawer);
//        na.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()) {
//                    case R.id.navigation_newsGDPT1:
//
//                        return true;
//                    case R.id.navigation_newsPS1:
//
//
//                        return true;
//                    case R.id.navigation_TuHoc1:
//
//
//                        return true;
//                    case R.id.navigation_TroChoi1:
//
//
//                        return true;
//                }
//                return false;
//            }
//        });
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_nav_activity);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        //mTextMessage = findViewById(R.id.message);

        fragment = new FragmentNewsGDPT();
        ActionBarTitleGravity("Tin tức GĐPT");


        loadFragment(fragment);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    @Override
    public void onBackPressed() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        int seletedItemId = bottomNavigationView.getSelectedItemId();

        if (R.id.navigation_newsGDPT == seletedItemId) {
            if (count == 2) {
                finish();

            } else {
                Toast.makeText(this, "Back lần nữa để thoát ứng dụng", Toast.LENGTH_SHORT).show();
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

        textview.setTextColor(Color.BLACK);

        textview.setGravity(Gravity.CENTER);

        textview.setTextSize(20);


        actionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        actionbar.setCustomView(textview);

    }
}
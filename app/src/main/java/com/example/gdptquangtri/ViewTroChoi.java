package com.example.gdptquangtri;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class ViewTroChoi extends AppCompatActivity {
    private String pudate, key, ten, noidung, kyten;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    TextView textview;
    RelativeLayout.LayoutParams layoutparams;
    private ActionBar actionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.context_trochoi);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        Intent intent = getIntent();

        pudate = intent.getStringExtra("pubdate");
        key = intent.getStringExtra("key");
        ten = intent.getStringExtra("ten");
        noidung = intent.getStringExtra("noidung");
        kyten = intent.getStringExtra("kyten");
        ActionBarTitleGravity(ten);
        TextView txtTen = findViewById(R.id.txtTenTroChoi);
        TextView txtNoidung = findViewById(R.id.noidungTroChoi);
        TextView txtkyten = findViewById(R.id.txtKyten);
        txtTen.setText(ten);
        txtNoidung.setText("     " + noidung);
        txtkyten.setText(kyten);

        //  Toast.makeText(this,link,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (firebaseUser != null) {
            getMenuInflater().inflate(R.menu.update_trochoi, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Menu_updateTrochoi:
                Intent intent = new Intent(ViewTroChoi.this, Details_Trochoi.class);
                intent.putExtra("key", key);
                intent.putExtra("pubdate", pudate);
                intent.putExtra("noidung", noidung);
                intent.putExtra("ten", ten);
                intent.putExtra("kyten", kyten);
                startActivity(intent);
                return true;
            case R.id.Menu_DeleteTrochoi:
                new FisebaseDatabase().DeleteTroChoi(key, new FisebaseDatabase.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<TroChoi> troChois, List<String> key) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdate() {

                    }

                    @Override
                    public void DataIsDelete() {
                        Toast.makeText(ViewTroChoi.this, "Xóa thành công", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }
                });
                return true;
        }
        return super.onOptionsItemSelected(item);
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

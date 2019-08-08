package com.example.gdptquangtri;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Details_Trochoi extends AppCompatActivity {
    EditText ten, noidung, nguoiviet;
    Button btnCapnhat, btnTrove;
    TextView textview;
    RelativeLayout.LayoutParams layoutparams;
    private ActionBar actionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_trochoi);
        ten = findViewById(R.id.details_tenTrochoi);
        noidung = findViewById(R.id.details_noidungTrochoi);
        nguoiviet = findViewById(R.id.details_nguoiviet);


        btnCapnhat = findViewById(R.id.btn_capnhatTrochoi);

        btnTrove = findViewById(R.id.btn_trove);


        Intent intent = getIntent();
        final String pudate1 = intent.getStringExtra("pubdate");
        final String key1 = intent.getStringExtra("key");
        String ten1 = intent.getStringExtra("ten");
        String noidung1 = intent.getStringExtra("noidung");
        String kyten1 = intent.getStringExtra("kyten");
        ActionBarTitleGravity("Cập nhật trò chơi");
        ten.setText(ten1);
        noidung.setText(noidung1);
        nguoiviet.setText(kyten1);

        btnCapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TroChoi troChoi = new TroChoi();
                troChoi.setPubDate(pudate1);
                troChoi.setNoiDung(noidung.getText().toString());
                troChoi.setTen(ten.getText().toString());
                troChoi.setKyTen(nguoiviet.getText().toString());
                new FisebaseDatabase().UpdateTroChoi(key1, troChoi, new FisebaseDatabase.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<TroChoi> troChois, List<String> key) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdate() {
                        Toast.makeText(Details_Trochoi.this, "Cập nhật thành công", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }

                    @Override
                    public void DataIsDelete() {

                    }
                });
            }
        });


        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                return;
            }
        });
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

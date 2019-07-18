package com.example.gdptquangtri;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Details_Trochoi extends AppCompatActivity {
    EditText ten, noidung, nguoiviet;
    Button btnCapnhat, btnXoa, btnTrove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_trochoi);
        ten = findViewById(R.id.details_tenTrochoi);
        noidung = findViewById(R.id.details_noidungTrochoi);
        nguoiviet = findViewById(R.id.details_nguoiviet);


        btnCapnhat = findViewById(R.id.btn_capnhatTrochoi);
        btnXoa = findViewById(R.id.btn_xoaTrochoi);
        btnTrove = findViewById(R.id.btn_trove);


        Intent intent = getIntent();
        final String pudate1 = intent.getStringExtra("pubdate");
        final String key1 = intent.getStringExtra("key");
        String ten1 = intent.getStringExtra("ten");
        String noidung1 = intent.getStringExtra("noidung");
        String kyten1 = intent.getStringExtra("kyten");

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

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FisebaseDatabase().DeleteTroChoi(key1, new FisebaseDatabase.DataStatus() {
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
                        Toast.makeText(Details_Trochoi.this, "Xóa thành công", Toast.LENGTH_LONG).show();
                        finish();
                        return;
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
}

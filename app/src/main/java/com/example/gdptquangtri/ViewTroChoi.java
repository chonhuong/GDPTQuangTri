package com.example.gdptquangtri;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ViewTroChoi extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.context_trochoi);

        Intent intent = getIntent();
        String ten = intent.getStringExtra("ten");
        String noidung = intent.getStringExtra("noidung");
        String kyten = intent.getStringExtra("kyten");
        TextView txtTen = findViewById(R.id.txtTenTroChoi);
        TextView txtNoidung = findViewById(R.id.noidungTroChoi);
        txtTen.setText("     " + ten);
        txtNoidung.setText("     " + noidung);

        //  Toast.makeText(this,link,Toast.LENGTH_SHORT).show();
    }
}

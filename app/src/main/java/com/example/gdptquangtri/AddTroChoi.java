package com.example.gdptquangtri;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddTroChoi extends AppCompatActivity {
    private EditText edTen, edNoidung, edKyten;
    private Button btnSave, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_trochoi);
        edTen = findViewById(R.id.add_tentrochoi);
        edNoidung = findViewById(R.id.add_noidungTroChoi);
        edKyten = findViewById(R.id.add_TenTacGia);

        btnSave = findViewById(R.id.add_saveTrochoi);
        btnBack = findViewById(R.id.add_backTrochoi);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat dateFormatter = new SimpleDateFormat("HH:mm dd/MM/yyyy");
                dateFormatter.setLenient(false);
                Date today = new Date();
                String s = dateFormatter.format(today);
                TroChoi troChoi = new TroChoi();
                troChoi.setTen(edTen.getText().toString());
                troChoi.setNoiDung(edNoidung.getText().toString());
                troChoi.setKyTen(edKyten.getText().toString());
                troChoi.setPubDate(s);
                new FisebaseDatabase().AddTroChoi(troChoi, new FisebaseDatabase.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<TroChoi> troChois, List<String> key) {

                    }

                    @Override
                    public void DataIsInserted() {
                        Toast.makeText(AddTroChoi.this, "Thêm thành công", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }

                    @Override
                    public void DataIsUpdate() {

                    }

                    @Override
                    public void DataIsDelete() {

                    }
                });

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                return;
            }
        });
    }
}

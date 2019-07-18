package com.example.gdptquangtri;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ViewTroChoi extends AppCompatActivity {
    private String pudate, key, ten, noidung, kyten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.context_trochoi);

        Intent intent = getIntent();
        pudate = intent.getStringExtra("pubdate");
        key = intent.getStringExtra("key");
        ten = intent.getStringExtra("ten");
        noidung = intent.getStringExtra("noidung");
        kyten = intent.getStringExtra("kyten");
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
        getMenuInflater().inflate(R.menu.update_trochoi, menu);
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
        }
        return super.onOptionsItemSelected(item);
    }
}

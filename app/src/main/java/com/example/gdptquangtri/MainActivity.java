package com.example.gdptquangtri;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    ActionBar actionBar;
    private EditText edtUser, edtPass;
    private TextView txtClickDK;
    FirebaseAuth mAuth;
    private Button btnDN, btnSudung;

    //------------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        actionBar = getSupportActionBar();
        actionBar.hide();
        btnSudung = findViewById(R.id.btn_Sudungngay);
        mAuth = FirebaseAuth.getInstance();
        edtUser = findViewById(R.id.login);
        edtPass = findViewById(R.id.pass);
        btnDN = findViewById(R.id.btn_dangnhap);
        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangNhap();
            }
        });

        //    txtClickDK = findViewById(R.id.txt_clickDK);
//        txtClickDK.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, DangKyThanhVien.class);
//                startActivity(intent);
//
//
//            }
//        });

        btnSudung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BottomNavActivity.class));
                finish();
            }
        });
//
    }


    private void DangNhap() {

        String email = edtUser.getText().toString();
        String password = edtPass.getText().toString();
        if (email.equalsIgnoreCase("") || password.equalsIgnoreCase("")) {
            Toast.makeText(MainActivity.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_LONG).show();
        } else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(MainActivity.this, BottomNavActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_LONG).show();

                            }

                            // ...
                        }
                    });
        }
    }


}

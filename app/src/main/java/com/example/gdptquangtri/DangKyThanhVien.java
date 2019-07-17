package com.example.gdptquangtri;

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

public class DangKyThanhVien extends AppCompatActivity {
    ActionBar actionBar;
    private EditText edtUser, edtPass;
    private TextView txtClickDK;
    private FirebaseAuth mAuth;
    private Button btnDK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addthanhvien_activity);
        actionBar = getSupportActionBar();
        actionBar.hide();
        edtUser = findViewById(R.id.loginDK);
        edtPass = findViewById(R.id.passDK);
        mAuth = FirebaseAuth.getInstance();
    }

    private void DangKy() {

        String email = edtUser.getText().toString();
        String password = edtPass.getText().toString();
        if (email.equalsIgnoreCase("") || password.equalsIgnoreCase("")) {
            Toast.makeText(DangKyThanhVien.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_LONG).show();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(DangKyThanhVien.this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
                                finish();
                                return;
                            } else {
                                Toast.makeText(DangKyThanhVien.this, "Đăng ký không thành công", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    public void ClickDangKy(View view) {
        DangKy();
    }
}

package com.example.personapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.drawable.ColorDrawable;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin,btnSignup;
    private TextView txtMiss;
    private ImageButton cancelButton;
    private Button ok_btn;
    private Dialog dialog1;
    private Button ShowDialog;
    private Handler hander;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getID();
        start();
    }
    private void getID(){
        btnSignup = findViewById(R.id.btnSignup);
        btnLogin = findViewById(R.id.btnLogin);
        txtMiss = findViewById(R.id.txtMiss);
    }
    private void start(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT);
                toast.show();
                // Toast sẽ hiển thị thông báo “Đăng nhập thành công” trong 2 giây.
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        Intent login = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(login);
                    }
                }, 1000); // 1000 milliseconds = 1 seconds
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Sms();
            }
        });
        View alertCustomDialog = LayoutInflater.from(LoginActivity.this).inflate(R.layout.custom_dialog,null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);

        alertDialog.setView(alertCustomDialog);
        cancelButton = (ImageButton) alertCustomDialog.findViewById(R.id.cancelID);
        ok_btn = (Button) alertCustomDialog.findViewById(R.id.ok_btn_id);

        final  AlertDialog dialog = alertDialog.create();
        findViewById(R.id.txtMiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                Toast.makeText(LoginActivity.this, "Thanks for watching", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Sms(){
        Toast.makeText(LoginActivity.this,"Chức năng đang trong quá trình phát triển",Toast.LENGTH_SHORT).show();
    }

}
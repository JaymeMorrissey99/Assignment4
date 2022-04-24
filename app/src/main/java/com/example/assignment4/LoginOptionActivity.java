package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginOptionActivity extends AppCompatActivity {

    Button logincBtn, loginaBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_option);

        logincBtn = findViewById(R.id.logincustomerOption);
        loginaBtn = findViewById(R.id.loginadminOption);

        logincBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginOptionActivity.this, LoginCustomer.class);
                startActivity(intent);
                finish();
                onBackPressed();
            }
        });

        loginaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginOptionActivity.this, LoginAdmin.class);
                startActivity(intent);
                finish();
                onBackPressed();
            }
        });
    }
}
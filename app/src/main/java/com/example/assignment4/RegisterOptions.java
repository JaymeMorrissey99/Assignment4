package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterOptions extends AppCompatActivity {

    Button registercBtn, registeraBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_options);

        registercBtn = findViewById(R.id.customerOption);
        registeraBtn = findViewById(R.id.adminOption);

        registercBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterOptions.this, RegisterCustomer.class);
                startActivity(intent);
                finish();
                onBackPressed();
            }
        });

        registeraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterOptions.this, RegisterAdmin.class);
                startActivity(intent);
                finish();
                onBackPressed();
            }
        });
    }
}
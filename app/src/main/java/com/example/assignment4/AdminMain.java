package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AdminMain extends AppCompatActivity {

    Button stockbtn, customerdbtn, purchase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        stockbtn = findViewById(R.id.stockBtn);
        customerdbtn = findViewById(R.id.cusBtn);
        purchase = findViewById(R.id.pHistory);


        stockbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminMain.this, "Successful Register", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminMain.this, AdminStock.class);
                startActivity(intent);
            }
        });

        customerdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminMain.this, "Successful Register", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminMain.this, AdminCustomerDetails.class);
                startActivity(intent);
            }
        });

        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminMain.this, "Successful Register", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminMain.this, AdminPurchaseHistory.class);
                startActivity(intent);

            }
        });


    }
}
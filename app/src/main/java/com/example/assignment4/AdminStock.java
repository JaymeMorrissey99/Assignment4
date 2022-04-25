package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class AdminStock extends AppCompatActivity {

    RecyclerView sRV;
    FloatingActionButton addS;
    DatabaseReference stockRef, userRef;
    FirebaseAuth mAuth;
    FirebaseUser muser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_stock);

        sRV = findViewById(R.id.stockrv);
        addS = findViewById(R.id.addStock);

        addS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToAddStock();
            }
        });
    }

    private void sendToAddStock() {
        //Toast.makeText(AdminStock.this, "Successful Register", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AdminStock.this, AddStock.class);
        startActivity(intent);
    }
}
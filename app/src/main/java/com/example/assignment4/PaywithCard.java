package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class PaywithCard extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseDatabase cardRef;

    TextView cardName, cardNumber, cardEx;
    EditText c;

    Button payBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paywith_card);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        cardName = findViewById(R.id.usercardName);
        cardNumber = findViewById(R.id.usercardNumber);
        cardEx = findViewById(R.id.userexpireDate);
        c = findViewById(R.id.usercvc);
        payBtn = findViewById(R.id.paybtn);

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        LoadData();



    }

    private void LoadData() {

    }
}
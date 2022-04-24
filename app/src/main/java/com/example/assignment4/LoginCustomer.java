package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class LoginCustomer extends AppCompatActivity {

    private EditText cp, ce;
    private Button mLogin, mRergister;
    private TextView mgotoforgotP;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String uid;
    DatabaseReference databaseReference, curRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_customer);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        cp = findViewById(R.id.e);
        ce = findViewById(R.id.p);
        mLogin = findViewById(R.id.loginBtn);


        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
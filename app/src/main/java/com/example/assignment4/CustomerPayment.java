package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class CustomerPayment extends AppCompatActivity {

    EditText cardname, cardnumber, expirdate, cvc;
    Button registerpaymentBtn;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    DatabaseReference cardRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_payment);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        cardRef = FirebaseDatabase.getInstance().getReference().child("Cardpayments");

        cardname = findViewById(R.id.cardName);
        cardnumber = findViewById(R.id.cardNumber);
        expirdate = findViewById(R.id.expireDate);
        cvc = findViewById(R.id.cvc);

        registerpaymentBtn = findViewById(R.id.registerpaymentButton);

        registerpaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpPayment();
            }
        });


    }

    private void setUpPayment() {
        String cn = cardname.getText().toString().trim();
        String cnum = cardnumber.getText().toString().trim();
        String xp = expirdate.getText().toString().trim();
        String c = cvc.getText().toString().trim();

        if(cnum.length()<16){
            Toast.makeText(CustomerPayment.this,"Card Number Must be more then 16 digits",Toast.LENGTH_SHORT).show();
        }
        else if(c.length()!=3){
            Toast.makeText(CustomerPayment.this,"CVC must be 3 digits",Toast.LENGTH_SHORT).show();
        }
        else if(xp.length()!=5){
            Toast.makeText(CustomerPayment.this,"CVC must be 3 digits",Toast.LENGTH_SHORT).show();
        }
        else{
            Map<String, Object> user = new HashMap<>();
            String userid = mUser.getUid();
            user.put("cardNumber", cnum);
            user.put("cardName", cn);
            user.put("expiry", xp);
            user.put("cvc", c);
            cardRef.child(mUser.getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(CustomerPayment.this, "Payment Method Registered", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CustomerPayment.this, LoginCustomer.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                }
            });

        }


    }
}
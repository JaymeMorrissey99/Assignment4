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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterCustomer extends AppCompatActivity {

    EditText registercemail, registercpassword, confirmcpassword, shipaddress, fullname;
    Button registercbutton;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_customer);

        mAuth = FirebaseAuth.getInstance();
        registercemail = findViewById(R.id.registercEmail);
        registercpassword = findViewById(R.id.registercPassword);
        confirmcpassword = findViewById(R.id.confirmcPassword);
        shipaddress = findViewById(R.id.shipaddress);
        fullname = findViewById(R.id.fullName);
        registercbutton = findViewById(R.id.registercButton);

        userRef= FirebaseDatabase.getInstance().getReference().child("Users");


        registercbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerCustomer();
            }
        });



    }

    private void registerCustomer() {

        String e = registercemail.getText().toString().trim();
        String p = registercpassword.getText().toString().trim();
        String cp = confirmcpassword.getText().toString().trim();
        String s = shipaddress.getText().toString().trim();
        String fn = fullname.getText().toString().trim();

        if(!cp.equals(p)){
            Toast.makeText(RegisterCustomer.this,"Passwords Do Not Match",Toast.LENGTH_SHORT).show();
        }
        else{

            mAuth.createUserWithEmailAndPassword(e, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        mUser = mAuth.getCurrentUser();
                        String userid = mUser.getUid();
                        Map<String, Object> user = new HashMap<>();
                        user.put("id", userid);
                        user.put("Type", "Customer");
                        user.put("Email", e);
                        user.put("Password", p);
                        user.put("Fullname", fn);
                        user.put("ShippingAddress", s);
                        userRef.child(userid).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(RegisterCustomer.this, "Successful Register", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterCustomer.this, CustomerPayment.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
//                        Toast.makeText(RegisterModelActivity.this, "Successful Register", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(RegisterModelActivity.this, ModelSetUpActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(intent);
//                        finish();
                    }
                    else{
                        Toast.makeText(RegisterCustomer.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }

    }


}
package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Models.User;

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

        ce = findViewById(R.id.e);
        cp = findViewById(R.id.p);
        mLogin = findViewById(R.id.loginBtn);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");


        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginCustomer();
            }
        });
    }

    private void LoginCustomer() {


        String email = ce.getText().toString().trim();
        String pass = cp.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    mUser = mAuth.getCurrentUser();
                    uid = mUser.getUid();
                    CheckUserType(uid);
//                    Intent intent = new Intent(LoginCustomer.this, MainActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//                    finish();
                }
                else{
                    Toast.makeText(LoginCustomer.this, "Fehler"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    //POTENTIAL PATTERN

    private void CheckUserType(String uid) {
        //uid = mUser.getUid();
        databaseReference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String type = snapshot.child("Type").getValue().toString();
                    if(type.equals("Admin")){
                        Intent intent = new Intent(LoginCustomer.this, AdminMain.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();

                    }else if(type.equals("Customer")){
                        Intent intent = new Intent(LoginCustomer.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }

                }
//                User user = snapshot.getValue(User.class);
//                if(user.getType().equals("Company")){
//                    sendToCompanyMain();
//                }
//                else if(user.getType().equals("Model")){
//                    sendToModelMain();
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
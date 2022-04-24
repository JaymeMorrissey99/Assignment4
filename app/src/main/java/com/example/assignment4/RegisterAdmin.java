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

public class RegisterAdmin extends AppCompatActivity {

    EditText registeraemail, registerapassword, confirmapassword;
    Button registerabutton;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);

        mAuth = FirebaseAuth.getInstance();
        registeraemail = findViewById(R.id.aEmail);
        registerapassword = findViewById(R.id.aPassword);
        confirmapassword = findViewById(R.id.acPassword);
        registerabutton = findViewById(R.id.registeraButton);

        userRef= FirebaseDatabase.getInstance().getReference().child("Users");

        registerabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerAdmin();
            }
        });

    }


    private void registerAdmin() {

        String e = registeraemail.getText().toString().trim();
        String p = registerapassword.getText().toString().trim();
        String cp = confirmapassword.getText().toString().trim();

        if(!cp.equals(p)){
            Toast.makeText(RegisterAdmin.this,"Passwords Do Not Match",Toast.LENGTH_SHORT).show();
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
                        user.put("Type", "Admin");
                        user.put("Email", e);
                        user.put("Password", p);
                        userRef.child(userid).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(RegisterAdmin.this, "Successful Register", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterAdmin.this, LoginCustomer.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
                    }
                    else{
                        Toast.makeText(RegisterAdmin.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }

    }
}
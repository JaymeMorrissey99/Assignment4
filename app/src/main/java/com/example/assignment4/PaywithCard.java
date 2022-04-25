package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PaywithCard extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference cardRef;
    String cname, cnum, cex, cvc;

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

        cardRef = FirebaseDatabase.getInstance().getReference().child("Cardpayments");

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pay();
            }
        });

        LoadData();



    }

    private void pay() {
        String inputcvc = c.getText().toString().trim();
        if(inputcvc.equalsIgnoreCase(cvc)){
            Toast.makeText(this, "Correct Pin", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Wrong PiN\nPlease Try Again", Toast.LENGTH_SHORT).show();
        }
    }

    private void LoadData() {

        cardRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    cname = snapshot.child("cardName").getValue().toString();
                    cnum = snapshot.child("cardNumber").getValue().toString();
                    cex = snapshot.child("expiry").getValue().toString();
                    cvc = snapshot.child("cvc").getValue().toString();

                    cardName.setText(cname);
                    cardNumber.setText(cnum);
                    cardEx.setText(cex);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//itemRef.child(itemid).addValueEventListener(new ValueEventListener() {
//@Override
//public void onDataChange(@NonNull DataSnapshot snapshot) {
//        if(snapshot.exists()){
////                    for(DataSnapshot d: snapshot.getChildren()){
//        itemNAME = snapshot.child("itemName").getValue().toString();
//        itemC = snapshot.child("category").getValue().toString();
//        itemP = snapshot.child("itemprice").getValue().toString();
//        itemM = snapshot.child("manufacturer").getValue().toString();
//
//        //error check q
//
//        itemtitle.setText(itemNAME);
//        itemcategory.setText(itemC);
//        itemprice.setText(itemP);
//        itemmanuf.setText(itemM);
//
//
//        //}
//
//        }
    }
}


//itemRef.child(itemid).addValueEventListener(new ValueEventListener() {
//@Override
//public void onDataChange(@NonNull DataSnapshot snapshot) {
//        if(snapshot.exists()){
////                    for(DataSnapshot d: snapshot.getChildren()){
//        itemNAME = snapshot.child("itemName").getValue().toString();
//        itemC = snapshot.child("category").getValue().toString();
//        itemP = snapshot.child("itemprice").getValue().toString();
//        itemM = snapshot.child("manufacturer").getValue().toString();
//
//        //error check q
//
//        itemtitle.setText(itemNAME);
//        itemcategory.setText(itemC);
//        itemprice.setText(itemP);
//        itemmanuf.setText(itemM);
//
//
//        //}
//
//        }
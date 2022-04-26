package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Models.Cart;

public class PaywithCard extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference cardRef, purchaseRef, cartref, stockRef;
    String cname, cnum, cex, cvc;
    final static String TAG = "cart";

    TextView cardName, cardNumber, cardEx;
    EditText c;
    ArrayList<String>ids;

    String itemid, quan;
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

        ids = getIntent().getStringArrayListExtra("itemIDS");

        Log.d(TAG, "onCreate: "+ ids.toString());



        //Toast.makeText(this, ""+myCartItems.toString(), Toast.LENGTH_SHORT).show();
        cardRef = FirebaseDatabase.getInstance().getReference().child("Cardpayments");
        purchaseRef = FirebaseDatabase.getInstance().getReference().child("Purchases");
        stockRef = FirebaseDatabase.getInstance().getReference().child("Stock");
        cartref = FirebaseDatabase.getInstance().getReference().child("MyCart");


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
        String u = mUser.getUid();
        if (!inputcvc.equalsIgnoreCase(cvc)) {
            Toast.makeText(this, "Wrong PiN\nPlease Try Again", Toast.LENGTH_SHORT).show();
        } else if (inputcvc.equalsIgnoreCase(cvc)) {
            Toast.makeText(this, "Correct Pin", Toast.LENGTH_SHORT).show();

        Query q = cartref.orderByChild("CustomerID").equalTo(u);
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot d : snapshot.getChildren()) {
                        itemid = d.child("itemID").getValue().toString();
                        quan = d.child("itemquantity").getValue().toString();
                        Log.d(TAG, "onDataChange: " + itemid);
                        Log.d(TAG, "onDataChange: " + q);
                        Map<String, Object> purchase = new HashMap<>();
                        String customerID = mUser.getUid();
                        purchase.put("itemID", itemid);
                        purchase.put("quantity", quan);
                        purchaseRef.child(customerID).push().setValue(purchase).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(PaywithCard.this, "Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(PaywithCard.this, LeaveReview.class);
//                                                intent.putExtras(b);
                                    //intent.putParcelableArrayListExtra("c", (ArrayList<? extends Parcelable>) itemIDS);
                                    //intent.putParcelableArrayListExtra("cartListkey", (ArrayList<? extends Parcelable>) cartList);
                                    startActivity(intent);
                                }
                            }
                        });

                    }
                    UpdateStock();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    }

    private void UpdateStock() {

        stockRef.child(itemid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String stockQ = snapshot.child("quantity").getValue().toString();
                    int sQ = Integer.parseInt(stockQ);
                    int quantity = Integer.parseInt(quan);
                    int updatedStock = sQ - quantity;
                    String updatedS = String.valueOf(updatedStock);
                    HashMap<String, Object> updateDetails = new HashMap<>();
                    updateDetails.put("quantity", updatedS);
                    stockRef.child(itemid).updateChildren(updateDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
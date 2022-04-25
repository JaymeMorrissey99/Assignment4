package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adaptor.MyCartAdaptor;
import Models.Cart;

public class Payment extends AppCompatActivity {

    RecyclerView payRV;
    TextView totalPrice;
    DatabaseReference stockRef, cartRef;
    FirebaseAuth mAuth;
    FirebaseUser muser;
    DatabaseReference stockref;
    Button prop;

    private MyCartAdaptor myCartAdaptor;
    private List<Cart> cartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        mAuth = FirebaseAuth.getInstance();
        muser = mAuth.getCurrentUser();
        payRV = findViewById(R.id.paymentRV);
        totalPrice = findViewById(R.id.ttlPrice);
        prop = findViewById(R.id.proceedPurchase);

        prop.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { SendToPayment(); }});
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        payRV.setLayoutManager(linearLayoutManager);
        //new ItemTouchHelper(itemtouch).attachToRecyclerView(cartRV);
        cartList = new ArrayList<>();
        myCartAdaptor = new MyCartAdaptor(this, cartList);
        payRV.setAdapter(myCartAdaptor);

        LoadCart();
        GetTotalAmount();
    }

    private void GetTotalAmount() {

    }

    private void SendToPayment() {
        Intent intent = new Intent(Payment.this, PaywithCard.class);
        //intent.putExtra("totalAmt", totalAmount);
        startActivity(intent);
    }

    private void LoadCart() {
        String u = muser.getUid();
        cartRef = FirebaseDatabase.getInstance().getReference().child("MyCart");
        Query q = cartRef.orderByChild("CustomerID").equalTo(u);

        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cartList.clear();
                for(DataSnapshot d: snapshot.getChildren()){
                    Cart c = d.getValue(Cart.class);
                    cartList.add(c);
                }
                myCartAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
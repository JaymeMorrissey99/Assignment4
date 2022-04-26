package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.lang.reflect.Array;
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

    final static String TAG = "cart";
    private MyCartAdaptor myCartAdaptor;
    private List<Cart> cartList;
//    ArrayList<String>qp;
    ArrayList<String>itemIDS;



    double ttl, ttlprice;
    int q;
    double p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        mAuth = FirebaseAuth.getInstance();
        muser = mAuth.getCurrentUser();
        payRV = findViewById(R.id.paymentRV);
        totalPrice = findViewById(R.id.ttlPrice);
        prop = findViewById(R.id.proceedPurchase);

        itemIDS = new ArrayList<>();

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

    private Double GetTotalAmount() {

        stockref =  FirebaseDatabase.getInstance().getReference().child("Stock");
        cartRef = FirebaseDatabase.getInstance().getReference().child("MyCart");
//        double ttl;
//        int q;
//        double p;
        String u = muser.getUid();
        Query query = cartRef.orderByChild("CustomerID").equalTo(u);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot d: snapshot.getChildren()){
                        String p = d.child("itemprice").getValue().toString();
                        String q = d.child("itemquantity").getValue().toString();
                        ttlprice = Double.parseDouble(p)*Integer.parseInt(q);
                        ttl = ttl + ttlprice;
//                        Cart c = d.getValue(Cart.class);
//                        cartTotal = new ArrayList<>();
//                        cartTotal.add(c);
                        //cartTotal.add(qu);
                        //calculate(cartList);
                        totalPrice.setText(String.valueOf(ttl));
                        Toast.makeText(Payment.this, ""+ ttl, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //cartRef.
        return null;
    }

//    private void calculate(List<Cart> cartList) {
//
//        for(Cart i: cartList){
//            String p = i.getItemprice();
//            String q = i.getItemquantity();
//
//            ttlprice = Double.parseDouble(p)*Integer.parseInt(q);
//
////            Double price = Double.parseDouble(p);
////            int quan = Integer.parseInt(q);
//
//            //Toast.makeText(this, ""+ price, Toast.LENGTH_SHORT).show();
//            //ttlprice = price * quan;
//            ttl = ttl + ttlprice;
//            totalPrice.setText(String.valueOf(ttl));
//
//        }
////        Cart c = qp.get(i);
////        for( d: qp){
////            String
////        }
//    }

    private void SendToPayment() {
        //Array<Cart>cartList
        //cartList = (ArrayList<Cart>) getIntent().getSerializableExtra("cartlistkey");
        Bundle b = new Bundle();
        b.putStringArrayList("itemIDS", itemIDS);
        Intent intent = new Intent(Payment.this, PaywithCard.class);
        intent.putExtras(b);
        //intent.putParcelableArrayListExtra("c", (ArrayList<? extends Parcelable>) itemIDS);
        //intent.putParcelableArrayListExtra("cartListkey", (ArrayList<? extends Parcelable>) cartList);
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
                    //Log.d(TAG, "onDataChange: "+cartList.toString());
                    itemIDS.add(c.getItemID());
                    itemIDS.add(c.getItemquantity());
                    //Log.d(TAG, "onDataChange: "+ itemIDS.toString());
                }
                myCartAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        Cart c = (Cart) cartList;
//        parcel.writeString(c.getItemID());
//    }
}
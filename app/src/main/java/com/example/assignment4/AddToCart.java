package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddToCart extends AppCompatActivity {

    ImageView iPic;
    TextView itemtitle, itemcategory, itemprice, itemmanuf;
    EditText iq;
    Button addsbtn;
    String itemid;
    String itemNAME, itemC, itemP, itemQ, itemM;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    DatabaseReference itemRef, mycart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        mycart = FirebaseDatabase.getInstance().getReference().child("MyCart");
        itemRef = FirebaseDatabase.getInstance().getReference().child("Stock");
        itemid = getIntent().getExtras().get("stockitemKey").toString();
        iPic = findViewById(R.id.itemPic);
        itemtitle = findViewById(R.id.ititle);
        itemcategory = findViewById(R.id.icategory);
        itemprice = findViewById(R.id.iprice);
        itemmanuf = findViewById(R.id.imanu);
        iq = findViewById(R.id.iquantity);

        addsbtn = findViewById(R.id.additem);

        addsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemtoCart();
            }
        });

        LoadItems();
    }

    private void LoadItems() {

        itemRef.child(itemid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
//                    for(DataSnapshot d: snapshot.getChildren()){
                        itemNAME = snapshot.child("itemName").getValue().toString();
                        itemC = snapshot.child("category").getValue().toString();
                        itemP = snapshot.child("itemprice").getValue().toString();
                        itemM = snapshot.child("manufacturer").getValue().toString();

                        itemtitle.setText(itemNAME);
                        itemcategory.setText(itemC);
                        itemprice.setText(itemP);
                        itemmanuf.setText(itemM);


                    //}

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void addItemtoCart() {



    }
}
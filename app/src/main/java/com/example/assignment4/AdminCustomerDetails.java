package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adaptor.CustomerAdaptor;
import Adaptor.StockAdaptor;
import Models.Customer;
import Models.Stock;

public class AdminCustomerDetails extends AppCompatActivity {

    RecyclerView cusRV;
    FloatingActionButton addS;
    DatabaseReference stockRef, userRef;
    FirebaseAuth mAuth;
    FirebaseUser muser;
    DatabaseReference stockref;

    private CustomerAdaptor customerAdaptor;
    private List<Customer> cList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_customer_details);

        cusRV = findViewById(R.id.customerRV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        cusRV.setLayoutManager(linearLayoutManager);
        cList = new ArrayList<>();
        customerAdaptor = new CustomerAdaptor(this, cList);
        cusRV.setAdapter(customerAdaptor);

        LoadStock();


    }

    private void LoadStock() {
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cList.clear();
                for(DataSnapshot d: snapshot.getChildren()){
                    Customer c = d.getValue(Customer.class);
                    if(c.getType().equalsIgnoreCase("Customer")){
                        cList.add(c);
                    }

                }
                customerAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
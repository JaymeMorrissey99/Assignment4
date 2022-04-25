package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

import Adaptor.StockAdaptor;
import Models.Stock;
import VH.StockVH;

public class AdminStock extends AppCompatActivity {

    RecyclerView sRV;
    FloatingActionButton addS;
    DatabaseReference stockRef, userRef;
    FirebaseAuth mAuth;
    FirebaseUser muser;
    DatabaseReference stockref;

    private StockAdaptor stockAdaptor;
    private List<Stock> sList;



//    FirebaseRecyclerOptions<Stock> myjoptions;
//    FirebaseRecyclerAdapter<Stock, StockVH> myjobadaptor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_stock);

        sRV = findViewById(R.id.stockrv);
        addS = findViewById(R.id.addStock);

        stockref = FirebaseDatabase.getInstance().getReference().child("Stock");
        addS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToAddStock();
            }
        });

//        sRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        sRV.setLayoutManager(linearLayoutManager);
        sList = new ArrayList<>();
        stockAdaptor = new StockAdaptor(this, sList);
        sRV.setAdapter(stockAdaptor);

        LoadStock();
    }

    private void LoadStock(){
        stockref = FirebaseDatabase.getInstance().getReference().child("Stock");

        stockref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sList.clear();
                for(DataSnapshot d: snapshot.getChildren()){
                    Stock s = d.getValue(Stock.class);
                    sList.add(s);
                }
                stockAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

//    private void LoadStock(String s) {
////        Query q = stockRef.orderByKey()
//        Query q = stockRef.orderByChild("itemID");
//        myjoptions = new FirebaseRecyclerOptions.Builder<Stock>().setQuery(q, Stock.class).build();
//        myjobadaptor = new FirebaseRecyclerAdapter<Stock, StockVH>(myjoptions) {
//            @Override
//            protected void onBindViewHolder(@NonNull StockVH stockVH, int i, @NonNull Stock stock) {
//                stockVH.itemN.setText(stock.getItemName());
//                stockVH.itemB.setText(stock.getManufacturer());
//                stockVH.itemP.setText(String.valueOf(stock.getPrice()));
//                stockVH.itemQuan.setText(stock.getQuantity());
////                holder.myjobTitle.setText(model.getJobTitle());
////                holder.myjobcompanyName.setText(model.getPublisher());
////                holder.myjobTitle.setText(model.getJobTitle());
//                stockVH.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//
//
//            @NonNull
//            @Override
//            public StockVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_item, parent, false);
//                return new StockVH(v);
//            }
//        };
//        myjobadaptor.startListening();
//        sRV.setAdapter(myjobadaptor);
//    }

    private void sendToAddStock() {
        //Toast.makeText(AdminStock.this, "Successful Register", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AdminStock.this, AddStock.class);
        startActivity(intent);
    }
}
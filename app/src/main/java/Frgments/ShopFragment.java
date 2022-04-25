package Frgments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment4.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adaptor.StockAdaptor;
import Models.Stock;

public class ShopFragment extends Fragment {

    RecyclerView shopRV;
    DatabaseReference stockRef, userRef;
    FirebaseAuth mAuth;
    FirebaseUser muser;
    DatabaseReference stockref;

    private StockAdaptor stockAdaptor;
    private List<Stock> shoppingList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_shop, container, false);

        shopRV = v.findViewById(R.id.shoppingRV);

        stockref = FirebaseDatabase.getInstance().getReference().child("Stock");


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        shopRV.setLayoutManager(linearLayoutManager);
        shoppingList = new ArrayList<>();
        stockAdaptor = new StockAdaptor(getContext(), shoppingList);
        shopRV.setAdapter(stockAdaptor);

        LoadItems();

        return v;
    }

    private void LoadItems() {

        stockref = FirebaseDatabase.getInstance().getReference().child("Stock");

        stockref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                shoppingList.clear();
                for(DataSnapshot d: snapshot.getChildren()){
                    Stock s = d.getValue(Stock.class);
                    shoppingList.add(s);
                }
                stockAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
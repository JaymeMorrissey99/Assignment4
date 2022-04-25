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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adaptor.StockAdaptor;
import Models.Stock;
import de.hdodenhof.circleimageview.CircleImageView;

public class ShopFragment extends Fragment {

    RecyclerView shopRV;
    DatabaseReference stockRef, userRef;
    FirebaseAuth mAuth;
    FirebaseUser muser;
    DatabaseReference stockref;
    CircleImageView shoebtn, jacketbtn, tshirtbtn, pantsbtn, reset;

    private StockAdaptor stockAdaptor;
    private List<Stock> shoppingList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_shop, container, false);

        shopRV = v.findViewById(R.id.shoppingRV);

        shoebtn = v.findViewById(R.id.shoe);
        jacketbtn = v.findViewById(R.id.jacket);
        tshirtbtn = v.findViewById(R.id.tshirts);
        pantsbtn = v.findViewById(R.id.pants);
        reset = v.findViewById(R.id.reset);

        stockref = FirebaseDatabase.getInstance().getReference().child("Stock");


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        shopRV.setLayoutManager(linearLayoutManager);
        shoppingList = new ArrayList<>();
        stockAdaptor = new StockAdaptor(getContext(), shoppingList);
        shopRV.setAdapter(stockAdaptor);

        shoebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadShoes();
            }
        });


        jacketbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadJackets();
            }
        });

        tshirtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadTShirts();
            }
        });

        pantsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadPants();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadItems();
            }
        });


        LoadItems();

        return v;
    }

    private void LoadPants() {
    }

    private void LoadTShirts() {
    }

    private void LoadJackets() {

        Query q = stockref.orderByChild("category").equalTo("Jacket");
        q.addValueEventListener(new ValueEventListener() {
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

    private void LoadShoes() {

        Query q = stockref.orderByChild("category").equalTo("Shoes");
        q.addValueEventListener(new ValueEventListener() {
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
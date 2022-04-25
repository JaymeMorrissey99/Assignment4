package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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

public class MyCart extends AppCompatActivity{

    RecyclerView cartRV;
    TextView options;
    DatabaseReference stockRef, cartRef;
    FirebaseAuth mAuth;
    FirebaseUser muser;
    DatabaseReference stockref;
    Button pay;

    private MyCartAdaptor myCartAdaptor;
    private List<Cart> cartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        mAuth = FirebaseAuth.getInstance();
        muser = mAuth.getCurrentUser();
        cartRV = findViewById(R.id.mycart);
        pay = findViewById(R.id.payment);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        cartRV.setLayoutManager(linearLayoutManager);
        new ItemTouchHelper(itemtouch).attachToRecyclerView(cartRV);
        cartList = new ArrayList<>();
        myCartAdaptor = new MyCartAdaptor(this, cartList);
        cartRV.setAdapter(myCartAdaptor);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyCart.this, Payment.class);
                startActivity(intent);
            }
        });


        LoadCart();
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

//    @Override
//    public void onItemLongClicked(int position) {
////        onCreateContextMenu(position);
//    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.cartitemmenu, menu);
//
//    }

    ItemTouchHelper.SimpleCallback itemtouch = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//            cartList.remove(viewHolder.getAdapterPosition());
            myCartAdaptor.deleteItem(viewHolder.getAdapterPosition());
            //Cart cart = viewHolder.get;

            myCartAdaptor.notifyDataSetChanged();
        }
    };

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

//        switch(){
//
//        }

        return super.onContextItemSelected(item);

    }
}
package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
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
import Adaptor.ReviewAdapter;
import Models.Cart;
import Models.Stock;
import VH.ReviewVH;

public class LeaveReview extends AppCompatActivity {

    RatingBar rBar;
    Button submit;
    EditText review;
    TextView itemName, itemttl;
    ImageView clothingImg;
    DatabaseReference purchaseRef, stockRef;
    RecyclerView reviewrv;
    DatabaseReference cartRef;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    private List<Cart> cartList;
    ReviewAdapter myReviewA;

    FirebaseRecyclerOptions<Stock> myjoptions;
    FirebaseRecyclerAdapter<Stock, ReviewVH> myjobadaptor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_review);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        rBar = findViewById(R.id.ratingBar);
        review = findViewById(R.id.reviewTxt);
        submit = findViewById(R.id.submitreview);
        itemName = findViewById(R.id.iNAME);
        itemttl = findViewById(R.id.ittl);
        reviewrv = findViewById(R.id.reviewRV);


        purchaseRef = FirebaseDatabase.getInstance().getReference().child("Purchases");
        stockRef = FirebaseDatabase.getInstance().getReference().child("Stock");

//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SubmitReview();
//            }
//        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        reviewrv.setLayoutManager(linearLayoutManager);
       // new ItemTouchHelper(itemtouch).attachToRecyclerView(cartRV);
        cartList = new ArrayList<>();
        myReviewA = new ReviewAdapter(this, cartList);
        reviewrv.setAdapter(myReviewA);


        LoadItem();


    }

//    private void SubmitReview() {
//
//    }

    private void LoadItem() {

        String u = mUser.getUid();
        cartRef = FirebaseDatabase.getInstance().getReference().child("MyCart");
        Query q = cartRef.orderByChild("CustomerID").equalTo(u);

        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cartList.clear();
                for(DataSnapshot d: snapshot.getChildren()){
                    Cart c = d.getValue(Cart.class);
                    cartList.add(c);
                    //itemIDS.add(c.getItemID());
                }
                myReviewA.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        String u = mUser.getUid();
//        purchaseRef.child(u).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(){
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

//        Query q = purchaseRef.child(mUser.getUid());
//        q.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    for(DataSnapshot d: snapshot.getChildren()){
//                        String id = d.child("itemID").getValue().toString();
//                        Query sQ = stockRef.child(id);
//                        myjoptions = new FirebaseRecyclerOptions.Builder<Stock>().setQuery(sQ, Stock.class).build();
//                        myjobadaptor = new FirebaseRecyclerAdapter<Stock, ReviewVH>(myjoptions) {
//                            @Override
//                            protected void onBindViewHolder(@NonNull ReviewVH holder, int i, @NonNull Stock stock) {
//
//                            }
//
//                            @NonNull
//                            @Override
//                            public ReviewVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
//
//                                return new ReviewVH(view);
//                            }
//                        };
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });



    }

//    myjoptions = new FirebaseRecyclerOptions.Builder<JobApplication>().setQuery(query, JobApplication.class).build();
//    myjobadaptor = new FirebaseRecyclerAdapter<JobApplication, MyJobViewHolder>(myjoptions) {
//        @Override
//        protected void onBindViewHolder(@NonNull MyJobViewHolder holder, int position, @NonNull JobApplication model) {
////                holder.myjobcompanyName.setText(model.getJobId());
//            holder.myjobcompanyName.setText(model.getPublisher());
//            holder.myjobTitle.setText(model.getJobTitle());
//
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(getApplicationContext(), ViewMyJobDetailsActivity.class);
//                    intent.putExtra("userKey", model.getSenderId());
////                        intent.putExtra("userKey", getRef(position).getKey().toString());
//                    intent.putExtra("jobKey", model.getJobId());
//                    startActivity(intent);
//                }
//            });
//        }
//
//        @NonNull
//        @Override
//        public MyJobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_job_item, parent, false);
//
//            return new MyJobViewHolder(view);
//        }
//    };
//        myjobadaptor.startListening();
//        myjobrv.setAdapter(myjobadaptor);
}
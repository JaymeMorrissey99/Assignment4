package Frgments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.assignment4.AdminCustomerDetails;
import com.example.assignment4.AdminMain;
import com.example.assignment4.AdminPurchaseHistory;
import com.example.assignment4.AdminStock;
import com.example.assignment4.LeaveReview;
import com.example.assignment4.LoginCustomer;
import com.example.assignment4.MyCart;
import com.example.assignment4.R;
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

import Adaptor.StockAdaptor;
import Models.Stock;

public class MyStuffFragment extends Fragment {

    Button mycart, myreview, logout;
    FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_my_stuff, container, false);

        mAuth = FirebaseAuth.getInstance();

        mycart = v.findViewById(R.id.myCart);
        myreview = v.findViewById(R.id.myRev);
        logout = v.findViewById(R.id.logout);


        mycart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(.this, "Successful Register", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MyCart.class);
                startActivity(intent);
            }
        });

        myreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(AdminMain.this, "Successful Register", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), LeaveReview.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Toast.makeText(AdminMain.this, "Successful Register", Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                Intent intent = new Intent(getContext(), LoginCustomer.class);
                startActivity(intent);


            }
        });
        return v;
    }


}
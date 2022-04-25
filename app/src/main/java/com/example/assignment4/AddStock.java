package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AddStock extends AppCompatActivity {

    ImageView iPic;
    EditText itemtitle, itemcategory, itemprice, imanuf, iq;
    Button addsbtn;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    DatabaseReference itemRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        itemRef = FirebaseDatabase.getInstance().getReference().child("Stock");

        iPic = findViewById(R.id.itemPic);
        itemtitle = findViewById(R.id.ititle);
        itemcategory = findViewById(R.id.icategory);
        itemprice = findViewById(R.id.iprice);
        imanuf = findViewById(R.id.imanu);
        iq = findViewById(R.id.iquantity);

        addsbtn = findViewById(R.id.additem);

        addsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });
    }

    private void addItem() {
        String itemName = itemtitle.getText().toString().trim();
        String itemCategory = itemcategory.getText().toString().trim();
        String itemp = itemprice.getText().toString().trim();
        String manu = imanuf.getText().toString().trim();
        String q = iq.getText().toString().trim();
        int quantity = Integer.parseInt(q);
        Double p = Double.valueOf(itemp);

        Map<String, Object> item = new HashMap<>();
        String itemId = itemRef.push().getKey();
        item.put("itemID", itemId);
        item.put("itemName", itemName);
        item.put("category", itemCategory);
        item.put("manufacturer", manu);
        item.put("itemprice", p);
        item.put("quantity", q);
        itemRef.child(itemId).setValue(item).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Item added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddStock.this, AdminMain.class);
                    startActivity(intent);
                }else
                    Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
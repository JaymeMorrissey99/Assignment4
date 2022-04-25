package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class EditCartItem extends AppCompatActivity{

    String qtext;
    NumberPicker numberPicker;
    String itemID;
    DatabaseReference cartRef;
    Button updateQ;
    String update;
    TextView qNumber;
    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cart_item);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        itemID = getIntent().getExtras().get("stockitemKey").toString();
        updateQ = findViewById(R.id.updateQ);
        cartRef = FirebaseDatabase.getInstance().getReference().child("MyCart");
        numberPicker = findViewById(R.id.numPicker);
        qNumber = findViewById(R.id.qNumber);
        updateQ = findViewById(R.id.updateQ);

//        numberPicker.setValue();
//        numberPicker.setMaxValue(5);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(5);
        numberPicker.setDisplayedValues( new String[] { "0", "1", "2", "3", "4", "5" } );

        Toast.makeText(this, ""+itemID, Toast.LENGTH_SHORT).show();
        qNumber.setText(String.format(""+ numberPicker.getValue()));

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                update = String.valueOf(i1);
                //qNumber.setText((i1));
            }
        });
//        Spinner spinner = findViewById(R.id.spinner);
//        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.quantityNumber, android.R.layout.simple_spinner_item);
//        // adapter = new ArrayAdapter<String >(EditCartItem.this, android.R.layout.simple_spinner_item, R.array.quantityNumber);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(this);

        updateQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateQuantity();
            }
        });

    }

    private void updateQuantity() {

        //update = String.valueOf(numberPicker.getValue());

        Toast.makeText(this, ""+ itemID, Toast.LENGTH_SHORT).show();

        //String u = mUser.getUid();
        Query q = cartRef.child(itemID);
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                   // if(snapshot.exists()){
                    for(DataSnapshot d: snapshot.getChildren()){
                        String cartitemID = snapshot.child("cartitemID").getValue().toString();
                             HashMap<String, Object> updateDetails = new HashMap<>();
                            updateDetails.put("itemquantity", update);
                            cartRef.child(cartitemID).updateChildren(updateDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(EditCartItem.this, "Updated", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }
                            });


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        qtext = adapterView.getItemAtPosition(i).toString();
//        Toast.makeText(getApplicationContext(), "Quantity: "+qtext, Toast.LENGTH_SHORT).show();
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }
}
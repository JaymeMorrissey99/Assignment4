package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class EditCartItem extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String qtext;
    String itemID;
    DatabaseReference cartRef;
    Button updateQ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cart_item);


        itemID = getIntent().getExtras().get("stockitemKey").toString();
        updateQ = findViewById(R.id.updateQ);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.quantityNumber, android.R.layout.simple_spinner_item);
        // adapter = new ArrayAdapter<String >(EditCartItem.this, android.R.layout.simple_spinner_item, R.array.quantityNumber);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        updateQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateQuantity();
            }
        });

    }

    private void updateQuantity() {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        qtext = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), "Quantity: "+qtext, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
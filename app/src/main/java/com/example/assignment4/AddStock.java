package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AddStock extends AppCompatActivity {

    ImageView iPic;
    EditText itemtitle, itemcategory, itemprice;
    Button addsbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);

        iPic = findViewById(R.id.itemPic);
        itemtitle = findViewById(R.id.ititle);
        itemcategory = findViewById(R.id.icategory);
        itemprice = findViewById(R.id.iprice);

        addsbtn = findViewById(R.id.additem);
    }
}
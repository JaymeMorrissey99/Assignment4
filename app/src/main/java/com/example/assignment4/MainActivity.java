package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {



    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String uid;
    DatabaseReference databaseReference, curRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_menu);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFrag= null;
                switch (item.getItemId()){
                    case R.id.shop:
                        Toast.makeText(getApplicationContext(), "Shop Selected", Toast.LENGTH_SHORT).show();
//                        selectedFrag = new CompanyHomeFragment();
                        break;
                    case R.id.mystuff:
                        Toast.makeText(getApplicationContext(), "My Stuff Selected", Toast.LENGTH_SHORT).show();
//                        selectedFrag = new SearchFragment();
                        break;
                        //Toast.makeText(getApplicationContext(), "Post Selected", Toast.LENGTH_SHORT).show();
                        //SendToPost();
//                    default:
//                        selectedFrag = new CompanyHomeFragment();

                }
//                getSupportFragmentManager().beginTransaction().replace(R.id.cfrag_containter, selectedFrag).commit();
                return true;
            }
        });

    }
}
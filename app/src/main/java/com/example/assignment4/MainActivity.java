package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

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

import Adaptor.CustomerVP;
import Frgments.MyStuffFragment;
import Frgments.ShopFragment;

public class MainActivity extends AppCompatActivity {



    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String uid;
    DatabaseReference databaseReference, curRef;
    ViewPager2 viewPager2;
    CustomerVP customerVP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        //viewPager2 = findViewById(R.id.vp);

//        FragmentManager fm = getSupportFragmentManager();
//        customerVP = new CustomerVP(fm, getLifecycle());
//        viewPager2.setAdapter(customerVP);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_containter, new ShopFragment()).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFrag= null;
                switch (item.getItemId()){
                    case R.id.shop:
                        Toast.makeText(getApplicationContext(), "Shop Selected", Toast.LENGTH_SHORT).show();
                        selectedFrag = new ShopFragment();
                        //customerVP.createFragment(0);
                        break;
                    case R.id.mystuff:
                        Toast.makeText(getApplicationContext(), "My Stuff Selected", Toast.LENGTH_SHORT).show();
                        selectedFrag = new MyStuffFragment();
                        //customerVP.createFragment(1);
                        break;
                        //Toast.makeText(getApplicationContext(), "Post Selected", Toast.LENGTH_SHORT).show();
                        //SendToPost();
//                    default:
//                        selectedFrag = new CompanyHomeFragment();

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_containter, selectedFrag).commit();
                return true;
            }
        });

    }
}
package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AdminMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_menu);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFrag= null;
                switch (item.getItemId()){
                    case R.id.home:
                        Toast.makeText(getApplicationContext(), "Home Selected", Toast.LENGTH_SHORT).show();
//                        selectedFrag = new CompanyHomeFragment();
                        break;
                    case R.id.search:
                        Toast.makeText(getApplicationContext(), "Search Selected", Toast.LENGTH_SHORT).show();
//                        selectedFrag = new SearchFragment();
                        break;
                    case R.id.addPost:
                        selectedFrag=null;
                        //Toast.makeText(getApplicationContext(), "Post Selected", Toast.LENGTH_SHORT).show();
                        //SendToPost();
                        break;
//                    default:
//                        selectedFrag = new CompanyHomeFragment();

                }
//                getSupportFragmentManager().beginTransaction().replace(R.id.cfrag_containter, selectedFrag).commit();
                return true;
            }
        });
    }
}
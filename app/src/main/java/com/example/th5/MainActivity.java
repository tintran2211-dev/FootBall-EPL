package com.example.th5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import Fragments.LatestFragment;
import Fragments.LiveFragment;
import Fragments.MoretFragment;
import Fragments.PLFragment;


public class MainActivity extends AppCompatActivity {

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                superinit();
            }

            private void superinit(){
                //        Bottomnavigation
                BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
                bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new LatestFragment()).commit();
            }

            private BottomNavigationView.OnNavigationItemSelectedListener navListener =
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            Fragment selectedFragment = null;

                            switch (item.getItemId()){
                                case R.id.nav_latest:selectedFragment = new LatestFragment();
                                    break;
                                case R.id.nav_pl:selectedFragment = new PLFragment();
                                    break;
                                case R.id.nav_live:selectedFragment = new LiveFragment();
                                    break;
                                case R.id.nav_more:selectedFragment = new MoretFragment();
                                    break;
                            }
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    selectedFragment).commit();
                            return true;
                        }
                    };

}
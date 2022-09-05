package com.example.th5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class Verify extends AppCompatActivity
            {
                TextView txt_vrf,txt_vrf1;
                Button button_vrf;
                FirebaseAuth auth;

                @Override
                protected void onCreate(Bundle savedInstanceState)
                {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_verify);
                    button_vrf = findViewById(R.id.button_vrf);
                    txt_vrf = findViewById(R.id.txt_vrf);
                    txt_vrf1 = findViewById(R.id.txt_vrf1);
                    auth = FirebaseAuth.getInstance();

                    if (!auth.getCurrentUser().isEmailVerified()){
                        button_vrf.setVisibility(View.VISIBLE);
                        txt_vrf.setVisibility(View.VISIBLE);
                    }

                    txt_vrf1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(getApplicationContext(),Login.class));
                            finish();
                        }
                    });

                    button_vrf.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            auth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Verify.this,"Verification Email sent",Toast.LENGTH_SHORT).show();
                                    button_vrf.setVisibility(View.GONE);
                                    txt_vrf.setVisibility(View.GONE);
                                }
                            });
                        }
                    });
                }
            }
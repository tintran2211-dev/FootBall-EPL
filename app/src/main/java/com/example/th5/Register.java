package com.example.th5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
                TextView txt_regis1;
                EditText edt_regis1,edt_regis2,edt_regis3,edt_regis4;
                Button button_regis1,button_regis2;
                FirebaseAuth firebaseAuth;

                @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_register);
                    firebaseAuth   = FirebaseAuth.getInstance();
                    Unit();
                }

                private void Unit(){
                    txt_regis1     = findViewById(R.id.txt_regis1);
                    edt_regis1     = findViewById(R.id.edt_regis1);
                    edt_regis2     = findViewById(R.id.edt_regis2);
                    edt_regis3     = findViewById(R.id.edt_regis3);
                    edt_regis4     = findViewById(R.id.edt_regis4);
                    button_regis1  = findViewById(R.id.button_regis1);
                    button_regis2  = findViewById(R.id.button_regis2);


                    button_regis2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(),Login.class);
                            startActivity(intent);
                        }
                    });
                    button_regis1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String fullname   = edt_regis1.getText().toString();
                            String email      = edt_regis2.getText().toString();
                            String password   = edt_regis3.getText().toString();
                            String repassword = edt_regis4.getText().toString();

                            if(fullname.isEmpty())
                            {
                                edt_regis1.setError("Hãy nhập đầy đủ tên");
                                return;
                            }
                            if(email.isEmpty())
                            {
                                edt_regis2.setError("Hãy nhập email của bạn");
                                return;
                            }
                            if(password.isEmpty())
                            {
                                edt_regis3.setError("Hãy nhập password");
                                return;
                            }
                            if(!password.equals(repassword))
                            {
                                edt_regis4.setError("Không đúng với Password đã nhập");
                                return;
                            }
                            Toast.makeText(Register.this, "Excute....",Toast.LENGTH_LONG).show();

                            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Intent it = new Intent(getApplicationContext(),Verify.class);
                                    startActivity(it);
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Register.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
}
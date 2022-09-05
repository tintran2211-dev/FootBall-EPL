package com.example.th5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import Fragments.MoretFragment;

public class Resetpass extends AppCompatActivity {
    EditText edt_rsp,edt_rsp1;
    Button button_rsp;
    FirebaseUser fuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpass);

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        edt_rsp = findViewById(R.id.edt_rsp);
        edt_rsp1 = findViewById(R.id.edt_rsp1);
        button_rsp = findViewById(R.id.button_rsp);

        button_rsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_rsp.getText().toString().isEmpty()){
                    edt_rsp.setError("Chưa nhập password");
                    return;
                }
                if(edt_rsp1.getText().toString().isEmpty()){
                    edt_rsp1.setError("Chưa nhập password");
                    return;
                }
                if(!edt_rsp.getText().toString().equals(edt_rsp1.getText().toString())){
                    edt_rsp.setError("Repassword ko giống với password");
                    return;
                }
                fuser.updatePassword(edt_rsp.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Resetpass.this,"Password đổi thành công",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MoretFragment.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Resetpass.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
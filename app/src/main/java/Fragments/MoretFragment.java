package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.th5.Login;
import com.example.th5.R;
import com.example.th5.Resetpass;
import com.google.firebase.auth.FirebaseAuth;

public class MoretFragment extends Fragment {
    FirebaseAuth fire1;
    AlertDialog.Builder reset_alert;
    LayoutInflater inflater;


    public MoretFragment(){}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_more,container,false);
        fire1 = FirebaseAuth.getInstance();
        setHasOptionsMenu(true);

        //khai báo button với biến view........
        Button button_pl = (Button)view.findViewById(R.id.button_pl);
        button_pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    fire1.signOut();
                    startActivity(new Intent(getContext(), Login.class));
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.reset_option,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.resetpass)
        {
            fire1.signOut();
            startActivity(new Intent(getContext(), Resetpass.class));
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.example.clinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.clinicapp.AddAngiogram2;
import com.example.clinicapp.Angiogram1List;
import com.example.clinicapp.EchoList;
import com.example.clinicapp.ListAngiogram2;
import com.example.clinicapp.R;
import com.example.clinicapp.UpdateActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class CheckAngiograms extends AppCompatActivity {

    ImageButton angiogram1explorebtn, angiogram2explorebtn, echoexplorebtn;
    Button homebtn;

    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_angiograms);

        angiogram1explorebtn = findViewById(R.id.angiogram1explorebtn);
//        angiogram2explorebtn = findViewById(R.id.angiogram2explorebtn);
        echoexplorebtn = findViewById(R.id.echoexplorebtn);
        homebtn = findViewById(R.id.homebtn);

        nav = findViewById(R.id.nav);

        nav.setSelectedItemId(R.id.explore);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        return true;
                    case R.id.explore:
//                        startActivity(new Intent(getApplicationContext(), UpdateActivity.class));
//                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), SearchUserRecyclerView.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        return true;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), PatientsList.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        return true;
                }
                return false;
            }
        });

        angiogram1explorebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), Angiogram1List.class);
                startActivity(in);
            }
        });

//        angiogram2explorebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent in = new Intent(getApplicationContext(), Angiogram1List.class);
//                startActivity(in);
//            }
//        });

        echoexplorebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), EchoList.class);
                startActivity(in);
            }
        });

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), UpdateActivity.class);
                startActivity(in);
            }
        });
    }
}
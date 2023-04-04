package com.example.clinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.clinicapp.Model.Patients;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button addpatient, patients, home, addnote, specialremarks, vsctbtn, vsctactivitybtn;
    private Button angiogram1, angiogram1activity, addangiogram2, angiogram2activity, addecho, echo;

    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nav = findViewById(R.id.nav);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.explore:
                        startActivity(new Intent(getApplicationContext(), UpdateActivity.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        return true;
                    case R.id.settings:
//                        startActivity(new Intent(getApplicationContext(), Profile.class));
//                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        return true;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), PatientsList.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        return true;
                }
                return false;
            }
        });

        Button signOutBtn = (Button) findViewById(R.id.signOut);
        addpatient = findViewById(R.id.addpatients);
        patients = findViewById(R.id.patients);
        home = findViewById(R.id.home);
        addnote = findViewById(R.id.addnote);
        specialremarks = findViewById(R.id.specialremarks);
        vsctbtn = findViewById(R.id.vsctbtn);
        vsctactivitybtn = findViewById(R.id.vsctactivitybtn);
        angiogram1 = findViewById(R.id.angiogram1);
        angiogram1activity = findViewById(R.id.angiogram1activity);
        addangiogram2 = findViewById(R.id.addangiogram2);
        angiogram2activity = findViewById(R.id.angiogram2activity);
        addecho = findViewById(R.id.addecho);
        echo = findViewById(R.id.echo);

        echo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), EchoList.class);
                startActivity(in);
            }
        });

        addecho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), AddEcho.class);
                startActivity(in);
            }
        });

        addangiogram2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), AddAngiogram2.class);
                startActivity(in);
            }
        });

        angiogram2activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), ListAngiogram2.class);
                startActivity(in);
            }
        });

        angiogram1activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), Angiogram1List.class);
                startActivity(in);
            }
        });

        angiogram1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), AddAgiogram1.class);
                startActivity(in);
            }
        });

        vsctactivitybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), VSCTRemarks.class);
                startActivity(in);
            }
        });

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), LoginUserActivity.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(in);
                finish();

            }

        });

        addpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), AddPatients.class);
                startActivity(in);
            }
        });

        patients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), PatientsList.class);
                startActivity(in);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), Home.class);
                startActivity(in);
            }
        });

        addnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), AddSpecialRemarks.class);
                startActivity(in);
            }
        });

        specialremarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), SpecialRemarksNotesRV.class);
                startActivity(in);
            }
        });

        vsctbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), AddVSCTRemarks.class);
                startActivity(in);
            }
        });
    }
}
package com.example.clinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.clinicapp.Model.Patients;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference reference;

    private EditText userName, Name, age, nic, address, telno, diagnosis;
    private Button registerBtn;
    ImageButton imagingbtn, specialremarksbtn, vsctnotesbtn, specialremarks, vsctnotes, addangiogram, addangiogram2, addecho;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String Date;

    BottomNavigationView nav;

    Patients patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

//        userName = findViewById(R.id.userId);
//        Name = findViewById(R.id.Name);
//        age = findViewById(R.id.age);
//        nic = findViewById(R.id.nic);
//        address = findViewById(R.id.address);
//        telno = findViewById(R.id.telno);
//        diagnosis = findViewById(R.id.diagnosis);
        registerBtn = findViewById(R.id.registerBtn);
        imagingbtn =findViewById(R.id.imagingbtn);
        specialremarksbtn = findViewById(R.id.specialremarksbtn);
        vsctnotesbtn = findViewById(R.id.vsctnotesbtn);
        specialremarks = findViewById(R.id.specialremarks);
        vsctnotes = findViewById(R.id.vsctnotes);
        addangiogram = findViewById(R.id.addangiogram);
        addangiogram2 = findViewById(R.id.addangiogram2);
        addecho = findViewById(R.id.addecho);

        calendar= Calendar.getInstance();
        simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");

        Date=simpleDateFormat.format(calendar.getTime());

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

//        userName.setText(patient.getUserName());
//        Name.setText(patient.getName());
//        age.setText(patient.getAge());
//        nic.setText(patient.getNic());
//        address.setText(patient.getAddress());
//        telno.setText(patient.getTelno());
//        diagnosis.setText(patient.getDiagnosis());


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), Home.class);
                startActivity(in);

            }
        });

        imagingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), CheckAngiograms.class);
                startActivity(in);
            }
        });

        specialremarksbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), SpecialRemarksNotesRV.class);
                startActivity(in);
            }
        });

        vsctnotesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), VSCTRemarks.class);
                startActivity(in);
            }
        });

        specialremarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), AddSpecialRemarks.class);
                startActivity(in);
            }
        });

        vsctnotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), AddVSCTRemarks.class);
                startActivity(in);
            }
        });

        addangiogram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), AddAgiogram1.class);
                startActivity(in);
            }
        });

        addangiogram2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), AddAgiogram1.class);
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
    }
}
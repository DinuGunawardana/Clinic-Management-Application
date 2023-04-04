package com.example.clinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinicapp.Model.Patients;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddPatients extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference reference;

    private EditText userName, Name, age, nic, address, telno, diagnosis;
    private Button registerBtn, addpatient;
    private RadioButton male;
    private RadioButton female;
    private RadioButton other;
    private String gender = "";
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String Date;
    BottomNavigationView nav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patients);

        userName = findViewById(R.id.userId);
        Name = findViewById(R.id.Name);
        age = findViewById(R.id.age);
        nic = findViewById(R.id.nic);
        address = findViewById(R.id.address);
        telno = findViewById(R.id.telno);
        diagnosis = findViewById(R.id.diagnosis);
        male = (RadioButton) findViewById(R.id.radio_male);
        female = (RadioButton) findViewById(R.id.radio_female);
        other = (RadioButton) findViewById(R.id.radio_other);
        registerBtn = findViewById(R.id.registerBtn);

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
                        startActivity(new Intent(getApplicationContext(), UpdateActivity.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName1 = userName.getText().toString();
                String Name1 = Name.getText().toString();
                String age1 = age.getText().toString();
                String nic1 = nic.getText().toString();
                String address1 = address.getText().toString();
                String telno1 = telno.getText().toString();
                String diagnosis1 = diagnosis.getText().toString();
                String Date1 = Date.toString();

                // Assigning male to gender variable if male radio button is checked
                if (male.isChecked()) {
                    gender = "Male";
                }

                // Assigning female to gender variable if female radio button is checked
                if (female.isChecked()) {
                    gender = "Female";
                }

                // Assigning other to gender variable if other radio button is checked
                if (other.isChecked()) {
                    gender = "Other";
                }


                if (!userName1.isEmpty() && !Name1.isEmpty() && !age1.isEmpty() && !gender.isEmpty() && !nic1.isEmpty() && !address1.isEmpty() && !telno1.isEmpty() && !diagnosis1.isEmpty()){

                    Patients patients = new Patients(userName1,Name1,age1,gender,nic1,address1,telno1,diagnosis1,Date1);
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Patients");
                    reference.child(userName1).setValue(patients).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            userName.setText("");
                            Name.setText("");
                            age.setText("");
                            nic.setText("");
                            address.setText("");
                            telno.setText("");
                            diagnosis.setText("");
                            Toast.makeText(AddPatients.this,"Successfuly Updated",Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }
        });

    }
}
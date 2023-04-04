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
import android.widget.Toast;

import com.example.clinicapp.Model.Note;
import com.example.clinicapp.Model.Patients;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.Timestamp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddSpecialRemarks extends AppCompatActivity {

    EditText titleEditText, contentEditText, patientId;
    Button submit;
    Note note;
    FirebaseDatabase db;
    DatabaseReference reference;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String Date;

    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_special_remarks);

        titleEditText = findViewById(R.id.notes_title_text);
        contentEditText = findViewById(R.id.notes_content_text);
        patientId = findViewById(R.id.patientid);
        submit = findViewById(R.id.submit);

        calendar= Calendar.getInstance();
        simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteTitle = titleEditText.getText().toString();
                String noteContent = contentEditText.getText().toString();
                String patientId1 = patientId.getText().toString();
                String Date1 = Date.toString();
                if (!noteTitle.isEmpty() && !noteContent.isEmpty() && !patientId1.isEmpty() && !Date1.isEmpty()){
                    Note note = new Note(noteTitle, noteContent, patientId1, Date1);
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Notes");
                    reference.push().setValue(note).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            titleEditText.setText("");
                            contentEditText.setText("");
                            patientId.setText("");
                            Toast.makeText(AddSpecialRemarks.this, "Successfuly Updated", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }
}

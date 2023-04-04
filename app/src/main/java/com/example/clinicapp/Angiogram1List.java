package com.example.clinicapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.clinicapp.Adapters.Angiogram1Adapter;
import com.example.clinicapp.Model.Angiogram1Model;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Angiogram1List extends AppCompatActivity {

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    RecyclerView recyclerView;
    Angiogram1Adapter angiogram1Adpter;
    Angiogram1Adapter angiogram1Adapter;
    List<Angiogram1Model> angiogram1List1;

    SearchView searchView;
    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_angiogram1_list);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Angiogram1");
        mStorage = FirebaseStorage.getInstance();
        recyclerView = findViewById(R.id.userList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        angiogram1List1 = new ArrayList<Angiogram1Model>();
        angiogram1Adpter = new Angiogram1Adapter(Angiogram1List.this, angiogram1List1);
        recyclerView.setAdapter(angiogram1Adpter);

        searchView = findViewById(R.id.searchView);

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

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Angiogram1Model angiogram1 = snapshot.getValue(Angiogram1Model.class);
                angiogram1List1.add(angiogram1);
                angiogram1Adpter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        if (searchView != null)
        {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }

        }

    private void search(String str)
    {
        ArrayList<Angiogram1Model> myList = new ArrayList<>();
        for (Angiogram1Model object : angiogram1List1)
        {
            if (object.getUserId().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(object);
            }
        }
        angiogram1Adapter = new Angiogram1Adapter(this, myList);
        recyclerView.setAdapter(angiogram1Adapter);
    }
}
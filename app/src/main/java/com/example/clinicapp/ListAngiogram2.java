package com.example.clinicapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.clinicapp.Adapters.AdapterAngiogram2;
import com.example.clinicapp.Model.ModelAngiogram2;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class ListAngiogram2 extends AppCompatActivity {

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    RecyclerView recyclerView1;
    AdapterAngiogram2 adapterAngiogram2;
    AdapterAngiogram2 adapter1Angiogram2;
    List<ModelAngiogram2> modelAngiogram2List;

    SearchView searchView;
    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_angiogram2);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Angiogram2");
        mStorage = FirebaseStorage.getInstance();

        recyclerView1.findViewById(R.id.userList3);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        modelAngiogram2List = new ArrayList<ModelAngiogram2>();
        adapterAngiogram2 = new AdapterAngiogram2(ListAngiogram2.this, modelAngiogram2List);
        recyclerView1.setAdapter(adapterAngiogram2);

        searchView = findViewById(R.id.searchView3);

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
                ModelAngiogram2 modelAngiogram2 = snapshot.getValue(ModelAngiogram2.class);
                modelAngiogram2List.add(modelAngiogram2);
                adapterAngiogram2.notifyDataSetChanged();
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
        ArrayList<ModelAngiogram2> myList = new ArrayList<>();
        for (ModelAngiogram2 object : modelAngiogram2List)
        {
            if (object.getUserId().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(object);
            }
        }
        adapter1Angiogram2 = new AdapterAngiogram2(this, myList);
        recyclerView1.setAdapter(adapter1Angiogram2);
    }
}
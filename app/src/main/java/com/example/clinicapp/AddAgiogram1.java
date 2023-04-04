package com.example.clinicapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddAgiogram1 extends AppCompatActivity {

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;

    FirebaseStorage mStorage;
    ImageButton imageButton;
    EditText edtuserid, edtangiogram1;
    Button btninsert, btnactivity;
    private static final int Gallery_Code=1;
    Uri imageUrl = null;
    ProgressDialog progressDialog;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String Date;

    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_agiogram1);

        imageButton = findViewById(R.id.imageButton);
        edtuserid = findViewById(R.id.editUserId);
        edtangiogram1 = findViewById(R.id.editAngiogram1);
        btninsert = findViewById(R.id.btninsert);
        btnactivity = findViewById(R.id.btnActivity);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Angiogram1");
        mStorage = FirebaseStorage.getInstance();
        progressDialog = new ProgressDialog(this);

        calendar= Calendar.getInstance();
        simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");

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

        Date=simpleDateFormat.format(calendar.getTime());

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, Gallery_Code);
            }
        });

        btnactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), Angiogram1List.class);
                startActivity(in);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Gallery_Code && resultCode==RESULT_OK){
            imageUrl=data.getData();
            imageButton.setImageURI(imageUrl);
        }

        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userId = edtuserid.getText().toString().trim();
                final String angiogram1 = edtangiogram1.getText().toString().trim();
                final String Date1 = Date.toString();

                if (!(userId.isEmpty() && angiogram1.isEmpty() && imageUrl!=null)){
                    progressDialog.setTitle("Uploading...");
                    progressDialog.show();

                    StorageReference filepath = mStorage.getReference().child(userId).child("Angiogram1").child(imageUrl.getLastPathSegment());
                    filepath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String t = task.getResult().toString();

                                    DatabaseReference newPost = mRef.push();
                                    newPost.child("userId").setValue(userId);
                                    newPost.child("angiogram1").setValue(angiogram1);
                                    newPost.child("angiogram1Image").setValue(task.getResult().toString());
                                    newPost.child("date").setValue(Date1);
                                    progressDialog.dismiss();
                                }
                            });
                        }
                    });
                }
            }
        });
    }


}
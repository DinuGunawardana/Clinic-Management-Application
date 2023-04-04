package com.example.clinicapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class SearchUserRecyclerView extends AppCompatActivity {

    private EditText name , email, phoneNumber;
    private TextView userName;
    ImageView profileImage;
    Button editImage;

    private Button btnChangePw;

    private FirebaseAuth mAuth;
    FirebaseFirestore firebaseFirestore;
    StorageReference storageReference;

    FirebaseUser user;
    DatabaseReference myRef;
    private ProgressBar progressBar;

    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user_recycler_view);

        Button signOutBtn = (Button) findViewById(R.id.signOut);

        TextView edChangePassword = (TextView) findViewById(R.id.changePassword);
        Button saveBtn = (Button) findViewById(R.id.profileSaveBtn);

        name = findViewById(R.id.edName);
        email = findViewById(R.id.edEmail);
        phoneNumber = findViewById(R.id.edPhoneNo);

        profileImage = findViewById(R.id.profileImage);
        editImage = findViewById(R.id.editImage);

        storageReference = FirebaseStorage.getInstance().getReference();

        nav = findViewById(R.id.nav);

        nav.setSelectedItemId(R.id.settings);

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
//                        startActivity(new Intent(getApplicationContext(), SearchUserRecyclerView.class));
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

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userid = user.getUid();
        StorageReference profileRef = storageReference.child("users/"+userid+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileImage);
            }
        });

        getData();


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });

        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open gallery
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent,1000);
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


    }

    private void update() {
        String userName = name.getText().toString();
        String emailAddress = email.getText().toString();
        String occupation = phoneNumber.getText().toString();
//        Log.d("TAG", "onDataChange: check-------------name-------" + userName);

        if (userName.isEmpty()) {
            userName = name.getHint().toString();

        }
        if (emailAddress.isEmpty()) {
            emailAddress = email.getHint().toString();

        }

        if (occupation.isEmpty()) {
            occupation = phoneNumber.getHint().toString();

        }

        HashMap<String, Object> User1 = new HashMap();
        User1.put("fullName", userName);
        User1.put("email", emailAddress);
        User1.put("occupation", occupation);

        // get current user
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userid = user.getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.child(userid).updateChildren(User1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SearchUserRecyclerView.this, "User Details Updated Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SearchUserRecyclerView.this, "Failed Updating User Details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void getData() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userid = user.getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                String name = snapshot.child("fullName").getValue(String.class);
//                String age = snapshot.child("age").getValue(String.class);
                String phone = snapshot.child("occupation").getValue(String.class);
                String email = snapshot.child("email").getValue(String.class);
//                String gender = snapshot.child("gender").getValue(String.class);


//                final TextView displaynametextview = (TextView) findViewById(R.id.userName);
                final EditText fullNameTextView = (EditText) findViewById(R.id.edName);
                final EditText emailTextView =(EditText) findViewById(R.id.edEmail);
                final EditText phoneNumberTextView =(EditText) findViewById(R.id.edPhoneNo);

//                displaynametextview.setText(name);
                fullNameTextView.setText(name);
                emailTextView.setText(email);
                phoneNumberTextView.setText(phone);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                Uri imageUri = data.getData();
                Log.d("Uri", String.valueOf(imageUri));
                //profileImage.setImageURI(imageUri);

                uploadImageToFirebase(imageUri);
            }

        }

    }

    private void uploadImageToFirebase(Uri imageUri) {
        //upload image to firebase storage
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userid = user.getUid();
        StorageReference fileRef = storageReference.child("users/"+userid+"/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profileImage);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SearchUserRecyclerView.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
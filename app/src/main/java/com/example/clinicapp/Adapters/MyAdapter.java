package com.example.clinicapp.Adapters;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clinicapp.Model.Patients;
import com.example.clinicapp.R;
import com.example.clinicapp.UpdateActivity;
import com.example.clinicapp.VSCTRemarks;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {

    Context context;

    ArrayList<Patients> list;

    FirebaseDatabase db;
    DatabaseReference reference;

    public MyAdapter(Context context, ArrayList<Patients> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Patients patients = list.get(position);
        holder.userName.setText(patients.getUserName());
        holder.Name.setText(patients.getName());
        holder.age.setText(patients.getAge());
        holder.gender.setText(patients.getGender());
        holder.nic.setText(patients.getNic());
        holder.address.setText(patients.getAddress());
        holder.telno.setText(patients.getTelno());
        holder.diagnosis.setText(patients.getDiagnosis());
        holder.date.setText(patients.getDate());

        holder.editicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UpdateActivity.class);
                context.startActivity(intent);
            }
        });

        holder.deleteicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = FirebaseDatabase.getInstance();
                reference = db.getReference("Patients");
                reference.child(patients.getUserName()).removeValue();

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView userName, Name, age, gender, nic, address, telno, diagnosis, date;

        Button editicon, deleteicon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.userName);
            Name = itemView.findViewById(R.id.Name);
            age = itemView.findViewById(R.id.age);
            gender = itemView.findViewById(R.id.gender);
            nic = itemView.findViewById(R.id.nic);
            address = itemView.findViewById(R.id.address);
            telno = itemView.findViewById(R.id.telno);
            diagnosis = itemView.findViewById(R.id.diagnosis);
            date = itemView.findViewById(R.id.date);

            editicon = itemView.findViewById(R.id.editicon);
            deleteicon = itemView.findViewById(R.id.deleteicon);

        }
    }

}

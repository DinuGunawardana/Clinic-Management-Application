package com.example.clinicapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clinicapp.Model.CVNotes;
import com.example.clinicapp.Model.Note;
import com.example.clinicapp.Model.Patients;
import com.example.clinicapp.R;

import java.util.ArrayList;
import java.util.List;

public class CVNoteAdapter extends RecyclerView.Adapter<CVNoteAdapter.MyViewHolder>{

    Context context;
    List<CVNotes> noteArrayList;

    public CVNoteAdapter(Context context, List<CVNotes> noteArrayList) {
        this.context = context;
        this.noteArrayList = noteArrayList;
    }

    @NonNull
    @Override
    public CVNoteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.vsctitem,parent,false);
        return new CVNoteAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CVNoteAdapter.MyViewHolder holder, int position) {
        CVNotes cvNotes = noteArrayList.get(position);

        holder.patientId.setText(cvNotes.getPatientid());
        holder.notescontent.setText(cvNotes.getNotes_content_text());
        holder.datebtn.setText(cvNotes.getDatebtn());
        holder.date.setText(cvNotes.getDate());
    }

    @Override
    public int getItemCount() {
        return noteArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView patientId, notescontent, datebtn, date;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            patientId = itemView.findViewById(R.id.userId);
            notescontent = itemView.findViewById(R.id.title);
            datebtn = itemView.findViewById(R.id.content);
            date  = itemView.findViewById(R.id.date);
        }
    }
}

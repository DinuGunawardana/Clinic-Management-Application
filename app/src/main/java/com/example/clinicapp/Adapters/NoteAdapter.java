package com.example.clinicapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clinicapp.Model.Note;
import com.example.clinicapp.R;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    Context context;
    List<Note> noteArrayList;

    public NoteAdapter(Context context, List<Note> noteArrayList) {
        this.context = context;
        this.noteArrayList = noteArrayList;
    }

    @NonNull
    @Override
    public NoteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.remarkslist,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.MyViewHolder holder, int position) {

        Note note = noteArrayList.get(position);

        holder.title.setText(note.getTitle());
        holder.content.setText(note.getContent());
        holder.userId.setText(note.getId());
        holder.date.setText(note.getDate());

    }

    @Override
    public int getItemCount() {
        return noteArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView date, userId, title, content;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            date  = itemView.findViewById(R.id.date);
            userId = itemView.findViewById(R.id.userId);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
        }
    }
}

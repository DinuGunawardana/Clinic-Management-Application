package com.example.clinicapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clinicapp.Model.Angiogram1Model;
import com.example.clinicapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Angiogram1Adapter extends RecyclerView.Adapter<Angiogram1Adapter.ViewHolder>{

    Context context;
    List<Angiogram1Model> angiogram1List;

    FirebaseDatabase db;
    DatabaseReference reference;

    public Angiogram1Adapter(Context context, List<Angiogram1Model> angiogram1List) {
        this.context = context;
        this.angiogram1List = angiogram1List;
    }

    @NonNull
    @Override
    public Angiogram1Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.angiogram1item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Angiogram1Adapter.ViewHolder holder, int position) {
        Angiogram1Model angiogram1 = angiogram1List.get(position);

        holder.tvuserId.setText(angiogram1.getUserId());
        holder.tvangiogram1.setText(angiogram1.getAngiogram1());
        holder.date.setText(angiogram1.getDate());

        String angiogram1Image = null;
        angiogram1Image = angiogram1.getAngiogram1Image();
        Picasso.get().load(angiogram1Image).into(holder.imageView);

//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "Angiogram1", Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(context, ImageDiscriptionsActivity.class);
//                intent.putExtra("angiogram1Image", angiogram1.getAngiogram1Image());
//                intent.putExtra("angiogram1", angiogram1.getAngiogram1());
//                intent.putExtra("userId", angiogram1.getUserId());
//
//                context.startActivity(intent);
//            }
//        });

//        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                reference = FirebaseDatabase.getInstance().getReference("Angiogram1");
//                reference.child(angiogram1.getUserId()).removeValue()
//                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return angiogram1List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvuserId, tvangiogram1, date;
        Button deletebtn;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_recyclerview_id);
            tvuserId = itemView.findViewById(R.id.tvuserid);
            tvangiogram1 = itemView.findViewById(R.id.tvangiogram1);
            date = itemView.findViewById(R.id.tvdate);
            cardView = itemView.findViewById(R.id.cardView);
//            deletebtn = itemView.findViewById(R.id.deletebtn);
        }
    }
}

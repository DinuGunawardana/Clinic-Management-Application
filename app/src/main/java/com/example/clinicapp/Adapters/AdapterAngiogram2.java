package com.example.clinicapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clinicapp.Model.ModelAngiogram2;
import com.example.clinicapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterAngiogram2 extends RecyclerView.Adapter<AdapterAngiogram2.ViewHolder>{

    Context context;
    List<ModelAngiogram2> modelAngiogram2List;

    public AdapterAngiogram2(Context context, List<ModelAngiogram2> modelAngiogram2List) {
        this.context = context;
        this.modelAngiogram2List = modelAngiogram2List;
    }

    @NonNull
    @Override
    public AdapterAngiogram2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemangiogram2, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAngiogram2.ViewHolder holder, int position) {
        ModelAngiogram2 modelAngiogram2 = modelAngiogram2List.get(position);

        holder.tvuserId.setText(modelAngiogram2.getUserId());
        holder.tvangiogram2.setText(modelAngiogram2.getAngiogram2());
        holder.date.setText(modelAngiogram2.getDate());

        String angiogram2Image = null;
        angiogram2Image = modelAngiogram2.getAngiogram2Image();
        Picasso.get().load(angiogram2Image).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return modelAngiogram2List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvuserId, tvangiogram2, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_recyclerview_id1);
            tvuserId = itemView.findViewById(R.id.tvuserid2);
            tvangiogram2 = itemView.findViewById(R.id.angiogram2);
            date = itemView.findViewById(R.id.date2);
        }
    }
}

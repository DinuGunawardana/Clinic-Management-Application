package com.example.clinicapp.Adapters;

import android.content.Context;
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
import com.example.clinicapp.Model.EchoModel;
import com.example.clinicapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EchoAdapter extends RecyclerView.Adapter<EchoAdapter.ViewHolder>{

    Context context;
    List<EchoModel> echoModels;

    public EchoAdapter(Context context, List<EchoModel> echoModels) {
        this.context = context;
        this.echoModels = echoModels;
    }

    @NonNull
    @Override
    public EchoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.echoitem, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EchoModel echoModel = echoModels.get(position);

        holder.tvuserId1.setText(echoModel.getUserId());
        holder.tvecho1.setText(echoModel.getEcho());
        holder.tvdate1.setText(echoModel.getDate());

        String echoImage = null;
        echoImage = echoModel.getEchoImage();
        Picasso.get().load(echoImage).into(holder.imageView);

//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "EchoAdapter", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return echoModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvuserId1, tvecho1, tvdate1;
        Button deletebtn;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.echo_recyclerview_id);
            tvuserId1 = itemView.findViewById(R.id.tvuserid1);
            tvecho1 = itemView.findViewById(R.id.tvecho1);
            tvdate1 = itemView.findViewById(R.id.tvdate1);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}

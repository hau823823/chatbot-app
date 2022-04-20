package com.example.apptest2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.List;

public class TripediteAdapter extends RecyclerView.Adapter<TripediteAdapter.TripeditViewHolder> {

    private Context mCtx;
    private List<Tripedititem> productList;

    public TripediteAdapter(Context mCtx, List<Tripedititem> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public TripeditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tripedit,parent,false);
        return new TripeditViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripeditViewHolder holder, int position) {
        Tripedititem Tripedititem = productList.get(position);


        holder.textViewsId.setText(String.valueOf(Tripedititem.getsId()));
        holder.textViewplace.setText(String.valueOf(Tripedititem.getplace()));
    }



    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class TripeditViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewsId, textViewplace;

        public TripeditViewHolder(View itemView) {
            super(itemView);

            textViewsId = itemView.findViewById(R.id.tripedit_date);
            textViewplace = itemView.findViewById(R.id.tripedit_place);
        }
    }
}
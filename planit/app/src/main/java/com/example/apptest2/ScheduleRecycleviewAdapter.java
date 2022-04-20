package com.example.apptest2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;



public class ScheduleRecycleviewAdapter extends RecyclerView.Adapter<ScheduleRecycleviewAdapter.SearchViewHolder>{


    private Context mCtx;
    private List<Scheduleitem> productList;
    private String uId;

    public ScheduleRecycleviewAdapter(Context mCtx, List<Scheduleitem> productList, String uId) {
        this.mCtx = mCtx;
        this.productList = productList;
        this.uId = uId;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule,parent,false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Scheduleitem Scheduleitem = productList.get(position);
        //loading the image
        holder.schname.setText(String.valueOf(Scheduleitem.getschname()));

        holder.schname.setOnClickListener(view -> {
            Intent intent = new Intent(mCtx,Tripedit.class);
            Bundle bundle = new Bundle();
            bundle.putString("uId",uId);
            bundle.putString("schname",String.valueOf(Scheduleitem.getschname()));
            intent.putExtras(bundle);
            mCtx.startActivity(intent);
        });
    }



    @Override
    public int getItemCount() {
        return productList.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {

        Button schname;
        public SearchViewHolder(View itemView){
            super(itemView);
            schname = itemView.findViewById(R.id.schname);
        }
    }
}
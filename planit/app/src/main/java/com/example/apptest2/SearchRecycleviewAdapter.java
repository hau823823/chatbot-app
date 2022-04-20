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

public class SearchRecycleviewAdapter extends RecyclerView.Adapter<SearchRecycleviewAdapter.SearchViewHolder> {


    private Context mCtx;
    private List<Searchitem> productList;
    private  String uId;

    public SearchRecycleviewAdapter(Context mCtx, List<Searchitem> productList, String uId) {
        this.mCtx = mCtx;
        this.productList = productList;
        this.uId = uId;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search,parent,false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Searchitem Searchitem = productList.get(position);

        //loading the image
        Glide.with(mCtx)
                .load(Searchitem.getpIMG())
                .into(holder.imageView);

        holder.textViewName.setText(String.valueOf(Searchitem.getpName()));
        holder.textViewIntroduce.setText(String.valueOf(Searchitem.getpIntroduce()));
        holder.textViewTel.setText(String.valueOf(Searchitem.getpTel()));
        holder.textViewAddress.setText(String.valueOf(Searchitem.getpAddress()));
        holder.textViewTime.setText(String.valueOf(Searchitem.getpTime()));

        holder.add.setOnClickListener(view -> {
            Toast.makeText(mCtx, "已將景點加入收藏", Toast.LENGTH_SHORT).show();

            //add
            String[] field = new String[2];
            field[0] = "uId";
            field[1] = "pId";

            String[] data = new String[2];
            data[0] = String.valueOf(uId);
            data[1] = String.valueOf(Searchitem.getpId());

            PutData putData = new PutData("http://140.117.71.149/favorite/writein_favorite.php/?", "POST", field, data);
            putData.startPut();
            putData.onComplete();
        });
    }



    @Override
    public int getItemCount() {
        return productList.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewIntroduce, textViewTel, textViewAddress, textViewTime;
        ImageView imageView,add;

        public SearchViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.search_name);
            textViewIntroduce = itemView.findViewById(R.id.search_introduce);
            textViewTel = itemView.findViewById(R.id.search_phone);
            textViewAddress = itemView.findViewById(R.id.search_address);
            textViewTime = itemView.findViewById(R.id.search_time);
            imageView = itemView.findViewById(R.id.search_img);
            add = itemView.findViewById(R.id.search_add);
        }
    }
}
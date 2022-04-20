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

public class FavoriteRecyclerviewAdapter extends RecyclerView.Adapter<FavoriteRecyclerviewAdapter.FavoriteViewHolder> {

    private Context mCtx;
    private List<Favoriteitem> productList;

    public FavoriteRecyclerviewAdapter(Context mCtx, List<Favoriteitem> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite,parent,false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        Favoriteitem Favoriteitem = productList.get(position);

        //loading the image
        Glide.with(mCtx)
                .load(Favoriteitem.getpIMG())
                .into(holder.imageView);

        holder.textViewName.setText(String.valueOf(Favoriteitem.getpName()));
        holder.textViewIntroduce.setText(String.valueOf(Favoriteitem.getpIntroduce()));
        holder.textViewTel.setText(String.valueOf(Favoriteitem.getpTel()));
        holder.textViewAddress.setText(String.valueOf(Favoriteitem.getpAddress()));
        holder.textViewTime.setText(String.valueOf(Favoriteitem.getpTime()));

        holder.delete.setOnClickListener(view -> {
            Toast.makeText(mCtx, "已將景點刪除", Toast.LENGTH_SHORT).show();

            //Delete
            String[] field = new String[2];
            field[0] = "uId";
            field[1] = "pId";

            String[] data = new String[2];
            data[0] = String.valueOf(Favoriteitem.getuId());
            data[1] = String.valueOf(Favoriteitem.getpId());

            PutData putData = new PutData("http://140.117.71.149/favorite/delete_favorite.php/?", "POST", field, data);
            putData.startPut();
            putData.onComplete();
        });
    }



    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName, textViewIntroduce, textViewTel, textViewAddress, textViewTime;
        private ImageView imageView,delete;

        public FavoriteViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.favorite_name);
            textViewIntroduce = itemView.findViewById(R.id.favorite_introduce);
            textViewTel = itemView.findViewById(R.id.favorite_phone);
            textViewAddress = itemView.findViewById(R.id.favorite_address);
            textViewTime = itemView.findViewById(R.id.favorite_time);
            imageView = itemView.findViewById(R.id.favorite_img);
            delete = itemView.findViewById(R.id.favorite_delete);
        }
    }
}
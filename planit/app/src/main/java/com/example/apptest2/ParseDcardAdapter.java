package com.example.apptest2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ParseDcardAdapter extends RecyclerView.Adapter<ParseDcardAdapter.ViewHolder> {

    private ArrayList<ParseItem> parseItems;
    private Context context;

    public ParseDcardAdapter(ArrayList<ParseItem> parseItems,Context context){
        this.parseItems = parseItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ParseDcardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    // 將list的每筆資料帶入cell item
    public void onBindViewHolder(@NonNull ParseDcardAdapter.ViewHolder holder, int position) {
        ParseItem parseItem = parseItems.get(position);
        holder.textView1.setText(parseItem.getInfobar());
        holder.textView2.setText(parseItem.getTitle());
        Picasso.get().load(parseItem.getImgUrl()).into(holder.imageView);
    }

    @Override
    // recyclerView會依據getItemCount決定顯示幾筆資料
    public int getItemCount() {
        return parseItems.size();
    }
    //在Viewholder內定義每個list cell上會有的view components
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView1;
        TextView textView2;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            textView1 = itemView.findViewById(R.id.infobar);
            textView2 = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.img);
            itemView.setOnClickListener(this);
            cardView = itemView.findViewById(R.id.cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();
                    ParseItem parseItem = parseItems.get(position);

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.dcard.tw"+parseItem.getDetailUrl()));
                    context.startActivity(intent);
                }
            });
        }

        public void onClick(View view) {

        }
    }
}

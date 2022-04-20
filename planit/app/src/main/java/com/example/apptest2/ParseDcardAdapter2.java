package com.example.apptest2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

public class ParseDcardAdapter2 extends RecyclerView.Adapter<ParseDcardAdapter2.ViewHolder2> {

    private ArrayList<ParseItem> parseItems2;
    private Context context2;

    public ParseDcardAdapter2(ArrayList<ParseItem> parseItems2,Context context2){
        this.parseItems2 = parseItems2;
        this.context2 = context2;
    }

    @NonNull
    @Override
    public ParseDcardAdapter2.ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item,parent,false);
        return new ViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParseDcardAdapter2.ViewHolder2 holder2, int position) {
        ParseItem parseItem2 = parseItems2.get(position);
        holder2.textView2.setText(parseItem2.getTitle());
        Picasso.get().load(parseItem2.getImgUrl()).into(holder2.imageView2);
    }


    @Override
    public int getItemCount() {
        return parseItems2.size();
    }
    //在Adapter裡建立一個ViewHolder class，其功能十分簡單，只需要在constructor裡存儲起自己用到的View即可。
    public class ViewHolder2 extends RecyclerView.ViewHolder{

        TextView textView2;
        ImageView imageView2;
        CardView cardView2;

        public ViewHolder2(@NonNull View itemView2){
            super(itemView2);
            textView2 = itemView2.findViewById(R.id.title);
            imageView2 = itemView2.findViewById(R.id.img);
            cardView2 = itemView2.findViewById(R.id.cardView);

            cardView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();
                    ParseItem parseItem2 = parseItems2.get(position);

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.dcard.tw"+parseItem2.getDetailUrl()));
                    context2.startActivity(intent);
                }
            });
        }

        public void onClick(View view) {

        }
    }
}

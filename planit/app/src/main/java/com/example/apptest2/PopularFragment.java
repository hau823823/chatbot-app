package com.example.apptest2;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class PopularFragment extends Fragment {

    private RecyclerView recyclerView2;
    private ParseDcardAdapter2 adapter;
    private ArrayList<ParseItem> parseItems = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, container, false);
        recyclerView2 = view.findViewById(R.id.recyclerView2);

        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ParseDcardAdapter2(parseItems,getActivity());
        recyclerView2.setAdapter(adapter);


        Content content = new Content();
        content.execute();

        return view;
    }

    private class Content extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
//            progressBar.setVisibility(View.VISIBLE);
//            progressBar.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.fade_in));
        }

        @Override
        //執行完的結果
        protected void onPostExecute(Void avoid){
            super.onPostExecute(avoid);
//            progressBar.setVisibility(View.GONE);
//            progressBar.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.fade_out));
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled(){
            super.onCancelled();
        }

        @Override
        //實際要執行的程式碼
        protected Void doInBackground(Void... voids) {
            try {
                String url = "https://www.dcard.tw/topics/%E5%8F%B0%E5%8C%97%E7%BE%8E%E9%A3%9F";
                Document doc = Jsoup.connect(url).get();
                Elements data = doc.select("article.tgn9uw-0");

                int size = data.size();

                Log.d("size",""+size);

                for(int i = 0; i< size;i++){

                    String infobar = data.select("div.euk31c-2")
                            .eq(i)
                            .text();

                    String title = data.select("h2")
                            .eq(i)
                            .text();
                    Log.d("title:", "title"+title);

                    String imgUrl = data.select("img")
                            .eq(i)
                            .attr("src");
                    Log.d("imgurl:", "imgurl"+imgUrl);

                    String detailUrl= data.select("h2")
                            .select("a")
                            .eq(i)
                            .attr("href");

                    parseItems.add(new ParseItem(infobar,title,imgUrl,detailUrl));
                    Log.d("items","img:"+imgUrl+".title:"+title);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
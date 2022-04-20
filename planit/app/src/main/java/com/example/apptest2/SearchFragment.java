package com.example.apptest2;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class SearchFragment extends Fragment{

    View v;

    //從mianactivity傳值過來
    private static final String uId = "0";
    public SearchFragment() {
        // Requires empty public constructor
    }
    public static Fragment newInstance(String uid){
        SearchFragment fragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putString( uId, uid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        v = inflater.inflate(R.layout.fragment_search, container, false);

        CardView view_restaurant = v.findViewById(R.id.View_restaurant);
        CardView view_sec = v.findViewById(R.id.View_sec);
        CardView view_coffee = v.findViewById(R.id.View_coffee);
        CardView view_museum = v.findViewById(R.id.View_museum);
        CardView view_desert = v.findViewById(R.id.View_desert);
        CardView view_landmark = v.findViewById(R.id.View_landmark);

        view_restaurant.setOnClickListener(v ->{
            Intent intent = new Intent(getActivity().getApplicationContext(),SearchRecycleview.class);
            Bundle bundle = new Bundle();
            bundle.putString("searchitem","res");
            bundle.putString("uId",getArguments().getString(uId));
            intent.putExtras(bundle);
            startActivity(intent);
        });

        view_sec.setOnClickListener(v ->{
            Intent intent = new Intent(getActivity().getApplicationContext(),SearchRecycleview.class);
            Bundle bundle = new Bundle();
            bundle.putString("searchitem","sce");
            bundle.putString("uId",getArguments().getString(uId));
            intent.putExtras(bundle);
            startActivity(intent);
        });

        view_desert.setOnClickListener(v ->{
            Intent intent = new Intent(getActivity().getApplicationContext(),SearchRecycleview.class);
            Bundle bundle = new Bundle();
            bundle.putString("searchitem","des");
            bundle.putString("uId",getArguments().getString(uId));
            intent.putExtras(bundle);
            startActivity(intent);
        });

        view_coffee.setOnClickListener(v ->{
            Intent intent = new Intent(getActivity().getApplicationContext(),SearchRecycleview.class);
            Bundle bundle = new Bundle();
            bundle.putString("searchitem","cof");
            bundle.putString("uId",getArguments().getString(uId));
            intent.putExtras(bundle);
            startActivity(intent);
        });

        view_museum.setOnClickListener(v ->{
            Intent intent = new Intent(getActivity().getApplicationContext(),SearchRecycleview.class);
            Bundle bundle = new Bundle();
            bundle.putString("searchitem","博物");
            bundle.putString("uId",getArguments().getString(uId));
            intent.putExtras(bundle);
            startActivity(intent);
        });

        view_landmark.setOnClickListener(v ->{
            Intent intent = new Intent(getActivity().getApplicationContext(),SearchRecycleview.class);
            Bundle bundle = new Bundle();
            bundle.putString("searchitem","landmark");
            bundle.putString("uId",getArguments().getString(uId));
            intent.putExtras(bundle);
            startActivity(intent);
        });

        //search 功能
        SearchView searchview = v.findViewById(R.id.search_box);
        searchview.setIconifiedByDefault(false);
        searchview.setSubmitButtonEnabled(true);
        searchview.setQueryHint("請輸入欲搜尋之地點名稱");
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String searchitem) {
                if (!searchitem.equals("")){

                    Intent intent = new Intent(getActivity().getApplicationContext(),SearchRecycleview.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("searchitem",searchitem);
                    bundle.putString("uId",getArguments().getString(uId));
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return v;
    }

}

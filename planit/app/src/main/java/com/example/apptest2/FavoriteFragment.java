package com.example.apptest2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavoriteFragment extends Fragment {
    View v;
    List<Favoriteitem> productList;
    RecyclerView recyclerView;

    private static final String URL_PRODUCTS = "http://140.117.71.149/favorite/read_favorite.php";

    //從mianactivity傳值過來
    private static final String uId = "0";
    public FavoriteFragment() {
        // Requires empty public constructor
    }
    public static Fragment newInstance(String uid){
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle bundle = new Bundle();
        bundle.putString( uId, uid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_favorite, container, false);

        //initializing the productlist
        productList = new ArrayList<>();
        productList.clear();


        //getting the recyclerview from xml
        recyclerView = v.findViewById(R.id.favorite_recyclerView);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FavoriteRecyclerviewAdapter adapter = new FavoriteRecyclerviewAdapter(getActivity(), productList);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PRODUCTS,
                (Response.Listener<String>) response -> {
                    try {
                        JSONArray array = new JSONArray(response);

                        for (int i = 0; i < array.length(); i++) {
                            JSONObject Favoriteitem = array.getJSONObject(i);
                            productList.add(new Favoriteitem(
                                    Favoriteitem.getString("uId"),
                                    Favoriteitem.getString("pId"),
                                    Favoriteitem.getString("pName"),
                                    Favoriteitem.getString("pIntroduce"),
                                    Favoriteitem.getString("pTel"),
                                    Favoriteitem.getString("pAddress"),
                                    Favoriteitem.getString("pTime"),
                                    Favoriteitem.getString("pIMG")
                            ));
                        }

                        recyclerView.setAdapter(adapter);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                (Response.ErrorListener) error -> {
                }) {
            //post傳值到sql
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("uId", getArguments().getString(uId));
                return params;
            }

        };
        Volley.newRequestQueue(getContext()).add(stringRequest);

        return v;
    }
}

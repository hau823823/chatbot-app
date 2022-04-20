package com.example.apptest2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchRecycleview extends AppCompatActivity {
    //this is the JSON Data URL
    //make sure you are using the correct ip else it will not work
    private static final String URL_PRODUCTS = "http://140.117.71.149/search_api.php";

    //a list to store all the products
    List<Searchitem> productList;

    //the recyclerview
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview_search);

        //獲得SearchFragment傳過來的值
        Bundle bundle = getIntent().getExtras();
        String s = bundle.getString("searchitem" );
        String uId = bundle.getString("uId" );

        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.search_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        productList = new ArrayList<>();

        SearchRecycleviewAdapter adapter = new SearchRecycleviewAdapter(SearchRecycleview.this, productList, uId);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PRODUCTS,
                (Response.Listener<String>) response -> {
                    try {
                        //converting the string to json array object
                        JSONArray array = new JSONArray(response);

                        //traversing through all the object
                        for (int i = 0; i < array.length(); i++) {

                            //getting product object from json array
                            JSONObject Searchitem = array.getJSONObject(i);

                            //adding the product to product list
                            productList.add(new Searchitem(
                                    Searchitem.getString("pId"),
                                    Searchitem.getString("pName"),
                                    Searchitem.getString("pIntroduce"),
                                    Searchitem.getString("pTel"),
                                    Searchitem.getString("pAddress"),
                                    Searchitem.getString("pTime"),
                                    Searchitem.getString("pIMG")
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
                Map<String, String>  params = new HashMap<>();
                params.put("searchitem", s);
                return params;
            }

        };
        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);

        //返回
        ImageButton imageButton_back = findViewById(R.id.imageButton_back);
        imageButton_back.setOnClickListener(v ->{
            finish();
        });

    }
}
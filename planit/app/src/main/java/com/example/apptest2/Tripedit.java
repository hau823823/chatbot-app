package com.example.apptest2;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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

public class Tripedit extends AppCompatActivity {

    List<Tripedititem> productList;

    RecyclerView recyclerView;

    private static final String URL_PRODUCTS = "http://140.117.71.149/tripdit.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tripedit);

        //獲得SearchFragment傳過來的值
        Bundle bundle = getIntent().getExtras();
        String schname = bundle.getString("schname" );
        String uId = bundle.getString("uId" );

        TextView schedule_name = findViewById(R.id.schedule_name);
        schedule_name.setText(schname);

        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.tripedit_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        productList = new ArrayList<>();

        TripediteAdapter adapter = new TripediteAdapter(Tripedit.this, productList);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PRODUCTS,
                (Response.Listener<String>) response -> {
                    try {
                        JSONArray array = new JSONArray(response);

                        for (int i = 0; i < array.length(); i++) {

                            JSONObject Tripedititem = array.getJSONObject(i);

                            productList.add(new Tripedititem(
                                    Tripedititem.getString("sId"),
                                    Tripedititem.getString("schname"),
                                    Tripedititem.getString("place")
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
                params.put("uId", uId);
                params.put("schname", schname);
                return params;
            }

        };
        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);

        //返回
        ImageButton image_return = findViewById(R.id.image_return);
        image_return.setOnClickListener(v ->{
            finish();
        });

    }
}

package com.example.apptest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    TextInputEditText editTextTextUsername, editTextTextPassword;
    Button buttonLogin;
    private static final String URL_PRODUCTS = "http://140.117.71.149/login_register/login_api.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextTextUsername = findViewById(R.id.login_username);
        editTextTextPassword = findViewById(R.id.login_password);

        buttonLogin = findViewById(R.id.button_1);
        buttonLogin.setOnClickListener(view -> {

            String username, password;
            username = String.valueOf(editTextTextUsername.getText());
            password = String.valueOf(editTextTextPassword.getText());

            if (!username.equals("") && !password.equals("")){
                //判斷是否登入
                Handler handler = new Handler();
                handler.post(() -> {

                    String[] field = new String[2];
                    field[0] = "username";
                    field[1] = "password";
                    String[] data = new String[2];
                    data[0] = username;
                    data[1] = password;
                    PutData putData = new PutData("http://140.117.71.149/login_register/login.php/?", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Login Success")){
                                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();

                                //
                                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PRODUCTS,
                                        (Response.Listener<String>) response -> {
                                            try {
                                                JSONArray array = new JSONArray(response);
                                                JSONObject Loginitem = array.getJSONObject(0);
                                                int uId = Loginitem.getInt("uId");
                                                String email = Loginitem.getString("email");

                                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                Bundle bundle = new Bundle();
                                                bundle.putString("username",username);
                                                bundle.putString("email",email);
                                                bundle.putInt("uId",uId);
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                                finish();

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        },
                                        (Response.ErrorListener) error -> {
                                        }) {
                                    @Override
                                    protected Map<String, String> getParams()
                                    {
                                        Map<String, String>  params = new HashMap<>();
                                        params.put("loginitem", username);
                                        return params;
                                    }

                                };
                                Volley.newRequestQueue(this).add(stringRequest);
                                //
                            }
                            else{
                                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
            else {
                Toast.makeText(getApplicationContext(),"All fields required", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
package com.example.apptest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class register extends AppCompatActivity {

    TextInputEditText editTextTextUsername, editTextTextPassword, editTextTextEmail;
    Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextTextUsername = findViewById(R.id.register_username);
        editTextTextPassword = findViewById(R.id.register_password);
        editTextTextEmail = findViewById(R.id.register_email);
        buttonSignUp = findViewById(R.id.button_1);

        buttonSignUp.setOnClickListener(view -> {

            String username, password, email;

            username = String.valueOf(editTextTextUsername.getText());
            password = String.valueOf(editTextTextPassword.getText());
            email = String.valueOf(editTextTextEmail.getText());

            if (!username.equals("") && !password.equals("") && !email.equals("")){

                //Start ProgressBar first (Set visibility VISIBLE)
                Handler handler = new Handler();
                handler.post(() -> {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    String[] field = new String[3];
                    field[0] = "username";
                    field[1] = "password";
                    field[2] = "email";
                    //Creating array for data
                    String[] data = new String[3];
                    data[0] = username;
                    data[1] = password;
                    data[2] = email;
                    PutData putData = new PutData("http://140.117.71.149/login_register/signup.php/?", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Sign Up Success")){
                                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), login.class);
                                startActivity(intent);
                                finish();
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
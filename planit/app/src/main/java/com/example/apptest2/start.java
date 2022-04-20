package com.example.apptest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button to_register = findViewById(R.id.button_1);
        to_register.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(start.this, register.class);
            startActivity(intent);
        });

        Button to_login = findViewById(R.id.button_2);
        to_login.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(start.this, login.class);
            startActivity(intent);
        });

    }
}
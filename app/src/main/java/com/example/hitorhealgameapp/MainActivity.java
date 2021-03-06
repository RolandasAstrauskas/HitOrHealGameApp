package com.example.hitorhealgameapp;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;



public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(() -> {
            Intent homeIntent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(homeIntent);
            finish();
        }, SPLASH_TIME_OUT);


    }
}
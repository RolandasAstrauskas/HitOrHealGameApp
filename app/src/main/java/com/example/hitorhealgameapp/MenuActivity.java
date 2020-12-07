package com.example.hitorhealgameapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;



public class MenuActivity extends AppCompatActivity {

    private Button buttonStat, buttonJoin, buttonQuit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_menu);

        createNewPlayerNow();
        seeStat();
        quit();

    }

    private void createNewPlayerNow() {
        buttonJoin = findViewById(R.id.buttonJoin);
        buttonJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, CreatePlayerActivity.class);
                startActivity(intent);
            }
        });
    }

    private void seeStat() {
        buttonStat = findViewById(R.id.buttonStat);
        buttonStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, PlayerStatActivity.class);
                startActivity(intent);

            }
        });
    }


    private void quit() {
        buttonQuit = findViewById(R.id.buttonQuit);
        buttonQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }
}
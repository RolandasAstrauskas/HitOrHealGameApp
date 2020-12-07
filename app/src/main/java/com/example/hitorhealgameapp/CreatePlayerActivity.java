package com.example.hitorhealgameapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import Database.*;
import Player.Player;


public class CreatePlayerActivity extends AppCompatActivity {

    private Button buttonCreatePlayer;
    private EditText insertName;
    private ConstraintLayout constraintLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_create_player);

        createNewPlayerNow();

    }


    private void createNewPlayerNow() {

        buttonCreatePlayer = findViewById(R.id.buttonCreatPlayer);
        buttonCreatePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertName = findViewById(R.id.insertName);

                if(insertName.getText().toString().equals("") || insertName.getText().toString() == null){
                    constraintLayout = findViewById(R.id.layout);
                    Snackbar snackbar = Snackbar.make(constraintLayout, "You must have name!", Snackbar.LENGTH_SHORT);
                    snackbar.show();

            }else{
                Database helper = new Database(CreatePlayerActivity.this);
                SQLiteDatabase database = helper.getReadableDatabase();
                DatabaseWorker worker = new DatabaseWorker(database);

                    String[] name = {insertName.getText().toString()};

                    ArrayList<Player> playerArrayList = (ArrayList<Player>) worker.checkPlayer(name);

                       if(!playerArrayList.isEmpty()){

                           constraintLayout = findViewById(R.id.layout);
                           Snackbar snackbar = Snackbar.make(constraintLayout, "You must have name", Snackbar.LENGTH_SHORT);

                           snackbar.show();

                       }else{

                          worker.createNewPlayer(null, insertName.getText().toString(), new Player(insertName.getText().toString()).getHealth(), new Player(insertName.getText().toString()).getExperience());

                           Intent intent = new Intent(CreatePlayerActivity.this, GameActivity.class);
                           intent.putExtra("playerName", insertName.getText().toString());
                           startActivity(intent);
                       }
                  }
            }
        });
    }
}
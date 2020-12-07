package com.example.hitorhealgameapp;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import Adapter.Utils;
import Database.*;
import Player.*;

public class GameActivity extends AppCompatActivity {


    private Database helper;
    private TextView randomNumber;
    private TextView health;
    private TextView experience;
    private TextView healthDarkLord;
    private static Player player;
    private static DarkLord darkLord;
    private static Random r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Objects.requireNonNull(getSupportActionBar()).hide();


        int playerId = getPlayerId();

        randomNumber();

        healButton(playerId);

        hitButton(playerId);

    }

    private int getPlayerId() {
        Bundle extras = getIntent().getExtras();
        String[] userName = {extras.getString("playerName")};

        helper = new Database(GameActivity.this);
        SQLiteDatabase database = helper.getReadableDatabase();
        DatabaseWorker worker = new DatabaseWorker(database);

        ArrayList<Player> playerArrayList = (ArrayList<Player>) worker.checkPlayer(userName);
        Player players = playerArrayList.get(0);
        int playerId = players.getPlayerID();

        darkLord = new DarkLord();

        TextView playerName = findViewById(R.id.playerName);
        playerName.setText(extras.getString("playerName"));
        TextView darkLordName = findViewById(R.id.darkLord);
        darkLordName.setText(darkLord.getName());
        healthDarkLord = findViewById(R.id.healthDarkLord);
        healthDarkLord.setText(String.valueOf(darkLord.getHealthDark()));

        player = new Player(extras.getString("playerName"));

        health = findViewById(R.id.health);
        health.setText(String.valueOf(player.getHealth()));

        experience = findViewById(R.id.experience);
        experience.setText(String.valueOf(player.getExperience()));
        return playerId;
    }

    private void healButton(int playerId) {

        Button heal = findViewById(R.id.heal);
        heal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stringPoint = randomNumber.getText().toString();
                int point = Integer.parseInt(stringPoint);

                helper = new Database(GameActivity.this);
                SQLiteDatabase database = helper.getReadableDatabase();
                DatabaseWorker worker = new DatabaseWorker(database);


                player.healPlayer(point);
                player.getExperienceInGame(point);
                String[] playerID = {toString().valueOf(playerId)};

                worker.update(player.getHealth(), player.getExperience(), playerID);

                health.setText(String.valueOf(player.getHealth()));
                experience.setText(String.valueOf(player.getExperience()));



                Utils.delay(1, new Utils.DelayCallback() {
                    @Override
                    public void afterDelay() {

                        int pointDark =  setRandomNumberDark();

                        player.damagePlayer(pointDark);

                        health.setText(String.valueOf(player.getHealth()));

                        if(player.getHealth() <= 0){
                            AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                            builder.setTitle("You lost!!!!");
                            builder.setMessage("Nooo nooo!!!!");
                            builder.show();
                            worker.delete(playerId);

                            Utils.delay(3, new Utils.DelayCallback() {
                                @Override
                                public void afterDelay() {

                                    Intent intent = new Intent(GameActivity.this, MenuActivity.class);
                                    startActivity(intent);
                                    finish();


                                }
                            });
                        }
                    }
                });
            }
        });
    }

    private void hitButton(int playerId) {


        ImageView hit = findViewById(R.id.hit);
        hit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stringPoint = randomNumber.getText().toString();
                int point = Integer.parseInt(stringPoint);

                helper = new Database(GameActivity.this);
                SQLiteDatabase database = helper.getReadableDatabase();
                DatabaseWorker worker = new DatabaseWorker(database);

                darkLord.damageDarkLord(point);
                player.getExperienceInGame(point);
                String[] playerID = {toString().valueOf(playerId)};

                worker.update(player.getHealth(), player.getExperience(), playerID);

                healthDarkLord.setText(String.valueOf(darkLord.getHealthDark()));
                health.setText(String.valueOf(player.getHealth()));
                experience.setText(String.valueOf(player.getExperience()));

                if (darkLord.getHealthDark() <= 0) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                    builder.setTitle("You WIN!!!!");
                    builder.setMessage("Whoop whoop!!!!");
                    builder.show();

                    Utils.delay(3, new Utils.DelayCallback() {
                        @Override
                        public void afterDelay() {

                            Intent intent = new Intent(GameActivity.this, MenuActivity.class);
                            startActivity(intent);
                            finish();


                        }
                    });
                }else{
                    Utils.delay(1, new Utils.DelayCallback() {
                        @Override
                        public void afterDelay() {

                            int pointDark = setRandomNumberDark();

                            player.damagePlayer(pointDark);

                            health.setText(String.valueOf(player.getHealth()));

                            if(player.getHealth() <= 0){
                                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                                builder.setTitle("You lost!!!!");
                                builder.setMessage("Nooo nooo!!!!");
                                builder.show();
                                worker.delete(playerId);

                                Utils.delay(3, new Utils.DelayCallback() {
                                    @Override
                                    public void afterDelay() {

                                        Intent intent = new Intent(GameActivity.this, MenuActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
    }

    private int setRandomNumberDark(){
        r = new Random();

        int randomNumberNew = r.nextInt(10);

        randomNumber.setText(toString().valueOf(randomNumberNew));

        return randomNumberNew;
    }

    private void randomNumber() {

        final int[] randomNumberNew = new int[1];

        randomNumber = findViewById(R.id.randomNumber);
        randomNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r = new Random();

                randomNumberNew[0] = r.nextInt(10);

                int a = randomNumberNew[0];

                randomNumber.setText(toString().valueOf(a));

            }
        });
        int a = randomNumberNew[0];

        randomNumber.setText(toString().valueOf(a));
    }

}


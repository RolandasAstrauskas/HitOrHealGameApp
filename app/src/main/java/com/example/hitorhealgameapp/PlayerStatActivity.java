package com.example.hitorhealgameapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Adapter.RecyclerViewAdapter;
import Database.DatabaseWorker;
import Database.*;
import Player.Player;

public class PlayerStatActivity extends AppCompatActivity {

    private RecyclerView playersStat;
    private RecyclerView.Adapter adapter;
    private List<Player> playerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_player_stat);


        Database helper = new Database(this);
        SQLiteDatabase database = helper.getReadableDatabase();
        DatabaseWorker worker = new DatabaseWorker(database);

        ArrayList<Player> playerList = (ArrayList<Player>) worker.SelectPlayer();

        Collections.sort(playerList, new SortList());
        Collections.reverse(playerList);

        this.playersStat = findViewById(R.id.playersStat);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        this.playersStat.setLayoutManager(mLayoutManager);

        adapter = new RecyclerViewAdapter(playerList, this);
        this.playersStat.setAdapter(adapter);
        }
    }

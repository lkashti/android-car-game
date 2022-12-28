package com.example.cargame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.TypedArrayUtils;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.PlatformVpnProfile;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.cargame.Models.Player;
import com.example.cargame.Utils.MySharedPreferences;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class MenuActivity extends AppCompatActivity {
    private Button menu_start_btn;
    private Button menu_leaderboard_btn;
    private RadioGroup modes_radio_group;
    private EditText menu_input_player_name;
    private MySharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Objects.requireNonNull(getSupportActionBar()).hide();
        MySharedPreferences.init(getApplicationContext());


        initMenuViews();
        menu_start_btn.setOnClickListener(v -> switchToGameView());
        menu_leaderboard_btn.setOnClickListener(v -> switchToLeaderboardView());
    }

    private void savePlayer() {
        sharedPreferences = MySharedPreferences.getInstance();
        String playerNameInput = menu_input_player_name.getText().toString();
        Player newPlayer = new Player(playerNameInput);
        Gson gson = new Gson();
        ArrayList<Player> playerList;
        String playersJsonString = sharedPreferences.getString("players",null);
        Type type = new TypeToken<ArrayList<Player>>() {}.getType();
        playerList = gson.fromJson(playersJsonString,type);
        if (playerList == null) playerList = new ArrayList<Player>();
        playerList.add(newPlayer);
        sharedPreferences.putString("players", gson.toJson(playerList));
    }


    private void initMenuViews() {
        menu_start_btn = findViewById(R.id.menu_start_btn);
        menu_input_player_name = findViewById(R.id.input_player_name);
        menu_leaderboard_btn = findViewById(R.id.menu_leaderboard_btn);
        modes_radio_group = findViewById(R.id.options_radio_group);
    }

    private void switchToLeaderboardView() {
        Intent switch_intent = new Intent(this, LeaderboardActivity.class);
        startActivity(switch_intent);
        finish();
    }

    private void switchToGameView() {
        savePlayer();
        int selection = getRadioSelection();
        Intent switch_intent = new Intent(this, MainActivity.class);
        switch_intent.putExtra("game_mode", selection);
        startActivity(switch_intent);
        finish();
    }

    private int getRadioSelection() {
        return modes_radio_group.getCheckedRadioButtonId();
    }
}
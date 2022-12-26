package com.example.cargame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import java.util.Objects;

public class MenuActivity extends AppCompatActivity {
    private Button menu_start_btn;
    private Button menu_leaderboard_btn;
    private RadioGroup modes_radio_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Objects.requireNonNull(getSupportActionBar()).hide();

        initMenuViews();

        menu_start_btn.setOnClickListener(v -> switchToGameView());
        menu_leaderboard_btn.setOnClickListener(v-> switchToLeaderboardView());
    }


    private void initMenuViews() {
        menu_start_btn = findViewById(R.id.menu_start_btn);
        menu_leaderboard_btn = findViewById(R.id.menu_leaderboard_btn);
        modes_radio_group =  findViewById(R.id.options_radio_group);
    }
    private void switchToLeaderboardView() {
        Intent switch_intent = new Intent(this, LeaderboardActivity.class);
        startActivity(switch_intent);
        finish();
    }

    private void switchToGameView() {
        int selection = getRadioSelection();
        Intent switch_intent = new Intent(this, MainActivity.class);
        switch_intent.putExtra("game_mode", selection);
        startActivity(switch_intent);
        finish();
    }

    private int getRadioSelection() {
        int checkedRadioButtonId = modes_radio_group.getCheckedRadioButtonId();
        return checkedRadioButtonId;
    }
}
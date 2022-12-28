package com.example.cargame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.cargame.Utils.MySharedPreferences;

import java.util.Objects;

public class LeaderboardActivity extends AppCompatActivity {
    private Button leaderboard_home_btn;
    private Button leaderboard_clear_btn;
    private MySharedPreferences sharedPreferences;
    private Fragment leaderboard_fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        Objects.requireNonNull(getSupportActionBar()).hide();
        MySharedPreferences.init(getApplicationContext());
        sharedPreferences = MySharedPreferences.getInstance();
        findViews();

        leaderboard_clear_btn.setOnClickListener(v->clearLeaderboard());
        leaderboard_home_btn.setOnClickListener(v->switchToHomeView());
        leaderboard_fragment = new RecyclerviewFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frame1,leaderboard_fragment).commit();

//        Fragment map_fragment = new MapFragment();
//        getSupportFragmentManager().beginTransaction().add(R.id.frame2,map_fragment).commit();

    }

    private void switchToHomeView() {
        Intent switch_intent = new Intent(this, MenuActivity.class);
        startActivity(switch_intent);
        finish();
    }

    private void clearLeaderboard() {
        sharedPreferences.clear();
        Toast.makeText(this,"Leaderboard Cleared", Toast.LENGTH_SHORT);
    }
    private void findViews() {
        leaderboard_home_btn = findViewById(R.id.leaderboard_home_btn);
        leaderboard_clear_btn = findViewById(R.id.leaderboard_clear_btn);
    }
}
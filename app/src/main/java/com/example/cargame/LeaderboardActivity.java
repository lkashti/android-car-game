package com.example.cargame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import java.util.Objects;

public class LeaderboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Fragment leaderboard_fragment = new RecyclerviewFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frame1,leaderboard_fragment).commit();

//        Fragment map_fragment = new MapFragment();
//        getSupportFragmentManager().beginTransaction().add(R.id.frame2,map_fragment).commit();

    }
}
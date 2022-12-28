package com.example.cargame;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cargame.Adapters.PlayerAdapter;
import com.example.cargame.Models.Player;
import com.example.cargame.Utils.MySharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RecyclerviewFragment extends Fragment {
    private ArrayList<Player> playerList;
    private MySharedPreferences sharedPreferences;
    private RecyclerView leaderboardList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        MySharedPreferences.init(view.getContext());
        sharedPreferences = MySharedPreferences.getInstance();
        loadPlayers();
        findViews(view);


        PlayerAdapter playerAdapter = new PlayerAdapter(playerList);
        Collections.sort(playerList);
        leaderboardList.setAdapter(playerAdapter);
        leaderboardList.setAdapter(new PlayerAdapter(playerList,
                new PlayerAdapter.OnItemClickListener() {
                    public void onItemClick(Player item) {
                        Toast.makeText(getContext(), "Item Clicked", Toast.LENGTH_LONG).show();

                    }
                }));

        return view;
    }

    private void findViews(View view) {
        leaderboardList = view.findViewById(R.id.recyclerview_leaderboard);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        leaderboardList.setLayoutManager(layoutManager);
    }

    private void loadPlayers() {
        playerList = new ArrayList<Player>();
        Gson gson = new Gson();
        String playersJsonString = sharedPreferences.getString("players", null);
        if (playersJsonString != null) {
            Type type = new TypeToken<ArrayList<Player>>() {
            }.getType();
            playerList = gson.fromJson(playersJsonString, type);
        }
    }
}
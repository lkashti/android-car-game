package com.example.cargame;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cargame.Adapters.PlayerAdapter;
import com.example.cargame.Models.Player;

import java.util.ArrayList;

public class RecyclerviewFragment extends Fragment {
    private ArrayList<Player> playerList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        findViews(view);
        return view;
    }

    private void findViews(View view) {
        RecyclerView leaderboardList = view.findViewById(R.id.recyclerview_leaderboard);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        leaderboardList.setLayoutManager(layoutManager);

        PlayerAdapter playerAdapter = new PlayerAdapter(playerList);
        leaderboardList.setAdapter(playerAdapter);

    }
}
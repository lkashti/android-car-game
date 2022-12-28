package com.example.cargame.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cargame.Models.Player;
import com.example.cargame.R;

import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {
    private final ArrayList<Player> playerList;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Player item);
    }
    public PlayerAdapter(ArrayList<Player> player_list, OnItemClickListener listener) {
        this.playerList = player_list;
        this.listener = listener;
    }

    public PlayerAdapter(ArrayList<Player> playerList) {
        this.playerList = playerList;
        this.listener = null;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View playerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem_player, parent, false);
        return new PlayerViewHolder(playerView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        holder.bind(playerList.get(position), listener);
        Player currentPlayer = playerList.get(position);
        holder.nameTextView.setText(currentPlayer.getName());
        holder.scoreTextView.setText(String.valueOf(currentPlayer.getHighScore()));
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public static class PlayerViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView scoreTextView;

        public PlayerViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.recycleritem_player_name);
            scoreTextView = itemView.findViewById(R.id.recycleritem_player_score);
        }
        public void bind(final Player item, final OnItemClickListener listener) {
            itemView.setOnClickListener(v -> listener.onItemClick(item));
        }
    }
}


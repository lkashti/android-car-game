package com.example.cargame.Models;

import com.google.android.gms.maps.GoogleMap;

public class Player implements Comparable {
    private String name;
    private int highScore;
    private float longitude;
    private float latitude;

    public Player(String name) {
        this.name = name;
        this.highScore = 0;
        this.longitude = 0f;
        this.latitude = 0f;

    }
    public Player(String name, int highScore) {
        this.name = name;
        this.highScore = highScore;
        this.longitude = 0f;
        this.latitude = 0f;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
    @Override
    public int compareTo(Object o) {
        Player player = (Player) o;
        return Integer.compare(player.getHighScore(), this.getHighScore());
    }
}

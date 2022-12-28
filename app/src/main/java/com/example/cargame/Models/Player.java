package com.example.cargame.Models;

public class Player implements Comparable {
    private String name;
    private int highScore;

    public Player(String name) {
        this.name = name;
        this.highScore = 0;
    }

    public Player(String name, int highScore) {
        this.name = name;
        this.highScore = highScore;
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

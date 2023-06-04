package com.tennis.model;

public class Game {
    Player server;
    Player receiver;

    boolean isCompleted = false;
    String winner;

    public Game(Player server, Player receiver) {
        this.server = server;
        this.receiver = receiver;
    }

    public Player getServer() {
        return server;
    }

    public void setServer(Player server) {
        this.server = server;
    }

    public Player getReceiver() {
        return receiver;
    }

    public void setReceiver(Player receiver) {
        this.receiver = receiver;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "GameScore{" +
                "server=" + server +
                ", receiver=" + receiver +
                ", isCompleted=" + isCompleted +
                ", winner='" + winner + '\'' +
                '}';
    }
}

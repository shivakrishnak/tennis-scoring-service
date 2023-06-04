package com.tennis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {
    @JsonIgnore
    private int gameScore = 0;

    @JsonProperty("score")
    private String displayScore = "0";
    @JsonIgnore
    private int serve = 0;
    private PlayerType playerType;

    public Player() {
    }

    public Player(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Player(PlayerType playerType, int gameScore, int serve) {
        this.gameScore = gameScore;
        this.displayScore = String.valueOf(gameScore);
        this.serve = serve;
        this.playerType = playerType;
    }

    public int getGameScore() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
        if (this.gameScore <= 40) {
            setDisplayScore(String.valueOf(gameScore));
        }
    }

    public PlayerType getType() {
        return playerType;
    }

    public int getServe() {
        return serve;
    }

    public void setServe(int serve) {
        this.serve = serve;
    }

    public String getDisplayScore() {
        return displayScore;
    }

    public void setDisplayScore(String displayScore) {
        this.displayScore = displayScore;
    }

    public boolean hasScored40Points() {
        return this.getGameScore() > 40;
    }

    public boolean hasWinningScoreComparedToOtherPlayer(Player otherPlayer) {
        return this.getGameScore() - otherPlayer.getGameScore() > 10;
    }

    public boolean hasAdvantagePoint(Player otherPlayer) {
        return this.getGameScore() - otherPlayer.getGameScore() == 10;
    }

    public boolean checkDeuce(Player otherPlayer) {
        return this.hasScored40Points() && this.hasAdvantagePoint(otherPlayer);
    }

    @Override
    public String toString() {
        return "Player{" +
                "score=" + gameScore +
                ", displayScore='" + displayScore + '\'' +
                ", serve=" + serve +
                ", playerType=" + playerType +
                '}';
    }
}

package com.tennis.model;

public enum PlayerType {
    SERVER("server"), RECEIVER("receiver");

    private final String player;

    PlayerType(String player) {
        this.player = player;
    }

}

package com.tennis.service;

import com.tennis.model.Game;
import com.tennis.model.PlayerType;

public interface TennisService {
    public Game getGameScore();

    void addPoints(PlayerType playerType);
}

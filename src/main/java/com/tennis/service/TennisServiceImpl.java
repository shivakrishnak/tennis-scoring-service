package com.tennis.service;

import com.tennis.model.Game;
import com.tennis.model.Player;
import com.tennis.model.PlayerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TennisServiceImpl implements TennisService {

    @Autowired
    private Map<Integer, Integer> scores;
    @Autowired
    private Game game;
    private Player server;
    private Player receiver;

    public TennisServiceImpl(Map<Integer, Integer> scores, Game game) {
        this.scores = scores;
        this.game = game;
        this.server = game.getServer();
        this.receiver = game.getReceiver();
    }


    @Override
    public Game getGameScore() {
        return game;
    }

    @Override
    public void addPoints(PlayerType playerType) {
        if (PlayerType.SERVER.equals(playerType)) {
            addPointsToPlayer(server, receiver);
        } else {
            addPointsToPlayer(receiver, server);
        }
        System.out.println("Score Table :: " + game);
    }

    private void addPointsToPlayer(Player player, Player otherPlayer) {
        int currentServe = player.getServe();
        player.setGameScore(scores.get(++currentServe));
        player.setServe(currentServe);
        setAdvantageIfDeuce(player, otherPlayer);
        checkForWinner(player, otherPlayer);
    }

    private void checkForWinner(Player player, Player otherPlayer) {
        if (player.hasScored40Points()) {
            if (player.hasWinningScoreComparedToOtherPlayer(otherPlayer)) {
                game.setCompleted(true);
                game.setWinner(player.getType().name());
            }
        }
    }

    private void setAdvantageIfDeuce(Player player, Player otherPlayer) {
        if (player.checkDeuce(otherPlayer)) {
            player.setDisplayScore("A");
        }
    }
}
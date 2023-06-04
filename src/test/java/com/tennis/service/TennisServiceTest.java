package com.tennis.service;

import com.tennis.model.Game;
import com.tennis.model.Player;
import com.tennis.model.PlayerType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class TennisServiceTest {

    Map<Integer, Integer> scores;
    private Game game;
    private Player server;
    private Player receiver;

    private TennisService tennisService;

    @BeforeEach
    void setUp() {
        server = new Player(PlayerType.SERVER);
        receiver = new Player(PlayerType.RECEIVER);
        game = new Game(server, receiver);

        scores = getScores();
        tennisService = new TennisServiceImpl(scores, game);
    }

    @Test
    public void shouldReturnInitialScoresWhenTheMatchIsStarted() {
        Game expected = new Game(new Player(PlayerType.SERVER), new Player(PlayerType.RECEIVER));
        assertThat(tennisService.getGameScore().getServer().getGameScore()).isEqualTo(0);
        assertThat(tennisService.getGameScore().getReceiver().getGameScore()).isEqualTo(0);
    }

    @Test
    public void shouldReturnServerScoreAs15_WhenServerWinPoint_GivenServerScoreIs0AndReceiverScoreIs0() {
        tennisService.addPoints(PlayerType.SERVER);
        assertThat(tennisService.getGameScore().getServer().getGameScore()).isEqualTo(15);
        assertThat(tennisService.getGameScore().getReceiver().getGameScore()).isEqualTo(0);
    }

    @Test
    public void shouldReturnReceiverScoreAs15_WhenReceiverWinPoint_GivenServerScoreIs0AndReceiverScoreIs0() {
        tennisService.addPoints(PlayerType.RECEIVER);
        assertThat(tennisService.getGameScore().getServer().getGameScore()).isEqualTo(0);
        assertThat(tennisService.getGameScore().getReceiver().getGameScore()).isEqualTo(15);
    }

    @Test
    public void shouldReturnServerScoreAs30_WhenServerWinPoint_GivenServerScoreIs15AndReceiverScoreIs0() {
        tennisService.addPoints(PlayerType.SERVER);
        assertThat(tennisService.getGameScore().getServer().getGameScore()).isEqualTo(15);
        assertThat(tennisService.getGameScore().getReceiver().getGameScore()).isEqualTo(0);
    }

    @Test
    public void shouldReturnReceiverScoreAs30_WhenReceiverWinPoint_GivenServerScoreIs15AndReceiverScoreIs15() {
        Player server = new Player(PlayerType.SERVER, 15, 1);
        Player receiver = new Player(PlayerType.RECEIVER, 15, 1);
        Game game = new Game(server, receiver);
        tennisService = new TennisServiceImpl(scores, game);

        tennisService.addPoints(PlayerType.RECEIVER);
        assertThat(tennisService.getGameScore().getServer().getGameScore()).isEqualTo(15);
        assertThat(tennisService.getGameScore().getReceiver().getGameScore()).isEqualTo(30);
    }

    @Test
    public void shouldReturnServerScoreAs40_WhenServerWinPoint_GivenServerScoreIs30AndReceiverScoreIs30() {
        Player server = new Player(PlayerType.SERVER, 30, 2);
        Player receiver = new Player(PlayerType.RECEIVER, 30, 2);
        Game game = new Game(server, receiver);
        tennisService = new TennisServiceImpl(scores, game);

        tennisService.addPoints(PlayerType.SERVER);
        assertThat(tennisService.getGameScore().getServer().getGameScore()).isEqualTo(40);
        assertThat(tennisService.getGameScore().getReceiver().getGameScore()).isEqualTo(30);
    }

    @Test
    public void shouldReturnServerAsWinner_WhenServerWinPoint_GivenServerScoreIs40AndReceiverScoreIs30() {
        Player server = new Player(PlayerType.SERVER, 40, 3);
        Player receiver = new Player(PlayerType.RECEIVER, 30, 2);
        Game game = new Game(server, receiver);
        tennisService = new TennisServiceImpl(scores, game);

        tennisService.addPoints(PlayerType.SERVER);
        assertThat(tennisService.getGameScore().getWinner()).isEqualTo("SERVER");
    }

    @Test
    public void shouldReturnServerAsAdvantage_WhenServerWinPoint_GivenServerScoreIs40AndReceiverScoreIs40() {
        Player server = new Player(PlayerType.SERVER, 40, 3);
        Player receiver = new Player(PlayerType.RECEIVER, 40, 3);
        Game game = new Game(server, receiver);
        tennisService = new TennisServiceImpl(scores, game);

        tennisService.addPoints(PlayerType.SERVER);
        assertThat(tennisService.getGameScore().getServer().getDisplayScore()).isEqualTo("A");
        assertThat(tennisService.getGameScore().getReceiver().getDisplayScore()).isEqualTo("40");
    }


    @Test
    public void shouldReturnReceiverAsAdvantage_WhenServerWinPoint_GivenServerScoreIs40AndReceiverScoreIs40() {
        Player server = new Player(PlayerType.SERVER, 40, 3);
        Player receiver = new Player(PlayerType.RECEIVER, 40, 3);
        Game game = new Game(server, receiver);
        tennisService = new TennisServiceImpl(scores, game);

        tennisService.addPoints(PlayerType.RECEIVER);
        assertThat(tennisService.getGameScore().getServer().getDisplayScore()).isEqualTo("40");
        assertThat(tennisService.getGameScore().getReceiver().getDisplayScore()).isEqualTo("A");
    }

    @Test
    public void shouldReturnReceiverAsWinner_WhenReceiverWinPoint_GivenServerScoreIs40AndReceiverHasAdvantage() {
        Player server = new Player(PlayerType.SERVER, 40, 3);
        Player receiver = new Player(PlayerType.RECEIVER, 50, 4);
        Game game = new Game(server, receiver);
        tennisService = new TennisServiceImpl(scores, game);

        tennisService.addPoints(PlayerType.RECEIVER);
        assertThat(tennisService.getGameScore().getWinner()).isEqualTo("RECEIVER");
    }

    private Map<Integer, Integer> getScores() {
        Map<Integer, Integer> scores = new HashMap<>();
        scores.put(0, 0);
        scores.put(1, 15);
        scores.put(2, 30);
        scores.put(3, 40);
        scores.put(4, 50);
        scores.put(5, 60);
        return scores;
    }
}
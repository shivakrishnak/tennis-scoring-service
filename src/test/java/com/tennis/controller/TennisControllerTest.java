package com.tennis.controller;

import com.tennis.model.Game;
import com.tennis.model.Player;
import com.tennis.model.PlayerType;
import com.tennis.service.TennisService;
import com.tennis.service.TennisServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TennisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private TennisService service;
    private TennisController controller;

    private Game game;

    @BeforeEach
    void setUp() {
        game = getGame();
        Map scores = getScores();
        service = new TennisServiceImpl(scores, game);
        controller = new TennisController(service);
    }

    @Test
    public void ShouldGetGameScore_ResponseAsOK() throws Exception {
        mockMvc.perform(get("/scores")).andExpect(status().isOk());
    }

    @Test
    public void ShouldGetGameScore() {
        Game actual = controller.getScore();
        Assertions.assertThat(actual).isEqualTo(game);
    }

    @Test
    public void ShouldReturnAsOK_WhenPointsAreAdded() throws Exception {
        mockMvc.perform(put("/points").param("player", "server")).andExpect(status().isOk());
    }

    @Test
    public void ShouldReturnInternalServerError_WhenInvalidPlayerIsPassed() throws Exception {
        mockMvc.perform(put("/points").param("player", "test")).andExpect(status().is5xxServerError());
    }

    @Test
    public void ShouldReturnInternalServerError_WhenGameIsOver() throws Exception {
        game = getGame();
        game.setCompleted(true);
        mockMvc.perform(put("/points").param("player", "test")).andExpect(status().is5xxServerError());
    }

    private Game getGame() {
        return new Game(new Player(PlayerType.SERVER), new Player(PlayerType.RECEIVER));
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
package com.tennis;

import com.tennis.model.Game;
import com.tennis.model.Player;
import com.tennis.model.PlayerType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
class DataConfig {

    @Bean
    public Game startNewGame() {
        return new Game(new Player(PlayerType.SERVER), new Player(PlayerType.RECEIVER));
    }

    @Bean
    public Map<Integer, Integer> scores() {
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

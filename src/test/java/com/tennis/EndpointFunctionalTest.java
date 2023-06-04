package com.tennis;

import com.tennis.model.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TennisScoringServiceApplication.class)
class EndpointFunctionalTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldGetGameScore() throws Exception {
        ResponseEntity<Game> response = restTemplate.getForEntity("/scores", Game.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldAddPoints() throws Exception {
        String player = "server";
        Map<String, String> requestVariables = new HashMap<>();
        requestVariables.put("player", player);
        restTemplate.put("/points", requestVariables);
    }
}
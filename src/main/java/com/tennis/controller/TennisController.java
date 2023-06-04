package com.tennis.controller;

import com.tennis.model.Game;
import com.tennis.model.PlayerType;
import com.tennis.service.TennisService;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TennisController {

    @Autowired
    TennisService tennisService;

    public TennisController(TennisService tennisService) {
        this.tennisService = tennisService;
    }

    @GetMapping("/scores")
    public Game getScore() {
        return tennisService.getGameScore();
    }

    @PutMapping("/points")
    public ResponseEntity addPoints(@RequestParam String player) {
        if (isInvalidPlayerType(player)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        if (isGameOver()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Game is Completed, Please start a new Game");
        }
        tennisService.addPoints(EnumUtils.getEnumIgnoreCase(PlayerType.class, player));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private boolean isGameOver() {
        return tennisService.getGameScore().isCompleted();
    }

    private boolean isInvalidPlayerType(String player) {
        return StringUtils.isBlank(player) || !EnumUtils.isValidEnumIgnoreCase(PlayerType.class, player);
    }
}

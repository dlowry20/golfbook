package com.example.golfbook.score.controller;

import com.example.golfbook.score.dto.CurrentRoundDto;
import com.example.golfbook.score.service.CurrentRoundService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/current_round")
public class CurrentRoundController {

    private final CurrentRoundService currentRoundService;

    public CurrentRoundController(CurrentRoundService currentRoundService) {
        this.currentRoundService = currentRoundService;
    }

    @GetMapping("/list_by_tournament_id/{tournament_id}")
    public ResponseEntity<List<CurrentRoundDto>> getCurrentRound(
            @PathVariable("tournament_id") int tournamentId
    ) {
        return new ResponseEntity<>(currentRoundService.getLeaderboard(tournamentId), HttpStatus.OK);
    }


}

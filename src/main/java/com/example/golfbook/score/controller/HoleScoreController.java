package com.example.golfbook.score.controller;

import com.example.golfbook.score.dto.HoleScoreDto;
import com.example.golfbook.score.dto.RoundScoreDto;
import com.example.golfbook.score.model.HoleScore;
import com.example.golfbook.score.model.RoundScore;
import com.example.golfbook.score.service.HoleScoreService;
import com.example.golfbook.score.service.RoundScoreService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:5173"})
@Controller
@RequestMapping("/hole_score")
public class HoleScoreController {

    private final RoundScoreService roundScoreService;
    private final HoleScoreService holeScoreService;

    public HoleScoreController(RoundScoreService roundScoreService,
                               HoleScoreService holeScoreService) {
        this.roundScoreService = roundScoreService;
        this.holeScoreService = holeScoreService;
    }

    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HoleScore> addHoleScore(
            @RequestBody HoleScoreDto holeScoreDto,
            HttpServletRequest httpServletRequest) {
        String userId = httpServletRequest.getRemoteUser();

        return new ResponseEntity<>(
                holeScoreService.insertHoleScore(holeScoreDto, userId),
                HttpStatus.CREATED
        );
    }

    @GetMapping(path = "/current_round", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HoleScoreDto>> getHoleScores(
            HttpServletRequest httpServletRequest) {
        String userId = httpServletRequest.getRemoteUser();
        return new ResponseEntity<>(
                holeScoreService.getAllScoresByCurrentRound(userId),
                HttpStatus.OK
        );
    }
}

package com.example.golfbook.score.controller;

import com.example.golfbook.score.dto.HoleScoreDto;
import com.example.golfbook.score.dto.RoundScoreDto;
import com.example.golfbook.score.model.HoleScore;
import com.example.golfbook.score.model.RoundScore;
import com.example.golfbook.score.service.HoleScoreService;
import com.example.golfbook.score.service.RoundScoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;

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
            Model model,
            HttpSession httpSession,
            HttpServletRequest httpServletRequest) {
        String userId = httpServletRequest.getRemoteUser();
        Map<String, UUID> userIDMap = (Map<String, UUID>) httpSession.getAttribute("currentRounds");
        UUID currentRound = userIDMap.get(userId);
        holeScoreDto.setRoundId(currentRound);
        if (currentRound == null) {
            throw new IllegalStateException("Round Not Set for session");
        }

        return new ResponseEntity<>(holeScoreService.insertHoleScore(holeScoreDto), HttpStatus.CREATED);
    }
}

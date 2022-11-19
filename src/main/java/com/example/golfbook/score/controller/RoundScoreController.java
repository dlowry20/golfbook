package com.example.golfbook.score.controller;

import com.example.golfbook.score.dto.RoundScoreDto;
import com.example.golfbook.score.model.RoundScore;
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
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/round_score")
public class RoundScoreController {

    private final RoundScoreService roundScoreService;

    public RoundScoreController(
            RoundScoreService roundScoreService) {

        this.roundScoreService = roundScoreService;
    }

    @PostMapping(path = "/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoundScore> addRoundScore(
            @RequestBody RoundScoreDto roundScoreDto,
            HttpServletRequest httpServletRequest
    ) {
        RoundScore roundScore = roundScoreService.insertRoundScore(roundScoreDto);
        UUID roundUuid = roundScore.getRoundId();
        updateUsersCurrentRound(httpServletRequest, roundUuid);
        return new ResponseEntity<>(roundScore, HttpStatus.CREATED);
    }

    private void updateUsersCurrentRound(HttpServletRequest httpServletRequest, UUID roundId) {
        Map<String, UUID> userToRoundIdMap = (Map<String, UUID>) httpServletRequest.getSession().getAttribute("currentRounds");
        if (userToRoundIdMap == null) {
            userToRoundIdMap = new HashMap<String, UUID>();
            httpServletRequest.getSession().setAttribute("currentRounds", userToRoundIdMap);
        }
        userToRoundIdMap.put(httpServletRequest.getRemoteUser(), roundId);
        httpServletRequest.getSession().setAttribute("currentRounds", userToRoundIdMap);
    }

}

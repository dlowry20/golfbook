package com.example.golfbook.score.controller;

import com.example.golfbook.score.dto.RoundScoreDto;
import com.example.golfbook.score.model.RoundScore;
import com.example.golfbook.score.service.RoundScoreService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RequestMapping("/round_score")
public class RoundScoreController {

    private final RoundScoreService roundScoreService;

    public RoundScoreController(
            RoundScoreService roundScoreService) {

        this.roundScoreService = roundScoreService;
    }

//    @CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"}, allowCredentials = "true")
    @PostMapping(path = "/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoundScore> addRoundScore(
            @RequestBody RoundScoreDto roundScoreDto,
            HttpServletRequest httpServletRequest
    ) {
        RoundScore roundScore = roundScoreService.insertRoundScore(
                roundScoreDto, httpServletRequest.getRemoteUser()
        );
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(
                roundScore,
                headers, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"}, allowCredentials = "true")
    @PostMapping(path = "/post",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> testPost() {
        return new ResponseEntity<>(
                "Test Worked",
                HttpStatus.CREATED);
    }
}

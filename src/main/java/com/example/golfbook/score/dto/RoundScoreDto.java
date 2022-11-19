package com.example.golfbook.score.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RoundScoreDto {

    private BigInteger courseId;
    private String courseName;
    private LocalDate datePlayed;
    private int score;
    private int coursePar;
    private String userId;
    private int tournamentId;
}

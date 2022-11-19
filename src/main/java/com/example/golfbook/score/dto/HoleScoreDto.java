package com.example.golfbook.score.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;

@Getter
@AllArgsConstructor
public class HoleScoreDto {

    private final BigInteger courseId;
    private final int holeNumber;
    private final int par; // TODO : Likely get this from the course object and don't require user to pass it
    private final int score;
    private final boolean gir;
    private final boolean fairway;
    private final int putts;
}

package com.example.golfbook.score.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class HoleScoreDto {

    private BigInteger courseId;
    @Setter
    private UUID roundId;
    private int holeNumber;
    private int par; // TODO : Likely get this from the course object and don't require user to pass it
    private int score;
    private boolean gir;
    private boolean fairway;
    private int putts;
}

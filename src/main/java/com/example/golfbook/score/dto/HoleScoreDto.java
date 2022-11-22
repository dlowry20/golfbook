package com.example.golfbook.score.dto;

import com.example.golfbook.score.model.HoleScore;
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

    public HoleScoreDto(HoleScore holeScore) {
        this.courseId = holeScore.getCourseId();
        this.holeNumber = holeScore.getHoleNumber();
        this.par = holeScore.getPar();
        this.score = holeScore.getScore();
        this.gir = holeScore.isGir();
        this.fairway = holeScore.isFairway();
        this.putts = holeScore.getPutts();
    }
}

package com.example.golfbook.score.dto;

import com.example.golfbook.score.model.HoleScore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Hole {

    private final int score;
    private final int par;
    private final int holeNumber;
    private final boolean gir;
    private final boolean fairway;
    private final int putts;

    public Hole(HoleScore holeScore) {
        this.score = holeScore.getScore();
        this.par = holeScore.getPar();
        this.holeNumber = holeScore.getHoleNumber();
        this.gir = holeScore.isGir();
        this.fairway = holeScore.isFairway();
        this.putts = holeScore.getPutts();
    }
}

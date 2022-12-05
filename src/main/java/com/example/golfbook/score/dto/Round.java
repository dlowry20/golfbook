package com.example.golfbook.score.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class Round {

    private String courseName;
    private LocalDate datePlayed;
    private int holesPlayed;
    private int score;
    private int coursePar;
    private List<Hole> holes;
}

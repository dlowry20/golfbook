package com.example.golfbook.score.dto;

import com.example.golfbook.score.model.CurrentRound;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CurrentRoundDto {
    private final String userId;
    private final int relationToPar;
    private final int holesPlayed;

    public CurrentRoundDto(CurrentRound currentRound) {
        this.userId = currentRound.getUserId();
        this.relationToPar = currentRound.getRelationToPar();
        this.holesPlayed = currentRound.getHolesPlayed();
    }
}



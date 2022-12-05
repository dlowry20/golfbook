package com.example.golfbook.score.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CurrentRound {

    @Id
    private String userId;

    private UUID roundId;
    private BigInteger courseId;
    @Setter
    private int relationToPar;
    @Setter
    private int holesPlayed;
    private int tournamentId;
}

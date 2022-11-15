package com.example.golfbook.score.model;

import com.example.golfbook.score.model.id.RoundScoreId;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@IdClass(RoundScoreId.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RoundScore {

    @Id
    private BigInteger courseId;
    @Id
    private UUID roundId;
    private String userId;
    private int par;
    @Setter
    private int roundScore;
    private String courseName;
    private LocalDate datePlayed;
}

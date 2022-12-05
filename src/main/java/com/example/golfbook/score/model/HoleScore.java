package com.example.golfbook.score.model;

import com.example.golfbook.score.model.id.HoleScoreId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.math.BigInteger;
import java.util.UUID;

@Entity
@IdClass(HoleScoreId.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class HoleScore {

    @Id
    private BigInteger courseId;
    @Id
    private UUID roundId;
    @Id
    private int holeNumber;
    private String userId;
    private int par;
    private int score;
    private boolean gir;
    private boolean fairway;
    private int putts;

    public HoleScore(
            BigInteger courseId,
            UUID roundId,
            int holeNumber,
            String userId,
            int par,
            int score
            ) {
        new HoleScore(courseId, roundId, holeNumber, userId, par, score, false, false, 0);
    }
}

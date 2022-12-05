package com.example.golfbook.score.model.id;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
public class HoleScoreId implements Serializable {
    private BigInteger courseId;
    private UUID roundId;
    private int holeNumber;

    public HoleScoreId(BigInteger courseId, UUID roundId, int holeNumber) {
        this.courseId = courseId;
        this.roundId = roundId;
        this.holeNumber = holeNumber;
    }
}

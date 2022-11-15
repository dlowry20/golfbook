package com.example.golfbook.score.model.id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
public class RoundScoreId implements Serializable {

    private BigInteger courseId;
    private UUID roundId;

    public RoundScoreId(BigInteger courseId, UUID roundId) {
        this.courseId = courseId;
        this.roundId = roundId;
    }
}

package com.example.golfbook.course.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Hole {

    @Id
    private BigInteger holesId;
    @Id
    private BigInteger courseId;
    @Id
    private int holeNum;
    private int holePar;

}

package com.example.golfbook.course.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Hole implements Serializable {

    @Id
    @GeneratedValue
    private BigInteger holesId;
    private int holeNum;
    @Setter
    private int holePar;
    private BigInteger courseId;

}

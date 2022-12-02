package com.example.golfbook.course.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@IdClass(HoleId.class)
public class Hole implements Serializable {

    @Id
    private BigInteger courseId;
    @Id
    private int holeNumber;
    private int holePar;
}

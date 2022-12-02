package com.example.golfbook.course.dto;

import com.example.golfbook.course.model.Hole;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;

@Getter
@AllArgsConstructor
public class HoleDto {
    private final BigInteger holesId;
    private final BigInteger courseId;
    private final int holeNum;
    private final int holePar;



    public HoleDto(Hole hole) {
        this.holesId = BigInteger.ZERO;
        this.courseId = hole.getCourseId();
        this.holeNum = hole.getHoleNumber();
        this.holePar = hole.getHolePar();

    }
}

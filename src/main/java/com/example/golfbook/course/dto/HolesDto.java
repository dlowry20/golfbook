package com.example.golfbook.course.dto;

import com.example.golfbook.course.model.Hole;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;

@Getter
@AllArgsConstructor
public class HolesDto {
    private final BigInteger holesId;
    private final BigInteger courseId;
    private final int holeNumber;
    private final int holePar;


    public HolesDto(Hole hole) {
        this.holesId = BigInteger.ZERO;
        this.courseId = hole.getCourseId();
        this.holeNumber = hole.getHoleNumber();
        this.holePar = hole.getHolePar();
    }
}

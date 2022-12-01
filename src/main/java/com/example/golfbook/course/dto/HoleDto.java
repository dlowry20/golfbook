package com.example.golfbook.course.dto;

import com.example.golfbook.course.model.Hole;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;

@Getter
@AllArgsConstructor
public class HoleDto {
    private final BigInteger holesId;
    private final int holeNum;
    private final int holePar;
    private final BigInteger courseId;


    public HoleDto(Hole hole) {
        this.holesId = hole.getHolesId();
        this.holeNum = hole.getHoleNum();
        this.holePar = hole.getHolePar();
        this.courseId = hole.getCourseId();
    }
}

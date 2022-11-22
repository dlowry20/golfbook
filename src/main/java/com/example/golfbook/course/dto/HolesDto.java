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
    private final int holeNum;
    private final int holePar;


    public HolesDto(Hole hole) {
        this.holesId = hole.getHolesId();
        this.courseId = hole.getCourseId();
        this.holeNum = hole.getHoleNum();
        this.holePar = hole.getHolePar();
    }
}

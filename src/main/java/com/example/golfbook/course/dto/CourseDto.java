package com.example.golfbook.course.dto;


import com.example.golfbook.course.model.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;

@Getter
@AllArgsConstructor
public class CourseDto {
    private final BigInteger courseId;
    private final String courseName;
    private final int par;


    public CourseDto(Course course) {
        this.courseId = course.getCourseId();
        this.courseName = course.getCourseName();
        this.par = course.getPar();
    }
}

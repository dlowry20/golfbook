package com.example.golfbook.course.model;

import org.springframework.data.annotation.Id;

import javax.annotation.processing.Generated;
import java.math.BigInteger;

public class CourseModel {

    @Id
    private BigInteger courseId;
    private String courseName;
}

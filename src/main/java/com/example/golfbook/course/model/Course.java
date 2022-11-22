package com.example.golfbook.course.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Course {

    @Id
    private BigInteger courseId;
    @Setter
    private String courseName;
    @Setter
    private int par;
    private BigInteger holesId;
}

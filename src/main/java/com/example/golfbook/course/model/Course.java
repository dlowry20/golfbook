package com.example.golfbook.course.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Course implements Serializable {

    @Id
    @GeneratedValue
    private BigInteger courseId;
    @Setter
    private String courseName;
    @Setter
    private int par;
}

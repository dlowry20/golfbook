package com.example.golfbook;

import com.example.golfbook.course.model.Course;
import com.example.golfbook.course.model.Hole;
import com.example.golfbook.course.repository.CourseRepository;
import com.example.golfbook.course.repository.HoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigInteger;

@SpringBootApplication
public class GolfbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(GolfbookApplication.class, args);
    }

}

package com.example.golfbook.course.repository;

import com.example.golfbook.course.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends CrudRepository<Course, BigInteger> {

    Optional<Course> findFirstByCourseName(String courseName);
}

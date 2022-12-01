package com.example.golfbook.course.repository;

import com.example.golfbook.course.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, BigInteger> {

//    List<Course> getAllByCourseId();
}

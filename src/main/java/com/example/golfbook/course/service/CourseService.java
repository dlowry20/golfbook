package com.example.golfbook.course.service;

import com.example.golfbook.course.model.Course;
import com.example.golfbook.course.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createNewCourse(Course course) {
        return courseRepository.save(course);
    }
    public List<Course> getAllCourses() {
        Iterable<Course> courses = this.courseRepository.findAll();
        List<Course> courseList = new ArrayList<>();
        courses.forEach(course->{courseList.add(course);});
        return courseList;
    }

    public Optional<Course> findCourseById(BigInteger id) {
        return courseRepository.findById(id);
    }

    public Course editCourse(Course newCourse, BigInteger id) {
        return courseRepository.findById(id)
                .map(course -> {
                    course.setCourseName(newCourse.getCourseName());
                    course.setPar(newCourse.getPar());
                    return courseRepository.save(course);
                })
                .orElseGet(() -> {
                    //fix this yet
                    return newCourse;
                });
    }

    public void deleteCourse(BigInteger id) {
        courseRepository.deleteById(id);
    }



}

package com.example.golfbook.course.service;

import com.example.golfbook.course.dto.CourseDto;
import com.example.golfbook.course.model.Course;
import com.example.golfbook.course.repository.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<Course> createNewCourse(Course course) {
        try {
            Course _course = courseRepository
                    .save(course);
            return new ResponseEntity<>(_course, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Course>> findAllCourses() {
        try {
            Iterable<Course> courses = this.courseRepository.findAll();
            List<Course> courseList = new ArrayList<>();
            courses.forEach(course->{courseList.add(course);});
            return new ResponseEntity<>(courseList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Course> findCourseById(BigInteger id) {
        Optional<Course> courseData = courseRepository.findById(id);

        if (courseData.isPresent()) {
            return new ResponseEntity<>(courseData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Course> updateCourse(BigInteger id, Course newCourse) {
        Optional<Course> courseData = courseRepository.findById(id);

        if (courseData.isPresent()) {
            Course _course = courseData.get();
            _course.setCourseName(newCourse.getCourseName());
            _course.setPar(newCourse.getPar());
            return new ResponseEntity<>(courseRepository.save(_course), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public Course insertCourse(CourseDto courseDto) {
        Course course = courseRepository.save(
                Course.builder()
                        .courseId(courseDto.getCourseId())
                        .courseName(courseDto.getCourseName())
                        .par(courseDto.getPar())
                        .build()
        );
        return course;
    }




}

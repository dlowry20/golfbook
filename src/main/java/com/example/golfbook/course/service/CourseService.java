package com.example.golfbook.course.service;

import com.example.golfbook.course.model.Course;
import com.example.golfbook.course.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllByCourseId(){
        Iterable<Course> courses = this.courseRepository.findAll();
        List<Course> courseList = new ArrayList<>();
        courses.forEach(courseList::add);
        return courseList;
    }



}

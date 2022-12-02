package com.example.golfbook.course.controller;

import com.example.golfbook.course.model.Course;
import com.example.golfbook.course.repository.CourseRepository;
import com.example.golfbook.course.service.CourseService;
import org.apache.catalina.Store;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@Controller
@RequestMapping("/courses")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @PostMapping("/courses")
    public ResponseEntity<Course> createNewCourse(@RequestBody Course course) {
        return courseService.createNewCourse(course);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        return courseService.findAllCourses();

    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") BigInteger id) {
        return courseService.findCourseById(id);
    }


    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") BigInteger id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

}

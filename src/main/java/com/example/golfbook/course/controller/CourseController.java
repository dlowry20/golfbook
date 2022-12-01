package com.example.golfbook.course.controller;

import com.example.golfbook.course.model.Course;
import com.example.golfbook.course.repository.CourseRepository;
import com.example.golfbook.course.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/courses")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @PostMapping("/")
    public Course createCourse(Course c) {
        return courseService.createNewCourse(c);
    }

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/courses/{id}")
    public Optional<Course> getCourse(@PathVariable BigInteger id) {
        return courseService.findCourseById(id);
    }

    @PutMapping("/courses/{id}")
    public Course editCourse(@RequestBody Course c, @PathVariable BigInteger id) {
        return courseService.editCourse(c, id);
    }

    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable BigInteger id) {
        courseService.deleteCourse(id);
    }
}

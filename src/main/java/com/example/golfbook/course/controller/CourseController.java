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

@CrossOrigin
@RestController
@RequestMapping("/courses")
public class CourseController {

    private CourseService courseService;
    private CourseRepository courseRepository;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @PostMapping(path="/add_course", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> addCourse(@RequestBody Course newCourse) throws ServerException {
        Course course = courseRepository.save(newCourse);
        if(course == null) {
            throw new ServerException("Course was not created");
        } else {
            return new ResponseEntity<>(course, HttpStatus.CREATED);
        }
    }

    @GetMapping(path="/get_course", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCourse(HttpServletRequest httpServletRequest) {
        return new ResponseEntity<Object>(courseService.getAllByCourseId(), HttpStatus.OK);
    }

    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable BigInteger id) {
        courseRepository.deleteById(id);
    }
}

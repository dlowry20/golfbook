package com.example.golfbook.course.controller;

import com.example.golfbook.course.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/add_course")
    public void addCourse() {
        courseService.addCourse();
    }

    @GetMapping("/get_course")
    public void gtCourse() {
        courseService.getCourse();
    }
}

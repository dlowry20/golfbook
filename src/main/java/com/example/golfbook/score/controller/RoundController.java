package com.example.golfbook.score.controller;

import com.example.golfbook.score.dto.Round;
import com.example.golfbook.score.model.RoundScore;
import com.example.golfbook.score.service.RoundService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173"})
@Controller
@RequestMapping("/round")
public class RoundController {

    private final RoundService roundService;

    public RoundController(RoundService roundService) {
        this.roundService = roundService;
    }

    @GetMapping("/get_by_course/{course_name}")
    public List<Round> getAllRoundsByCourse(
            @PathVariable("course_name") String courseName
    ) {
        return roundService.getAllRoundsAtCourse(courseName);
    }

}

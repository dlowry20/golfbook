package com.example.golfbook.course.controller;

import com.example.golfbook.course.model.Course;
import com.example.golfbook.course.model.Hole;
import com.example.golfbook.course.model.HoleId;
import com.example.golfbook.course.service.HoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/holes")
public class HoleController {
    private HoleService holeService;

    public HoleController(HoleService holeservice) {
        this.holeService = holeservice;
    }

    @CrossOrigin(origins = {"http://localhost:5173"}, allowCredentials = "true")
    @PostMapping(path="/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Hole> createNewHole(@RequestBody Hole h) {
        return holeService.createNewHole(h);
    }

    @CrossOrigin(origins = {"http://localhost:5173"}, allowCredentials = "true")
    @GetMapping("/all/{id}")
    public ResponseEntity<List<Hole>>  getAllHolesByCourseId(@PathVariable BigInteger id) {
        return holeService.findHolesByCourseId(id);
    }
    @CrossOrigin(origins = {"http://localhost:5173"}, allowCredentials = "true")
    @GetMapping("/hole/{id}/{holeNumber}")
    public ResponseEntity<List<Hole>> getSingleCourse(@PathVariable BigInteger id, @PathVariable int holeNumber) {
        return holeService.findSingleHole(id, holeNumber);
    }

    @CrossOrigin(origins = {"http://localhost:5173"}, allowCredentials = "true")
    @GetMapping("/")
    public ResponseEntity<List<Hole>> getAllHoles() {
        return holeService.findAllHoles();

    }

}

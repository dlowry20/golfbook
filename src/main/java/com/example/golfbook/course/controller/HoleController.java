package com.example.golfbook.course.controller;

import com.example.golfbook.course.model.Course;
import com.example.golfbook.course.model.Hole;
import com.example.golfbook.course.repository.HoleRepository;
import com.example.golfbook.course.service.HoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/holes")
public class HoleController {
    private HoleService holeService;

    @PostMapping("/")
    public Hole createNewHole(Hole h) {
        return holeService.createNewHole(h);
    }

    @GetMapping("/holes/{id}")
    public Optional<Hole> getHoleById(@PathVariable BigInteger id) {
        return holeService.findHoleById(id);
    }

    @GetMapping("/holes/all/{id}")
    public List<Hole> getAllHolesByCourseId(@PathVariable BigInteger id) {
        return holeService.findAllHolesByCourse(id);
    }

    @PutMapping("/holes/{id}")
    public Hole editHole(@RequestBody Hole hole, @PathVariable BigInteger id) {
        return holeService.editHolePar(hole, id);
    }

    @DeleteMapping("/holes/{id}")
    public void deleteHole(@PathVariable BigInteger id) {
        holeService.deleteHole(id);
    }
    //Need to add a way to delete all holes associated with a CourseId

}

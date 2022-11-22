package com.example.golfbook.course.controller;

import com.example.golfbook.course.repository.HoleRepository;
import com.example.golfbook.course.service.HoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/holes")
public class HoleController {
    private HoleService holeService;
    private HoleRepository holeRepository;

    @GetMapping(path = "get_Holes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getHole(HttpServletRequest httpServletRequest) {
        return new ResponseEntity<Object>(holeService.getAllByHoleId(), HttpStatus.OK);
    }
}

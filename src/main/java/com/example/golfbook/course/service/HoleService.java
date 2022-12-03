package com.example.golfbook.course.service;

import com.example.golfbook.course.model.Hole;
import com.example.golfbook.course.model.HoleId;
import com.example.golfbook.course.repository.HoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class HoleService {
    private final HoleRepository holeRepository;

    public HoleService(HoleRepository holeRepository) {
        this.holeRepository = holeRepository;
    }

    public ResponseEntity<Hole> createNewHole(Hole hole) {
        try {
            Hole _hole = holeRepository
                    .save(hole);
            return new ResponseEntity<>(_hole, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Hole>>  findHolesByCourseId(BigInteger id) {
        Iterable<Hole> holes = this.holeRepository.findAll();
        List<Hole> holeList = new ArrayList<>();
        holes.forEach(hole->{
            if(hole.getCourseId().equals(id)) {
                holeList.add(hole);
            }
        });
        return new ResponseEntity<>(holeList, HttpStatus.OK);
    }

    public ResponseEntity<List<Hole>>  findSingleHole(BigInteger id, int holeNum) {
        Iterable<Hole> holes = this.holeRepository.findAll();
        List<Hole> holeList = new ArrayList<>();
        holes.forEach(hole->{
            if(hole.getCourseId().equals(id)) {
                if(hole.getHoleNumber() == holeNum) {
                    holeList.add(hole);
                }
            }
        });
        return new ResponseEntity<>(holeList, HttpStatus.OK);
    }

}

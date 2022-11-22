package com.example.golfbook.course.service;

import com.example.golfbook.course.model.Hole;
import com.example.golfbook.course.repository.HoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HoleService {
    private final HoleRepository holeRepository;

    public HoleService(HoleRepository holeRepository) {
        this.holeRepository = holeRepository;
    }

    public List<Hole> getAllByHoleId(){
        Iterable<Hole> holes = this.holeRepository.findAll();
        List<Hole> holeList = new ArrayList<>();
        holes.forEach(hole->{holeList.add(hole);});
        return holeList;
    }
}

package com.example.golfbook.course.service;

import com.example.golfbook.course.model.Course;
import com.example.golfbook.course.model.Hole;
import com.example.golfbook.course.repository.HoleRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HoleService {
    private final HoleRepository holeRepository;

    public HoleService(HoleRepository holeRepository) {
        this.holeRepository = holeRepository;
    }

    public Hole createNewHole(Hole hole) {
        return holeRepository.save(hole);
    }
    public Optional<Hole> findHoleById(BigInteger id) {
        return holeRepository.findById(id);
    }

    public List<Hole> findAllHolesByCourse(BigInteger listId) {
        Iterable<Hole> holes = this.holeRepository.findAllById((Iterable<BigInteger>) listId);
        List<Hole> holeList = new ArrayList<>();
        holes.forEach(hole->{holeList.add(hole);});
        List<Hole> sortedList = holeList.stream().sorted().collect(Collectors.toList());
        return sortedList;
    }

    public Hole editHolePar(Hole newHole, BigInteger id) {
        return holeRepository.findById(id)
                .map(hole -> {
                    hole.setHolePar(newHole.getHolePar());
                    return holeRepository.save(hole);
                })
                .orElseGet(() -> {
                    // Fix this yet
                    return newHole;
                });
    }

    public void deleteHole(BigInteger id) {
        holeRepository.deleteById(id);
    }
}

package com.example.golfbook.course.repository;

import com.example.golfbook.course.model.Hole;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface HoleRepository extends CrudRepository<Hole, BigInteger> {
}

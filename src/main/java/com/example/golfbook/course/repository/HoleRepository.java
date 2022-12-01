package com.example.golfbook.course.repository;

import com.example.golfbook.course.model.Hole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface HoleRepository extends CrudRepository<Hole, BigInteger> {
}

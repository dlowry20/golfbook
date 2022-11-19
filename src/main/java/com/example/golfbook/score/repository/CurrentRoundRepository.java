package com.example.golfbook.score.repository;

import com.example.golfbook.score.model.CurrentRound;
import org.springframework.data.repository.CrudRepository;

public interface CurrentRoundRepository extends CrudRepository<CurrentRound, String> {
}

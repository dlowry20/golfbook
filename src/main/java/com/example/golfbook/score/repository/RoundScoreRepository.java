package com.example.golfbook.score.repository;

import com.example.golfbook.score.model.RoundScore;
import com.example.golfbook.score.model.id.RoundScoreId;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface RoundScoreRepository extends CrudRepository<RoundScore, RoundScoreId> {

    List<RoundScore> findAllByCourseNameOrderByDatePlayedDesc(String courseName);

    List<RoundScore> findAllByDatePlayed(LocalDate datePlayed);
}

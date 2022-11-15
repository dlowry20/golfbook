package com.example.golfbook.score.repository;

import com.example.golfbook.score.model.HoleScore;
import com.example.golfbook.score.model.id.HoleScoreId;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

public interface HoleScoreRepository extends CrudRepository<HoleScore, HoleScoreId> {
    List<HoleScore> findAllByCourseIdAndRoundId(BigInteger courseId, UUID roundId);
}

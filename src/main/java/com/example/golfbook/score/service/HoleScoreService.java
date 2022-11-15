package com.example.golfbook.score.service;

import com.example.golfbook.score.dto.HoleScoreDto;
import com.example.golfbook.score.model.HoleScore;
import com.example.golfbook.score.model.RoundScore;
import com.example.golfbook.score.model.id.HoleScoreId;
import com.example.golfbook.score.repository.HoleScoreRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HoleScoreService {

    // TODO : I don't like that we have repository and service in same bean.
    // I think this is a sign we need another class to handle the databases.
    private final HoleScoreRepository holeScoreRepository;
    private final RoundScoreService roundScoreService;

    public HoleScoreService(HoleScoreRepository holeScoreRepository,
                            RoundScoreService roundScoreService
    ) {
        this.holeScoreRepository = holeScoreRepository;
        this.roundScoreService = roundScoreService;
    }

    public Optional<HoleScore> getHoleScoreById(
            BigInteger courseId,
            UUID roundId,
            int holeNumber
    ) {
        return holeScoreRepository.findById(new HoleScoreId(courseId, roundId, holeNumber));
    }

    public List<HoleScore> getAllScoresByRound(BigInteger courseId, UUID roundId) {
        return holeScoreRepository.findAllByCourseIdAndRoundId(courseId, roundId);
    }

    // TODO: Make this action all one transaction.
    public HoleScore insertHoleScore(HoleScoreDto holeScoreDto) {
        HoleScore holeScore = holeScoreRepository.save(
            HoleScore.builder()
                    .courseId(holeScoreDto.getCourseId())
                    .roundId(holeScoreDto.getRoundId())
                    .holeNumber(holeScoreDto.getHoleNumber())
                    .par(holeScoreDto.getPar())
                    .score(holeScoreDto.getScore())
                    .gir(holeScoreDto.isGir())
                    .fairway(holeScoreDto.isFairway())
                    .putts(holeScoreDto.getPutts())
                    .build()
        );
        RoundScore roundScore = roundScoreService.getRoundScoreById(
                holeScore.getCourseId(),
                holeScore.getRoundId()
        ).orElseThrow(() -> new IllegalStateException("Cannot add a score to a round that does not exist"));

        roundScore.setRoundScore(roundScore.getRoundScore() + holeScore.getScore());
        roundScoreService.updateRoundScore(roundScore);
        return holeScore;

    }
}

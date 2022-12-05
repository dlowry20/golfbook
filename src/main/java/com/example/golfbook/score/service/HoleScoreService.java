package com.example.golfbook.score.service;

import com.example.golfbook.score.dto.HoleScoreDto;
import com.example.golfbook.score.model.CurrentRound;
import com.example.golfbook.score.model.HoleScore;
import com.example.golfbook.score.model.RoundScore;
import com.example.golfbook.score.model.id.HoleScoreId;
import com.example.golfbook.score.repository.HoleScoreRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HoleScoreService {

    // TODO : I don't like that we have repository and service in same bean.
    // I think this is a sign we need another class to handle the databases.
    private final HoleScoreRepository holeScoreRepository;
    private final RoundScoreService roundScoreService;
    private final CurrentRoundService currentRoundService;

    public HoleScoreService(HoleScoreRepository holeScoreRepository,
                            RoundScoreService roundScoreService,
                            CurrentRoundService currentRoundService
    ) {
        this.holeScoreRepository = holeScoreRepository;
        this.roundScoreService = roundScoreService;
        this.currentRoundService = currentRoundService;
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

    public List<HoleScore> getAllScoresByRoundId(UUID roundId) {
        return holeScoreRepository.findAllByRoundId(roundId);
    }

    public List<HoleScoreDto> getAllScoresByCurrentRound(String userId) {
        CurrentRound currentRound = currentRoundService.getCurrentRound(userId);
        List<HoleScore> holeScores = holeScoreRepository.findAllByRoundId(currentRound.getRoundId());

        return holeScores.stream()
                .map(HoleScoreDto::new)
                .sorted(Comparator.comparing(HoleScoreDto::getHoleNumber))
                .collect(Collectors.toList());
    }

    // TODO: Make this action all one transaction.
    public HoleScore insertHoleScore(HoleScoreDto holeScoreDto, String userId) {
        CurrentRound currentRound = currentRoundService.getCurrentRound(userId);
        HoleScore holeScore = holeScoreRepository.save(
            HoleScore.builder()
                    .courseId(holeScoreDto.getCourseId())
                    .roundId(currentRound.getRoundId())
                    .holeNumber(holeScoreDto.getHoleNumber())
                    .par(holeScoreDto.getPar())
                    .userId(currentRound.getUserId())
                    .score(holeScoreDto.getScore())
                    .gir(holeScoreDto.isGir())
                    .fairway(holeScoreDto.isFairway())
                    .putts(holeScoreDto.getPutts())
                    .build()
        );
        updateRoundScore(currentRound.getCourseId(), currentRound.getRoundId(), holeScore.getScore());
        updateCurrentRound(currentRound, holeScoreDto.getPar(), holeScore.getScore());
        return holeScore;
    }

    private void updateRoundScore(BigInteger courseId, UUID roundId, int score) {
        RoundScore roundScore = roundScoreService.getRoundScoreById(
                courseId,
                roundId
        ).orElseThrow(() -> new IllegalStateException("Cannot add a score to a round that does not exist"));

        roundScore.setRoundScore(roundScore.getRoundScore() + score);
        roundScoreService.updateRoundScore(roundScore);
    }

    private void updateCurrentRound(CurrentRound currentRound, int par, int score) {
        currentRound.setHolesPlayed(currentRound.getHolesPlayed() + 1);
        currentRound.setRelationToPar(currentRound.getRelationToPar() + (score - par));
        currentRoundService.updateCurrentRound(currentRound);
    }
}

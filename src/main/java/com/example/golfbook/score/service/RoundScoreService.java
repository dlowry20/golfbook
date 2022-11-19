package com.example.golfbook.score.service;

import com.example.golfbook.score.dto.RoundScoreDto;
import com.example.golfbook.score.model.RoundScore;
import com.example.golfbook.score.model.id.RoundScoreId;
import com.example.golfbook.score.repository.RoundScoreRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoundScoreService {

    private final RoundScoreRepository roundScoreRepository;
    private final CurrentRoundService currentRoundService;

    public RoundScoreService(
            RoundScoreRepository roundScoreRepository,
            CurrentRoundService currentRoundService
    ) {
        this.roundScoreRepository = roundScoreRepository;
        this.currentRoundService = currentRoundService;
    }

    public Optional<RoundScore> getRoundScoreById(BigInteger courseId, UUID roundId) {
        return roundScoreRepository.findById(new RoundScoreId(courseId, roundId));
    }

    public RoundScore updateRoundScore(RoundScore roundScore) {
        return roundScoreRepository.save(roundScore);
    }

    public List<RoundScore> getAllRoundsByCourseName(String courseName) {
        return roundScoreRepository.findAllByCourseNameOrderByDatePlayedDesc(courseName);
    }

    public List<RoundScore> getAllRoundsByDatePlayed(LocalDate datePlayed) {
        return roundScoreRepository.findAllByDatePlayed(datePlayed);
    }

    // @Transactional - TODO - this should be transactional.
    public RoundScore insertRoundScore(RoundScoreDto roundScoreDto) {
        RoundScore roundScore = RoundScore.builder()
                .courseId(roundScoreDto.getCourseId())
                .roundScore(roundScoreDto.getScore())
                .roundId(UUID.randomUUID())
                .par(roundScoreDto.getCoursePar())
                .datePlayed(roundScoreDto.getDatePlayed())
                .userId(roundScoreDto.getUserId()) // TODO : I think there should be a way to get the authenticated user
                .par(roundScoreDto.getCoursePar())
                .courseName(roundScoreDto.getCourseName())
                .build();
        currentRoundService.insertCurrentRound(
                roundScore.getUserId(),
                roundScore.getRoundId(),
                roundScore.getCourseId(),
                roundScoreDto.getTournamentId()
        );
        return roundScoreRepository.save(roundScore);
    }
}

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

    public RoundScoreService(RoundScoreRepository roundScoreRepository) {
        this.roundScoreRepository = roundScoreRepository;
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
        return roundScoreRepository.save(roundScore);
    }
}

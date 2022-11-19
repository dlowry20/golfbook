package com.example.golfbook.score.service;

import com.example.golfbook.score.dto.CurrentRoundDto;
import com.example.golfbook.score.model.CurrentRound;
import com.example.golfbook.score.repository.CurrentRoundRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CurrentRoundService {

    private final CurrentRoundRepository currentRoundRepository;

    public CurrentRoundService(CurrentRoundRepository currentRoundRepository) {
        this.currentRoundRepository = currentRoundRepository;
    }

    @Cacheable(value = "currentRoundCache")
    public CurrentRound getCurrentRound(String userId) {
        return currentRoundRepository.findById(userId)
                .orElseThrow(IllegalStateException::new);
    }

    @Cacheable(value = "currentRoundCache")
    public List<CurrentRound> listByTournamentId(int tournamentId) {
        return currentRoundRepository.findAllByTournamentIdOrderByRelationToParAsc(tournamentId);
    }

    @Cacheable(value = "currentRoundCache")
    public List<CurrentRoundDto> getLeaderboard(int tournamentId) {
        List<CurrentRound> currentRounds = currentRoundRepository.findAllByTournamentIdOrderByRelationToParAsc(tournamentId);
        return currentRounds.stream()
                .map(CurrentRoundDto::new)
                .collect(Collectors.toList());
    }

    public CurrentRound insertCurrentRound(
            String userId,
            UUID roundId,
            BigInteger courseId,
            int tournamentId
    ) {
        CurrentRound currentRound = new CurrentRound(userId, roundId, courseId, 0, 0, tournamentId);
        return currentRoundRepository.save(currentRound);
    }

    public CurrentRound insertCurrentRound(String userId, UUID roundId, BigInteger courseId) {
        CurrentRound currentRound = new CurrentRound(userId, roundId, courseId, 0, 0, 0);
        return currentRoundRepository.save(currentRound);
    }

    public CurrentRound updateCurrentRound(CurrentRound currentRound) {
        return currentRoundRepository.save(currentRound);
    }
}

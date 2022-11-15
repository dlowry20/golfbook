package com.example.golfbook.score.service;

import com.example.golfbook.score.dto.Hole;
import com.example.golfbook.score.dto.Round;
import com.example.golfbook.score.model.HoleScore;
import com.example.golfbook.score.model.RoundScore;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoundService {

    private final RoundScoreService roundScoreService;
    private final HoleScoreService holeScoreService;

    public RoundService(RoundScoreService roundScoreService, HoleScoreService holeScoreService) {
        this.roundScoreService = roundScoreService;
        this.holeScoreService = holeScoreService;
    }

    public List<Round> getAllRoundsAtCourse(String courseName) {
        List<RoundScore> roundScores = roundScoreService.getAllRoundsByCourseName(courseName);
        Map<UUID, List<HoleScore>> holeScoresByRound = groupHolesByRound(roundScores);

        return convertToRounds(roundScores, holeScoresByRound);
    }

    public List<Round> getAllRoundsByDate(LocalDate datePlayed) {
        List<RoundScore> roundScores = roundScoreService.getAllRoundsByDatePlayed(datePlayed);
        Map<UUID, List<HoleScore>> holeScoresByRound = groupHolesByRound(roundScores);

        return convertToRounds(roundScores, holeScoresByRound);
    }

    private Map<UUID, List<HoleScore>> groupHolesByRound(List<RoundScore> roundScores) {
        return roundScores.stream()
                .map(it -> holeScoreService.getAllScoresByRound(it.getCourseId(), it.getRoundId()))
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(HoleScore::getRoundId));
    }

    private List<Round> convertToRounds(
            List<RoundScore> roundScores,
            Map<UUID, List<HoleScore>> holeScoresByRound
    ) {
        List<Round> rounds = new ArrayList<>();
        for (RoundScore roundScore : roundScores) {
            List<HoleScore> holeScores = holeScoresByRound.get(roundScore.getRoundId());
            List<Hole> holes = holeScores.stream()
                    .map(Hole::new)
                    .collect(Collectors.toList());
            Round round = Round.builder()
                    .courseName(roundScore.getCourseName())
                    .datePlayed(roundScore.getDatePlayed())
                    .coursePar(roundScore.getPar())
                    .holesPlayed(holeScores.size())
                    .holes(holes)
                    .build();
            rounds.add(round);
        }
        return rounds;
    }
}

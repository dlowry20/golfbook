package com.example.golfbook.score.repository;

import com.example.golfbook.score.model.CurrentRound;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CurrentRoundRepository extends CrudRepository<CurrentRound, String> {

    List<CurrentRound> findAllByTournamentIdOrderByRelationToParAsc(int tournamentId);

    List<CurrentRound> findAllByOrderByRelationToParAsc();

}

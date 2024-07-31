package com.prtech.game_library_hb.controller;

import com.prtech.game_library_hb.model.Match;
import com.prtech.game_library_hb.model.MatchDTO;
import com.prtech.game_library_hb.model.TeamDTO;
import com.prtech.game_library_hb.repository.MatchRepository;
import com.prtech.game_library_hb.repository.PlayerRepository;
import com.prtech.game_library_hb.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class MatchController {

    @Autowired
    private MatchRepository matchTeamRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;




    @GetMapping("/matches")
    List<MatchDTO> readAllMatches() {
        return matchTeamRepository.findAll().stream().map(matchTeam ->
                new MatchDTO(
                        matchTeam.getId(),
                        new TeamDTO(matchTeam.getHomeTeam().getName()),
                        new TeamDTO(matchTeam.getAwayTeam().getName()),
                        matchTeam.getHomeTeamGoals(),
                        matchTeam.getAwayTeamGoals(),
                        matchTeam.getResult(),
                        matchTeam.getHomeTeamPenaltyGoals(),
                        matchTeam.getAwayTeamPenaltyGoals()))
                .collect(Collectors.toList());
    }

    @GetMapping("/matches/{id}")
    ResponseEntity<Match> readMatch(@PathVariable Long id) {
        log.info("Match with id: " + id);
        return matchTeamRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/matches")
    public Match createMatch(@RequestBody Match match) {
        log.info(String.valueOf(match.getId()));
        log.info(match.toString());
        return matchTeamRepository.save(match);
    }

    @PutMapping("/matches/{id}")
    ResponseEntity<?> updateMatch(@PathVariable Long id, @RequestBody Match match) {
        Match updateMatch = matchTeamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("match not exist with id: " + id));

//        if (match.getOpponent() != null) {
//            updateMatch.setOpponent(match.getOpponent());
//        }
//        if (match.getScore() != null) {
//            updateMatch.setScore(match.getScore());
//        }
//        if(match.getId() != null) {
//            updateMatch.setId(id); // Preventing id overriding when updating.
//        }

        matchTeamRepository.save(updateMatch);

        return ResponseEntity.ok(updateMatch);
    }

    @DeleteMapping("/matches/{id}")
    ResponseEntity<?> deleteMatch(@PathVariable Long id) {
        matchTeamRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/matches/deleteAll")
    ResponseEntity<?> deleteAllMatches() {
        matchTeamRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}

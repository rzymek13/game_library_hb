package com.prtech.game_library_hb.controller;

import com.prtech.game_library_hb.model.*;

import com.prtech.game_library_hb.model.dto.CreateMatchDTO;
import com.prtech.game_library_hb.model.dto.TeamDTO;
import com.prtech.game_library_hb.model.dto.TeamNameDTO;
import com.prtech.game_library_hb.repository.MatchPlayerRepository;
import com.prtech.game_library_hb.repository.MatchRepository;
import com.prtech.game_library_hb.repository.PlayerRepository;
import com.prtech.game_library_hb.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Slf4j
class MatchController {

    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MatchPlayerRepository matchPlayerRepository;


//    @GetMapping("/matches")
//    ResponseEntity<List<MatchDTO>> readAllMatches() {
//        return matchRepository.findAll()
//                .stream().map(matchTeam ->
//                new MatchDTO(
//                        matchTeam.getId(),
//                        new TeamDTO(matchTeam.getHomeTeam().getName()),
//                        new TeamDTO(matchTeam.getAwayTeam().getName()),
//                        matchTeam.getHomeTeamGoals(),
//                        matchTeam.getAwayTeamGoals(),
//                        matchTeam.getResult(),
//                        matchTeam.getHomeTeamPenaltyGoals(),
//                        matchTeam.getAwayTeamPenaltyGoals(),
//                        matchTeam.getMatchPlayerList()))
//                .collect(Collectors.toList());

//    }

    @GetMapping("/matches/{id}")
    ResponseEntity<Match> readMatch(@PathVariable Long id) {
        log.info("Match with id: " + id);
        return matchRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

@PostMapping("/matches")
    public CreateMatchDTO createMatch(@RequestBody CreateMatchDTO matchDTO) {

    Match match = new Match();

    Team homeTeam = teamRepository.findById(matchDTO.homeTeam().id()).get();
    Team awayTeam = teamRepository.findById(matchDTO.awayTeam().id()).get();


        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
        match.setHomeTeamGoals(matchDTO.homeTeamGoals());
        match.setAwayTeamGoals(matchDTO.awayTeamGoals());
        match.setResult(matchDTO.result());

//    List<MatchPlayer> matchPlayers = matchDTO.createMatchPlayers().stream()
//            .map(matchPlayer -> {
//                var list = match.getMatchPlayerList();
//                list.add(matchPlayer);
//                return matchPlayer;
//            })
//            .collect(Collectors.toList());
//
//    match.setMatchPlayerList(matchPlayers);


    matchRepository.save(match);
    log.info(match.getId().toString());
    return matchDTO;
}



    @PutMapping("/matches/{id}")
    ResponseEntity<?> updateMatch(@PathVariable Long id, @RequestBody Match match) {
        Match updateMatch = matchRepository.findById(id)
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

        matchRepository.save(updateMatch);

        return ResponseEntity.ok(updateMatch);
    }

    @DeleteMapping("/matches/{id}")
    ResponseEntity<?> deleteMatch(@PathVariable Long id) {
        matchRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/matches/deleteAll")
    ResponseEntity<?> deleteAllMatches() {
        matchRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}

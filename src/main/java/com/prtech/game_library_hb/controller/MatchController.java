package com.prtech.game_library_hb.controller;

import com.prtech.game_library_hb.model.*;

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




    @GetMapping("/matches")
    List<MatchDTO> readAllMatches() {
        return matchRepository.findAll().stream().map(matchTeam ->
                new MatchDTO(
                        matchTeam.getId(),
                        new TeamDTO(matchTeam.getHomeTeam().getName()),
                        new TeamDTO(matchTeam.getAwayTeam().getName()),
                        matchTeam.getHomeTeamGoals(),
                        matchTeam.getAwayTeamGoals(),
                        matchTeam.getResult(),
                        matchTeam.getHomeTeamPenaltyGoals(),
                        matchTeam.getAwayTeamPenaltyGoals(),
                        matchTeam.getMatchPlayerList()))
                .collect(Collectors.toList());
    }

    @GetMapping("/matches/{id}")
    ResponseEntity<Match> readMatch(@PathVariable Long id) {
        log.info("Match with id: " + id);
        return matchRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/matches")
    public Match createMatch(@RequestBody Match match) {
        //zapisuje drużyny jako nowe

        List<MatchPlayer> matchPlayers = match.getMatchPlayerList();

        for (MatchPlayer matchPlayer : matchPlayers) {

//            Team homeTeam = match.getHomeTeam();
//            Team awayTeam = match.getAwayTeam();
//            if (homeTeam == null || awayTeam == null) {
//                throw new RuntimeException("Invalid team IDs");
//            }
//            Player player = playerRepository.findById(matchPlayer.getPlayer().getId()).orElse(null);
//            if (player == null) {
//                throw new RuntimeException("Invalid player ID");
//            }
//
//            match.getMatchPlayerList().get(0).setPlayer(player);
//            match.setHomeTeam(homeTeam);
//            match.setAwayTeam(awayTeam);


            matchPlayerRepository.save(match.getMatchPlayerList().getFirst());
        }
        return matchRepository.save(match);
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

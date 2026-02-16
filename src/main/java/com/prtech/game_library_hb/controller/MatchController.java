package com.prtech.game_library_hb.controller;

import com.prtech.game_library_hb.controller.dto.MatchDto;
import com.prtech.game_library_hb.controller.dto.MatchPlayerDto;
import com.prtech.game_library_hb.service.MatchPlayerService;
import com.prtech.game_library_hb.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class MatchController {

    private MatchService matchService;
    private MatchPlayerService matchPlayerService;

    public MatchController(MatchService matchService, MatchPlayerService matchPlayerService) {
        this.matchService = matchService;
        this.matchPlayerService = matchPlayerService;
    }


    @GetMapping("/handball/matches")
    List<MatchDto> readAllMatches() {
        return matchService.readAllMatches();
    }

    @GetMapping("/handball/match_players")
    public List<MatchPlayerDto> getMatchPlayers() {
        return matchPlayerService.findAll();
    }

    @GetMapping("handball/scorers")
    public List<MatchPlayerDto> getScorers() {
        return matchPlayerService.getAllScorers();
    }

    @PostMapping("/handball/matches")
    public MatchDto createMatch(@RequestBody MatchDto matchDTO) {
        return matchService.saveMatch(matchDTO);
    }

    @DeleteMapping("/handball/matches/{id}")
    public void deleteMatch(@PathVariable Long id) {
        matchService.deleteMatchById(id);
    }

    //
//    @PutMapping("/handball/matches/{id}")
//    ResponseEntity<?> updateMatch(@PathVariable Long id, @RequestBody Match match) {
//        Match updateMatch = matchRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("match not exist with id: " + id));
//
////        if (match.getOpponent() != null) {
////            updateMatch.setOpponent(match.getOpponent());
////        }
////        if (match.getScore() != null) {
////            updateMatch.setScore(match.getScore());
////        }
////        if(match.getId() != null) {
////            updateMatch.setId(id); // Preventing id overriding when updating.
////        }
//
//        matchRepository.save(updateMatch);
//
//        return ResponseEntity.ok(updateMatch);
//    }
//
//    @DeleteMapping("/matches/deleteAll")
//    ResponseEntity<?> deleteAllMatches() {
//        matchService.deleteAllMatches();
//        return ResponseEntity.noContent().build();
//    }
}

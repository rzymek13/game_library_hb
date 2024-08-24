package com.prtech.game_library_hb.controller;

import com.prtech.game_library_hb.controller.dto.MatchDto;
import com.prtech.game_library_hb.model.MatchPlayer;
import com.prtech.game_library_hb.service.MatchPlayerService;
import com.prtech.game_library_hb.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
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
    @GetMapping("/match_players")
    public List<MatchPlayer> getMatchPlayers() {
        return matchPlayerService.findAll();
    }


//    @GetMapping("/handball/matches/{id}")
//    MatchDto readMatch(@PathVariable Long id) {
//        log.info("Match with id: " + id);
//        return mapMatchToDto(matchService.getMatchById(id));
//    }

    @PostMapping("/handball/matches")
    public MatchDto createMatch(@RequestBody MatchDto matchDTO) {
        return matchService.saveMatch(matchDTO);
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

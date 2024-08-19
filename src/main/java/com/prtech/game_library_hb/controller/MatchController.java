package com.prtech.game_library_hb.controller;

import com.prtech.game_library_hb.controller.dto.MatchDto;
import com.prtech.game_library_hb.controller.dto.ReadMatchDto;
import com.prtech.game_library_hb.model.*;

import com.prtech.game_library_hb.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.prtech.game_library_hb.controller.dto.MatchMapper.mapMatchToDto;

@RestController
@Slf4j
class MatchController {

    private MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }


    @GetMapping("/matches")
    List<ReadMatchDto> readAllMatches() {
        return matchService.getAllMatches();
    }


//    @GetMapping("/matches/{id}")
//    MatchDto readMatch(@PathVariable Long id) {
//        log.info("Match with id: " + id);
//        return mapMatchToDto(matchService.getMatchById(id));
//    }

    @PostMapping("/matches")
    public Match createMatch(@RequestBody MatchDto matchDTO) {
        return matchService.saveMatch(matchDTO);
    }


    //
//    @PutMapping("/matches/{id}")
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
    @DeleteMapping("/matches/deleteAll")
    ResponseEntity<?> deleteAllMatches() {
        matchService.deleteAllMatches();
        return ResponseEntity.noContent().build();
    }
}

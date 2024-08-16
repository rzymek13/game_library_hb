package com.prtech.game_library_hb.controller;

import com.prtech.game_library_hb.controller.dto.MatchDto;
import com.prtech.game_library_hb.model.*;

import com.prtech.game_library_hb.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.prtech.game_library_hb.controller.dto.MatchMapper.mapMatchToDto;

@RestController
@Slf4j
class MatchController {

    private MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }
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
//    @DeleteMapping("/matches/{id}")
//    ResponseEntity<?> deleteMatch(@PathVariable Long id) {
//        matchRepository.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @DeleteMapping("/matches/deleteAll")
//    ResponseEntity<?> deleteAllMatches() {
//        matchRepository.deleteAll();
//        return ResponseEntity.noContent().build();
//    }
}

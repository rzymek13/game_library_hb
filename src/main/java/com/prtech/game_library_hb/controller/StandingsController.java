package com.prtech.game_library_hb.controller;

import com.prtech.game_library_hb.model.Standings;
import com.prtech.game_library_hb.service.StandingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
public class StandingsController {
    private StandingsService standingsService;

    public StandingsController(StandingsService standingsService) {
        this.standingsService = standingsService;
    }

    @GetMapping("/handball/standings")
    public List<Standings> readAllStandings() {
        log.info("All the standings");
        return standingsService.createStandings();
    }
    @PostMapping("/handball/standings/generate")
    public List<Standings> generateStandings() {
        log.info("Generating a new standings");
        return standingsService.generateStandings();
    }

    @PatchMapping("/handball/standings/reset")
    public List<Standings> resetStandings() {
        log.info("Creating a new standings");
        return standingsService.clearStandings();
    }
    @DeleteMapping("/handball/standings")
    public void deleteAllStandings() {
        log.info("Deleting all the standings");
        standingsService.deleteAllStandings();
    }
    @DeleteMapping("/handball/standings/{id}")
    public void deteteStandingsById(@PathVariable Long id) {
        /*
        nie dziala usuwanie standings, przez drop table dziala



         */
        standingsService.delteById(id);
    }

}

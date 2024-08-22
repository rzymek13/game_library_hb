package com.prtech.game_library_hb.controller;

import com.prtech.game_library_hb.model.Standings;
import com.prtech.game_library_hb.service.StandingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class StandingsController {
    private StandingsService standingsService;

    public StandingsController(StandingsService standingsService) {
        this.standingsService = standingsService;
    }
    @GetMapping("/standings")
    public List<Standings> readAllStandings() {
        log.info("All the standings");
        return standingsService.findAllStandings();
    }

    @PostMapping("/standings")
    public List<Standings> createStandings() {
        log.info("Creating a new standings");
        return standingsService.saveStandings();
    }
    @DeleteMapping("/standings")
    public void deleteAllStandings() {
        log.info("Deleting all the standings");
        standingsService.deleteAllStandings();
    }
    @DeleteMapping("/standings/{id}")
    public void deteteStandingsById(@PathVariable Long id) {
        /*
        nie dziala usuwanie standings, przez drop table dziala



         */
        standingsService.delteById(id);
    }

}

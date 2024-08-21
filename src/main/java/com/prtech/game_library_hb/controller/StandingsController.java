package com.prtech.game_library_hb.controller;

import com.prtech.game_library_hb.model.Standings;
import com.prtech.game_library_hb.service.StandingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
        standingsService.deleteAllStandings();
        log.info("All the standings");
        return standingsService.findAllStandings();
    }

    @PostMapping("/standings")
    public List<Standings> createStandings() {
        log.info("Creating a new standings");
        return standingsService.saveStandings();
    }
}

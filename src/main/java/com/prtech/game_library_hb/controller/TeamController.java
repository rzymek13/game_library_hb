package com.prtech.game_library_hb.controller;

import com.prtech.game_library_hb.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RepositoryRestController
@Slf4j
public class TeamController {
    private final TeamRepository teamRepository;

    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping(value = "/teams", params = {"!sort", "!page", "!size"})
    ResponseEntity<?> readAllTeams() {
        log.info("All the teams");
        return ResponseEntity.ok(teamRepository.findAll());
    }
}

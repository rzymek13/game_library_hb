package com.prtech.game_library_hb.player.controller;

import com.prtech.game_library_hb.player.repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@Slf4j
@RestController
class PlayerController {
    private final PlayerRepository repository;

    public PlayerController(final PlayerRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/players", params = {"!sort", "!page", "!size"})
    ResponseEntity<?> readAllPlayers() {
        log.info("All the teams");
        return ResponseEntity.ok(repository.findAll());
    }


}

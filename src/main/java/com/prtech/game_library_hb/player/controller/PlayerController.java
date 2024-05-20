package com.prtech.game_library_hb.player.controller;

import com.prtech.game_library_hb.player.model.Player;
import com.prtech.game_library_hb.player.repository.PlayerRepository;
import com.prtech.game_library_hb.team.model.Team;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;


@Slf4j
@RestController
class PlayerController {
    private final PlayerRepository repository;

    public PlayerController(final PlayerRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/players", params = {"!sort", "!page", "!size"})
    ResponseEntity<?> readAllPlayers() {
        log.info("All the players");
        return ResponseEntity.ok(repository.findAll());
    }
    @PostMapping("/players")
    ResponseEntity<Player> createPlayer(@RequestBody Player toCreate) {
        Player player = repository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + player.getPlayerId())).body(player);
    }

}

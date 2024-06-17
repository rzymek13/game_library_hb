package com.prtech.game_library_hb.player.controller;

import com.prtech.game_library_hb.player.model.Player;
import com.prtech.game_library_hb.player.repository.PlayerRepository;
import com.prtech.game_library_hb.team.model.Team;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/players/{id}")
    ResponseEntity<Player> readPlayer(@PathVariable int id) {
        log.info("Player with id: " + id);
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/players")
    ResponseEntity<Player> createPlayer(@PathVariable int teamId, @RequestBody Player toCreate) {
        Player player = repository.save(toCreate);
        player.setTeam(repository.findById(teamId).get().getTeam());
        return ResponseEntity.created(URI.create("/" + player.getPlayerId())).body(player);
    }
//    ResponseEntity<Player> allAllPlayer()

    @DeleteMapping("/players/{id}")
    ResponseEntity<?> deletePlayer(@PathVariable int id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("players/deleteAll")
    ResponseEntity<?> deleteAllPlayers() {
        repository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}

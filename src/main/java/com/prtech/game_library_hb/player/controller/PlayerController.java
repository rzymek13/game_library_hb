package com.prtech.game_library_hb.player.controller;

import com.prtech.game_library_hb.player.model.Player;
import com.prtech.game_library_hb.player.model.PlayerDTO;
import com.prtech.game_library_hb.player.repository.PlayerRepository;
import com.prtech.game_library_hb.team.model.TeamNameDTO;

import com.prtech.game_library_hb.team.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;


    @GetMapping(value = "/players", params = {"!sort", "!page", "!size"})
    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.findAll().stream().map(player ->
                new PlayerDTO(
                        player.getId(),
                        player.getName(),
                        player.getMatchesPlayed(),
                        player.getGoalsScored(),
                        new TeamNameDTO(player.getTeam().getName())
                )
        ).collect(Collectors.toList());
    }

    @GetMapping("/players/{id}")
    ResponseEntity<Player> readPlayer(@PathVariable Long id) {
        log.info("Player with id: " + id);
        return playerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/players/{teamId}")
    public Player createPlayer(@PathVariable Long teamId, @RequestBody Player player) {
        return teamRepository.findById(teamId).map(team -> {
            player.setTeam(team);
            return playerRepository.save(player);
        }).orElseThrow(() -> new RuntimeException("Team not found"));
    }


    @DeleteMapping("/players/{id}")
    ResponseEntity<?> deletePlayer(@PathVariable Long id) {
        playerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("players/deleteAll")
    ResponseEntity<?> deleteAllPlayers() {
        playerRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}

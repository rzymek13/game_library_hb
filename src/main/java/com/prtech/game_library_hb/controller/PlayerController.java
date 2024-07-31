package com.prtech.game_library_hb.controller;

import com.prtech.game_library_hb.model.Player;
import com.prtech.game_library_hb.model.PlayerDTO;
import com.prtech.game_library_hb.repository.PlayerRepository;
import com.prtech.game_library_hb.model.Team;
import com.prtech.game_library_hb.model.TeamDTO;

import com.prtech.game_library_hb.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
                        new TeamDTO(player.getTeam().getName())
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
    @GetMapping("/playersTeam/{id}")
    ResponseEntity<List<Player>> readPlayersTeam(@PathVariable Long id) {
        log.info("Players in team with id: " + id);
        List<Player> playersInTeam = playerRepository.findAll().stream()
                .filter(player -> player.getTeam().getId().equals(id))
                .collect(Collectors.toList());
        if (playersInTeam.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(playersInTeam);
    }

    @PostMapping("/players/{teamId}")
    public Player createPlayer(@PathVariable Long teamId, @RequestBody Player player) {
        return teamRepository.findById(teamId).map(team -> {
            player.setTeam(team);
            return playerRepository.save(player);
        }).orElseThrow(() -> new RuntimeException("Team not found"));
    }
    @PostMapping("/players/addAll/{teamId}")
    public List<Player> addAllPlayers(@PathVariable Long teamId, @RequestBody List<Player> listOfPlayers) {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isPresent()) {
            return listOfPlayers.stream()
                    .map(player -> {
                        player.setTeam(team.get());
                        return playerRepository.save(player);
                    })
                    .collect(Collectors.toList());
        } else throw new RuntimeException("Team not found");
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
    @DeleteMapping("players/delete/{teamId}")
    ResponseEntity<?> deletePlayersByTeamId(@PathVariable Long teamId) {
//        if (playerRepository.)
        playerRepository.findAll().stream()
                .filter(player -> player.getTeam().getId().equals(teamId))
                .forEach(player -> playerRepository.deleteById(player.getId()));
        log.info("Deleted players");
        return ResponseEntity.ok().build();
    }


}

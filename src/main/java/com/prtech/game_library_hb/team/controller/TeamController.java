package com.prtech.game_library_hb.team.controller;

import com.prtech.game_library_hb.exceptions.ResourceNotFoundException;
import com.prtech.game_library_hb.player.model.Player;
import com.prtech.game_library_hb.team.model.Team;
import com.prtech.game_library_hb.team.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@Slf4j
public class TeamController {
    private final TeamRepository repository;

    public TeamController(final TeamRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/teams", params = {"!sort", "!page", "!size"})
    ResponseEntity<?> readAllTeams() {
        log.info("All the teams");
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/teams/{id}")
    ResponseEntity<Team> readTeam(@PathVariable int id) {
        log.info("Team with id: " + id);
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/teams")
    public Team createTeam(@RequestBody Team team) {
        log.info(String.valueOf(team.getTeamId()));
//        if (team.getPlayerList() != null) {
//            for (Player player : team.getPlayerList()) {
//                player.setTeam(team);
//            }
//        }
        return repository.save(team);
    }


    @PutMapping("/teams/{id}")
    ResponseEntity<?> updateTeam(@PathVariable int id, @RequestBody Team team) {
        Team updateTeam = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("team not exist with id: " + id));

        if (team.getName() != null) {
            updateTeam.setName(team.getName());
        }
        if (team.getPoints() != null) {
            updateTeam.setPoints(team.getPoints());
        }
        if (team.getMatches() != null) {
            updateTeam.setMatches(team.getMatches());
        }

        repository.save(updateTeam);

        return ResponseEntity.ok(updateTeam);
    }

    @DeleteMapping("/teams/{id}")
    ResponseEntity<?> deleteTeam(@PathVariable int id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/teams/deleteAll")
    ResponseEntity<?> deleteAllTeams() {
        repository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}

package com.prtech.game_library_hb.controller;

import com.prtech.game_library_hb.model.Team;
import com.prtech.game_library_hb.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@Transactional
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
    ResponseEntity<Team> readTeam(@PathVariable Long id) {
        log.info("Team with id: " + id);
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/teams")
    public Team createTeam(@RequestBody Team team) {
        log.info(String.valueOf(team.getId()));
        return repository.save(team);
    }


    @PutMapping("/teams/{id}")
    ResponseEntity<?> updateTeam(@PathVariable Long id, @RequestBody Team team) {
        Team updateTeam = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("team not exist with id: " + id));

        if (team.getName() != null) {
            updateTeam.setName(team.getName());


            repository.save(updateTeam);
        }
        return ResponseEntity.ok(updateTeam);

    }

    @DeleteMapping("/teams/{id}")
    ResponseEntity<?> deleteTeam(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/teams/deleteAll")
    ResponseEntity<?> deleteAllTeams() {
        repository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/teams/deleteAllTestTeams")
    ResponseEntity<?> deleteAllTestTeams() {
        List<Team> teamsToDelete = repository.findAll().stream()
                .filter(team -> team.getId() > 8)
                .toList();

        teamsToDelete.forEach(team -> repository.deleteById(team.getId()));

        return ResponseEntity.noContent().build();
    }

}
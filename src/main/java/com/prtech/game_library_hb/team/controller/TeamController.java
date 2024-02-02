package com.prtech.game_library_hb.team.controller;

import com.prtech.game_library_hb.exceptions.ResourceNotFoundException;
import com.prtech.game_library_hb.team.model.Team;
import com.prtech.game_library_hb.team.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


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
    ResponseEntity<Team> createTeam(@RequestBody Team toCreate) {
        Team result = repository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PutMapping("/teams/{id}")
    ResponseEntity<?> updateTeam(@PathVariable int id, @RequestBody Team team) {
        Team updateTeam = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        updateTeam.setName(team.getName());
        updateTeam.setPoints(team.getPoints());
        updateTeam.setMatches(team.getMatches());
        updateTeam.setGoalsScored(team.getGoalsScored());
        updateTeam.setGoalsConceded(team.getGoalsConceded());

        repository.save(updateTeam);

        return ResponseEntity.ok(updateTeam);
    }
}

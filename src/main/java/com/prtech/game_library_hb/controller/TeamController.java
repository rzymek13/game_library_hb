package com.prtech.game_library_hb.controller;

import com.prtech.game_library_hb.controller.dto.TeamNameDto;
import com.prtech.game_library_hb.model.Team;
import com.prtech.game_library_hb.service.TeamService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.prtech.game_library_hb.controller.dto.TeamMapper.mapDtoToTeam;


@RestController
@Slf4j
@CrossOrigin
public class TeamController {

    @Autowired private final TeamService teamService;

    public TeamController(final TeamService teamService) {
        this.teamService = teamService;
    }


    @GetMapping(value = "/handball/teams")
    ResponseEntity<List<Team>> readAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    @GetMapping("/handball/teams/{id}")
    ResponseEntity<Team> readTeam(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getTeamById(id));

    }

    @PostMapping("/handball/teams")
    ResponseEntity<Team> createTeam(@RequestBody @Valid TeamNameDto teamNameDto) {
        Team savedTeam = teamService.saveTeam(mapDtoToTeam(teamNameDto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTeam.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedTeam);
    }

    @PutMapping("/handball/teams/{id}")
    public ResponseEntity<Void> updateTeamName(@PathVariable Long id, @RequestBody @Valid TeamNameDto string) {
        Team team = new Team();
        team.setId(id);
        team.setName(string.name());
        teamService.saveTeam(team);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/handball/teams/{id}")
    ResponseEntity<?> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeamById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/handball/teams/deleteAll")
    ResponseEntity<?> deleteAllTeams() {
        teamService.deleteAllTeams();
        return ResponseEntity.noContent().build();
    }
}

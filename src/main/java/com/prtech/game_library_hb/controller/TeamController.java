package com.prtech.game_library_hb.controller;

import com.prtech.game_library_hb.model.Team;
import com.prtech.game_library_hb.model.dto.*;
import com.prtech.game_library_hb.service.TeamService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.prtech.game_library_hb.model.dto.TeamMapper.mapDtoToTeam;


@RestController
@Slf4j
@Transactional
public class TeamController {
    private final TeamService teamService;

    public TeamController(final TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping(value = "/teams")
    List<Team> readAllTeams() {
        log.info("All the teams");
        return teamService.getAllTeams();
    }

    @GetMapping("/teams/{id}")
    Team readTeam(@PathVariable Long id) {
        log.info("Team with id: " + id);
        return teamService.getTeamById(id);

    }

    @PostMapping("/teams")
    Team createTeam(@RequestBody @Valid TeamNameDTO teamNameDTO) {
        return teamService.saveTeam(mapDtoToTeam(teamNameDTO));
    }

    @PutMapping("/teams/{id}")
    public void updateTeamName(@PathVariable Long id, @RequestBody @Valid TeamNameDTO teamNameDTO) {
        Team team = new Team();
        team.setId(id);
        team.setName(teamNameDTO.name());
        teamService.saveTeam(team);
    }

    @DeleteMapping("/teams/{id}")
    ResponseEntity<?> deleteTeam(@PathVariable Long id) {
        log.info("Team with id: " + id + " deleted");
        teamService.deleteTeamById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/teams/deleteAll")
    ResponseEntity<?> deleteAllTeams() {
        teamService.deleteAllTeams();
        return ResponseEntity.noContent().build();
    }
}

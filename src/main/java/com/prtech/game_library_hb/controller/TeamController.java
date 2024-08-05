package com.prtech.game_library_hb.controller;

import com.prtech.game_library_hb.exceptions.ResourceNotFoundException;
import com.prtech.game_library_hb.model.Team;
import com.prtech.game_library_hb.model.dto.PlayerNameDTO;
import com.prtech.game_library_hb.model.dto.TeamDTO;
import com.prtech.game_library_hb.model.dto.TeamNameDTO;
import com.prtech.game_library_hb.model.dto.TeamWithPlayersDTO;
import com.prtech.game_library_hb.repository.TeamRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@Slf4j
@Transactional
public class TeamController {
    private final TeamRepository repository;

    public TeamController(final TeamRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/teams", params = {"!sort", "!page", "!size"})
    List<TeamDTO> readAllTeams() {
        log.info("All the teams");
        return repository.findAll().stream().map(team -> 
                new TeamDTO(team.getId(), team.getName())).collect(Collectors.toList());
    }

    @GetMapping("/teams/{id}")
    TeamDTO readTeam(@PathVariable Long id) {
        log.info("Team with id: " + id);
        return repository.findById(id).stream().map(team -> 
                new TeamDTO(team.getId(), team.getName())).findFirst().orElseThrow(() -> new RuntimeException("Team not found"));
    }
    @GetMapping("/teams_with_players/{id}")
    TeamWithPlayersDTO readTeamWithPlayersById(@PathVariable Long id) {
        return repository.findById(id).stream().map(team -> 
                new TeamWithPlayersDTO(
                        team.getId(),
                        team.getName(),
                        team.getPlayers().stream().map(player ->
                                new PlayerNameDTO(player.getName())).collect(Collectors.toList())))
                .findFirst().orElseThrow(() -> new RuntimeException(""));
    }

    @PostMapping("/teams")
    Team createTeam(@RequestBody @Valid TeamNameDTO teamNameDTO) {
        Team team = new Team();
        team.setName(teamNameDTO.name());
        log.info(String.valueOf(team.getId()));
        return repository.save(team);
    }


    @PutMapping("/teams/{id}")
    public void updateTeamName(@PathVariable Long id, @RequestBody @Valid TeamNameDTO teamNameDTO) {
        Team team = new Team();
        team.setId(id);
        team.setName(teamNameDTO.name());
        repository.save(team);
    }

    @DeleteMapping("/teams/{id}")
    ResponseEntity<?> deleteTeam(@PathVariable Long id) {
        log.info("Team with id: " + id + " deleted");
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

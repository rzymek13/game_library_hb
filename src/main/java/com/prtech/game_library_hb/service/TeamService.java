package com.prtech.game_library_hb.service;

import com.prtech.game_library_hb.model.Team;
import com.prtech.game_library_hb.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams() {
        log.info("Fetching all teams");
        List<Team> teams = teamRepository.findAll();
        log.info("All teams: {}", teams);
        return teams;
    }

    public Team getTeamById(Long id) {
        log.info("Fetching team by ID: {}", id);
        return teamRepository.findAll().stream()
                .filter(team -> team.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> {
                    log.warn("Team with ID: {} not found", id);
                    return new RuntimeException("Team not found with id: " + id);
                });
    }

    public Team getTeamByName(String name) {
        log.info("Fetching team by name: {}", name);
        return teamRepository.findAll().stream()
                .filter(team -> team.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> {
                    log.warn("Team with name: '{}' not found", name);
                    return new RuntimeException("Team not found with name: " + name);
                });
    }

    public Team saveTeam(Team team) {
        if (team.getId() == null) {
            log.info("Creating new team with name: {}", team.getName());
        } else {
            log.info("Saving team with ID: {}", team.getId());
        }
        Team savedTeam = teamRepository.save(team);
        log.info("Saved team details: {}", savedTeam);
        return savedTeam;
    }

    public void deleteTeamById(Long id) {
        log.info("Deleting team with ID: {}", id);
        if (getTeamById(id) != null) {
            log.warn("Attempted to delete non-existent team with ID: {}", id);
        }
        teamRepository.deleteById(id);
        log.info("Successfully deleted team with ID: {}", id);
    }

    public void deleteAllTeams() {
        log.warn("Deleting all teams from the database");
        long count = getAllTeams().size();
        teamRepository.deleteAll();
        log.info("Successfully deleted {} teams", count);
    }
}

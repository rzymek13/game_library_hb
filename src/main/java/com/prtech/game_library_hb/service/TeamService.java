package com.prtech.game_library_hb.service;

import com.prtech.game_library_hb.model.Team;
import com.prtech.game_library_hb.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
    public Team getTeamById(Long id) {
        return teamRepository.findAll().stream().filter(team -> team.getId().equals(id)).findFirst().get();
    }
    public Team getTeamByName(String name) {
        return teamRepository.findAll().stream()
                .filter(team -> team.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("HomeTeam nie może być nullem"));
    }
    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }
    public void deleteTeamById(Long id) {
    teamRepository.deleteById(id);
    }
    public void deleteAllTeams() {
        teamRepository.deleteAll();
    }


}

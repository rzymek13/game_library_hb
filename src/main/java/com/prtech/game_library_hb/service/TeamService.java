package com.prtech.game_library_hb.service;

import com.prtech.game_library_hb.model.Team;
import com.prtech.game_library_hb.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
private TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
    public Team getTeamById(Long id) {
        return teamRepository.findAll().stream().filter(team -> team.getId().equals(id)).findFirst().get();
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

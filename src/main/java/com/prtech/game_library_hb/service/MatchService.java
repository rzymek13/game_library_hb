package com.prtech.game_library_hb.service;

import com.prtech.game_library_hb.controller.dto.MatchDto;
import com.prtech.game_library_hb.model.Match;
import com.prtech.game_library_hb.model.Team;
import com.prtech.game_library_hb.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {
    private MatchRepository matchRepository;
    private TeamService teamService;

    public MatchService(MatchRepository matchRepository, TeamService teamService) {
        this.matchRepository = matchRepository;
        this.teamService = teamService;
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }
    public Match getMatchById(Long id) {
        return matchRepository.findAll().stream().filter(match -> match.getId() == id).findFirst().get();
    }
    public Match saveMatch(MatchDto matchDto) {
        Team homeTeam = teamService.getAllTeams().stream()
                .filter(team -> team.getName().equals(matchDto.homeTeam()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("HomeTeam nie może być nullem"));

        Team awayTeam = teamService.getAllTeams().stream()
                .filter(team -> team.getName().equals(matchDto.awayTeam()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("AwayTeam nie może być nullem"));
        Match match = new Match();
        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
        match.setHomeTeamGoals(matchDto.homeTeamGoals());
        match.setAwayTeamGoals(matchDto.awayTeamGoals());
        match.setResult(matchDto.result());
        match.setHomeTeamPenaltyGoals(matchDto.homeTeamPenaltyGoals());
        match.setAwayTeamPenaltyGoals(matchDto.awayTeamPenaltyGoals());


        return matchRepository.save(match);
    }
}

package com.prtech.game_library_hb.service;

import com.prtech.game_library_hb.model.Standings;
import com.prtech.game_library_hb.repository.StandingsRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StandingsService {
    private final MatchService matchService;
    private final TeamService teamService;
    private final StandingsRepository standingsRepository;

    public StandingsService(MatchService matchService, TeamService teamService, StandingsRepository standingsRepository) {
        this.matchService = matchService;
        this.teamService = teamService;
        this.standingsRepository = standingsRepository;
    }

    public List<Standings> findAllStandings() {
        resetStandings();
        matchService.readAllMatches()
                .forEach(match -> resultsCreator(match.result()));
        return standingsRepository.findAll();
    }


    public List<Standings> saveStandings() {
        return teamService.getAllTeams().stream()
                .map(team -> standingsRepository.save(new Standings(
                        null,
                        team,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0)))
                .toList();
    }
    private void resultsCreator(Integer result){
    switch (result) {
        case 1:
            matchService.getAllMatches().stream()
                    .filter(match -> match.result() == 1)
                    .forEach(match -> {
                        Standings homeTeamStandings = getStandingsByName(match.homeTeam().name());
                        homeTeamStandings.setWins(homeTeamStandings.getWins()+1);
                        standingsRepository.save(homeTeamStandings);
                        Standings awayTeamStandings = getStandingsByName(match.awayTeam().name());
                        awayTeamStandings.setLoses(awayTeamStandings.getLoses()+1);
                        standingsRepository.save(awayTeamStandings);
                    });
            break;
        case 2:
            matchService.getAllMatches().stream()
                    .filter(match -> match.result() == 2)
                    .forEach(match -> {
                        Standings awayTeamStandings = getStandingsByName(match.awayTeam().name());
                        awayTeamStandings.setWins(awayTeamStandings.getWins()+1);
                        standingsRepository.save(awayTeamStandings);
                        Standings homeTeamStandings = getStandingsByName(match.homeTeam().name());
                        homeTeamStandings.setLoses(homeTeamStandings.getWins()+1);
                        standingsRepository.save(homeTeamStandings);
                    });
            break;
        case 3:
            matchService.getAllMatches().stream()
                    .filter(match -> match.result() == 3)
                    .forEach(match -> {
                        Standings homeTeamStandings = getStandingsByName(match.homeTeam().name());
                        homeTeamStandings.setWinsAfterPenalty(homeTeamStandings.getWinsAfterPenalty()+1);
                        Standings awayTeamStandings = getStandingsByName(match.awayTeam().name());
                        awayTeamStandings.setLosesAfterPenalty(awayTeamStandings.getLosesAfterPenalty()+1);
                        standingsRepository.save(homeTeamStandings);
                        standingsRepository.save(awayTeamStandings);
                    });
            break;
        case 4:
            matchService.getAllMatches().stream()
                    .filter(match -> match.result() == 4)
                    .forEach(match -> {
                        Standings homeTeamStandings = getStandingsByName(match.homeTeam().name());
                        homeTeamStandings.setLosesAfterPenalty(homeTeamStandings.getLosesAfterPenalty()+1);
                        Standings awayTeamStandings = getStandingsByName(match.awayTeam().name());
                        awayTeamStandings.setWinsAfterPenalty(awayTeamStandings.getWinsAfterPenalty()+1);
                        standingsRepository.save(homeTeamStandings);
                        standingsRepository.save(awayTeamStandings);
                    });
            break;
        default:
            throw new IllegalArgumentException("Invalid result. Expected: 1 - home team won," +
                    " 2 - away team won -1, 3 - home team won after penalty 4, 4 - away team won after penalty");
    }
}

    private Standings getStandingsByName(String match) {
        return standingsRepository.findAll().stream()
                .filter(team -> team.getTeam().getName().equals(match))
                .findFirst()
                .get();
    }
    private void resetStandings() {
        standingsRepository.findAll().forEach(standings -> {
            standings.setPoints(0);
            standings.setMatchesPlayed(0);
            standings.setGoalsScored(0);
            standings.setGoalsConceded(0);
            standings.setWins(0);
            standings.setLoses(0);
            standings.setWinsAfterPenalty(0);
            standings.setLosesAfterPenalty(0);
                }
        );
    }

    public void deleteAllStandings() {
        standingsRepository.findAll().forEach(standings -> delteById(standings.getId()));
    }
    public void delteById(Long id) {
        standingsRepository.deleteById(id);
    }
}

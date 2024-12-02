package com.prtech.game_library_hb.service;

import com.prtech.game_library_hb.model.Standings;
import com.prtech.game_library_hb.repository.StandingsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Standings> createStandings() {
        clearStandings();
        matchService.readAllMatches()
                .forEach(match -> resultsCreator(match.result(), match.homeTeam().name(), match.awayTeam().name()));
        return standingsRepository.findAll();
    }
    public List<Standings> generateStandings() {
        ArrayList<Standings> standingsList = new ArrayList<>();
        teamService.getAllTeams().forEach(team -> {
            Standings standings = new Standings();
            standings.setTeam(team);
            standings.setPoints(0);
            standings.setMatchesPlayed(0);
            standings.setGoalsScored(0);
            standings.setGoalsConceded(0);
            standings.setWins(0);
            standings.setLoses(0);
            standings.setWinsAfterPenalty(0);
            standings.setLosesAfterPenalty(0);
            standingsList.add(standings);
            standingsRepository.save(standings);
        });
        return standingsList;
    }


    public List<Standings> clearStandings() {
        List<Standings> standingsList = standingsRepository.findAll();
        standingsList.forEach(standings -> {
            standings.setPoints(0);
            standings.setMatchesPlayed(0);
            standings.setGoalsScored(0);
            standings.setGoalsConceded(0);
            standings.setWins(0);
            standings.setLoses(0);
            standings.setWinsAfterPenalty(0);
            standings.setLosesAfterPenalty(0);
            standingsRepository.save(standings);
        });
        return standingsList;
    }
    private void resultsCreator(Integer result, String homeTeamName, String awayTeamName) {
        Standings homeTeamStandings = getStandingsByName(homeTeamName);
        Standings awayTeamStandings = getStandingsByName(awayTeamName);

        switch (result) {
            case 1:
                homeTeamStandings.setWins(homeTeamStandings.getWins() + 1);
                homeTeamStandings.setPoints(homeTeamStandings.getPoints() + 3);
                homeTeamStandings.setMatchesPlayed(homeTeamStandings.getMatchesPlayed() + 1);
                awayTeamStandings.setLoses(awayTeamStandings.getLoses() + 1);
                awayTeamStandings.setMatchesPlayed(awayTeamStandings.getMatchesPlayed() + 1);

                break;
            case 2:
                awayTeamStandings.setWins(awayTeamStandings.getWins() + 1);
                awayTeamStandings.setPoints(awayTeamStandings.getPoints() + 3);
                awayTeamStandings.setMatchesPlayed(awayTeamStandings.getMatchesPlayed() + 1);
                homeTeamStandings.setLoses(homeTeamStandings.getLoses() + 1);
                homeTeamStandings.setMatchesPlayed(homeTeamStandings.getMatchesPlayed() + 1);
                break;
            case 3:
                homeTeamStandings.setWinsAfterPenalty(homeTeamStandings.getWinsAfterPenalty() + 1);
                homeTeamStandings.setPoints(homeTeamStandings.getPoints() + 2);
                homeTeamStandings.setMatchesPlayed(homeTeamStandings.getMatchesPlayed() + 1);
                awayTeamStandings.setLosesAfterPenalty(awayTeamStandings.getLosesAfterPenalty() + 1);
                awayTeamStandings.setMatchesPlayed(awayTeamStandings.getMatchesPlayed() + 1);
                break;
            case 4:
                awayTeamStandings.setWinsAfterPenalty(awayTeamStandings.getWinsAfterPenalty() + 1);
                awayTeamStandings.setPoints(awayTeamStandings.getPoints() + 2);
                awayTeamStandings.setMatchesPlayed(awayTeamStandings.getMatchesPlayed() + 1);
                homeTeamStandings.setLosesAfterPenalty(homeTeamStandings.getLosesAfterPenalty() + 1);
                homeTeamStandings.setMatchesPlayed(homeTeamStandings.getMatchesPlayed() + 1);
                break;
            default:
                throw new IllegalArgumentException("Invalid result. Expected: 1 - home team won, 2 - away team won, 3 - home team won after penalty, 4 - away team won after penalty");
        }

        standingsRepository.save(homeTeamStandings);
        standingsRepository.save(awayTeamStandings);
    }

    private Standings getStandingsByName(String match) {
        return standingsRepository.findAll().stream()
                .filter(team -> team.getTeam().getName().equals(match))
                .findFirst()
                .get();
    }


    public void deleteAllStandings() {
        standingsRepository.findAll().forEach(standings -> delteById(standings.getId()));
    }
    public void delteById(Long id) {
        standingsRepository.deleteById(id);
    }
}

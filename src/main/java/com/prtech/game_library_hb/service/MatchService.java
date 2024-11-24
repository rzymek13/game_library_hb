package com.prtech.game_library_hb.service;

import com.prtech.game_library_hb.controller.dto.MatchDto;
import com.prtech.game_library_hb.controller.dto.MatchMapper;
import com.prtech.game_library_hb.controller.dto.TeamMapper;
import com.prtech.game_library_hb.model.Match;
import com.prtech.game_library_hb.model.MatchPlayer;
import com.prtech.game_library_hb.model.Player;
import com.prtech.game_library_hb.model.Team;
import com.prtech.game_library_hb.repository.MatchPlayerRepository;
import com.prtech.game_library_hb.repository.MatchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final TeamService teamService;
    private final MatchPlayerService matchPlayerService;
    private final PlayerService playerService;
    private final MatchPlayerRepository matchPlayerRepository;


    public MatchService(MatchRepository matchRepository, TeamService teamService, MatchPlayerService matchPlayerService, PlayerService playerService, MatchPlayerRepository matchPlayerRepository) {
        this.matchRepository = matchRepository;
        this.teamService = teamService;
        this.matchPlayerService = matchPlayerService;
        this.playerService = playerService;
        this.matchPlayerRepository = matchPlayerRepository;
    }

    public List<MatchDto> getAllMatches() {
        return MatchMapper.mapMatchesToDtos(matchRepository.findAll());
    }

    public List<MatchDto> readAllMatches() {
        return matchRepository.findAll().stream()
                .map(match -> new MatchDto(
                        match.getId(),
                        TeamMapper.mapToTeamDto(match.getHomeTeam()),
                        TeamMapper.mapToTeamDto(match.getAwayTeam()),
                        match.getHomeTeamGoals(),
                        match.getAwayTeamGoals(),
                        match.getResult(),
                        match.getHomeTeamPenaltyGoals(),
                        match.getAwayTeamPenaltyGoals(),
                        matchPlayerService.findAllByMatchId(match.getId())
                ))
                .collect(Collectors.toList());
    }


    public MatchDto saveMatch(MatchDto matchDto) {
        Team homeTeam = teamService.getTeamByName(matchDto.homeTeam().name());
        Team awayTeam = teamService.getTeamByName(matchDto.awayTeam().name());

        Match match = new Match();
        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
        match.setHomeTeamGoals(matchDto.homeTeamGoals());
        match.setAwayTeamGoals(matchDto.awayTeamGoals());
        match.setResult(matchDto.result());
        match.setHomeTeamPenaltyGoals(matchDto.homeTeamPenaltyGoals());
        match.setAwayTeamPenaltyGoals(matchDto.awayTeamPenaltyGoals());
        matchRepository.save(match);

        Set<MatchPlayer> matchPlayers = new HashSet<>();
        matchDto.matchPlayers().forEach(matchPlayerDto -> {
            Player player = playerService.getPlayerByName(matchPlayerDto.playerName());

            MatchPlayer matchPlayer = new MatchPlayer();
            matchPlayer.setPlayer(player);
            matchPlayer.setGoals(matchPlayerDto.goals());
            matchPlayer.setMatch(match);
            matchPlayerService.save(matchPlayer);
            matchPlayers.add(matchPlayer);
        });
        log.info(match.getId().toString());
        match.setMatchPlayerSet(matchPlayers);

        return MatchMapper.mapMatchToDto(match);
    }
    public void deleteMatchById(Long id) {
        matchPlayerRepository.findAll().stream()
                .filter(matchPlayer -> matchPlayer.getMatch().getId().equals(id))
                .forEach(matchPlayer -> matchPlayerService.deleteMatchPlayer(matchPlayer.getId()));
        matchRepository.deleteById(id);
        log.info("Match with id: {} deleted", id);
    }
}

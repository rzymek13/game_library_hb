package com.prtech.game_library_hb.controller.dto;

import com.prtech.game_library_hb.model.Match;
import com.prtech.game_library_hb.model.MatchPlayer;

import java.util.List;
import java.util.stream.Collectors;

public class MatchMapper {



    public static MatchPlayerDto mapPlayerMatchToDto(MatchPlayer matchPlayer) {
        return new MatchPlayerDto(
                matchPlayer.getPlayer().getName(),
                matchPlayer.getGoals());
    }
    public static List<MatchPlayerDto> mapPlayersMatchToDtos(List<MatchPlayer> matchPlayers) {
        return matchPlayers.stream()
                .map(MatchMapper::mapPlayerMatchToDto)
                .collect(Collectors.toList());
    }

    public static List<MatchDto> mapMatchesToDtos(List<Match> matches) {
        return matches.stream()
                .map(MatchMapper::mapMatchToDto)
                .collect(Collectors.toList());
    }

    public static MatchDto mapMatchToDto(Match match) {
        return new MatchDto(match.getId(),
                TeamMapper.mapToTeamDto(match.getHomeTeam()),
                TeamMapper.mapToTeamDto(match.getAwayTeam()),
                match.getHomeTeamGoals(),
                match.getAwayTeamGoals(),
                match.getResult(),
                match.getHomeTeamPenaltyGoals(),
                match.getAwayTeamPenaltyGoals(),
                match.getMatchPlayerSet().stream()
                        .map(MatchMapper::mapPlayerMatchToDto)
                        .collect(Collectors.toSet()));
    }
}

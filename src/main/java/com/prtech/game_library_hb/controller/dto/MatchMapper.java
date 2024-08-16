package com.prtech.game_library_hb.controller.dto;

import com.prtech.game_library_hb.model.Match;
import com.prtech.game_library_hb.model.MatchPlayer;

import java.util.Set;
import java.util.stream.Collectors;

public class MatchMapper {
    public static MatchDto mapMatchToDto(Match match, Set<MatchPlayer> scorers) {
        return new MatchDto(match.getId(),
                match.getHomeTeam().getName(),
                match.getAwayTeam().getName(),
                match.getHomeTeamGoals(),
                match.getAwayTeamGoals(),
                match.getResult(),
                match.getHomeTeamPenaltyGoals(),
                match.getAwayTeamPenaltyGoals(),
                mapPlayerMatchToDtos(scorers)
                );
    }
    private static  Set<MatchPlayerDto> mapPlayerMatchToDtos(Set<MatchPlayer> scores) {
        return scores.stream().map(MatchMapper::mapPlayerMatchToDto)
                .collect(Collectors.toSet());

    }
    private static MatchPlayerDto mapPlayerMatchToDto(MatchPlayer matchPlayer) {
        return new MatchPlayerDto(
                matchPlayer.getId(),
                matchPlayer.getPlayer().getName(),
                matchPlayer.getGoals()
        );
    }
}

package com.prtech.game_library_hb.controller.dto;

import com.prtech.game_library_hb.model.Match;

public class MatchMapper {
    public static MatchDto mapMatchToDto(Match match) {
        return new MatchDto(match.getId(),
                match.getHomeTeam().getName(),
                match.getAwayTeam().getName(),
                match.getHomeTeamGoals(),
                match.getAwayTeamGoals(),
                match.getResult(),
                match.getHomeTeamPenaltyGoals(),
                match.getAwayTeamPenaltyGoals()
                );
    }
}

package com.prtech.game_library_hb.controller.dto;



import java.util.Set;

public record MatchDto(Long id,
                       TeamNameDto homeTeam,
                       TeamNameDto awayTeam,
                       Integer homeTeamGoals,
                       Integer awayTeamGoals,
                       Integer result,
                       Integer homeTeamPenaltyGoals,
                       Integer awayTeamPenaltyGoals,
                       Set<MatchPlayerDto> matchPlayers
) {

    // Other methods and constructors can be added here as needed.


}


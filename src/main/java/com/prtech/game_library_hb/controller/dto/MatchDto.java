package com.prtech.game_library_hb.controller.dto;


public record MatchDto(Long id,
     String homeTeam,
     String awayTeam,
     Integer homeTeamGoals,
     Integer awayTeamGoals,
     Integer result,
     Integer homeTeamPenaltyGoals,
     Integer awayTeamPenaltyGoals
//     List<CreateMatchPlayerDTO> createMatchPlayers
) {

    // Other methods and constructors can be added here as needed.


}


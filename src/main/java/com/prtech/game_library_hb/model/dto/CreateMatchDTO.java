package com.prtech.game_library_hb.model.dto;

import com.prtech.game_library_hb.model.MatchPlayer;

import java.util.List;



public record CreateMatchDTO(
     TeamNameDTO homeTeam,
     TeamNameDTO awayTeam,
     Integer homeTeamGoals,
     Integer awayTeamGoals,
     Integer result,
     Integer homeTeamPenaltyGoals,
     Integer awayTeamPenaltyGoals,
     List<CreateMatchPlayerDTO> createMatchPlayers
) {

    // Other methods and constructors can be added here as needed.


}


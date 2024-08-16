package com.prtech.game_library_hb.controller.dto;


import com.prtech.game_library_hb.model.MatchPlayer;

import java.util.List;
import java.util.Set;

public record MatchDto(Long id,
                       String homeTeam,
                       String awayTeam,
                       Integer homeTeamGoals,
                       Integer awayTeamGoals,
                       Integer result,
                       Integer homeTeamPenaltyGoals,
                       Integer awayTeamPenaltyGoals,
                       Set<MatchPlayerDto> matchPlayers
) {

    // Other methods and constructors can be added here as needed.


}


package com.prtech.game_library_hb.controller.dto;

public record ReadMatchDto(Long id,
                           String homeTeam,
                           String awayTeam,
                           Integer homeTeamGoals,
                           Integer awayTeamGoals,
                           Integer result,
                           Integer homeTeamPenaltyGoals,
                           Integer awayTeamPenaltyGoals) {
}

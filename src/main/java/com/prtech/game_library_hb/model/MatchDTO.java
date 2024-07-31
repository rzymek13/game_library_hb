package com.prtech.game_library_hb.model;

import lombok.Data;

@Data
public class MatchDTO {

    private Long id;
    private TeamDTO homeTeam;
    private TeamDTO awayTeam;
    private Integer homeTeamGoals;
    private Integer awayTeamGoals;
    private Integer result;
    private Integer homeTeamPenaltyGoals;
    private Integer awayTeamPenaltyGoals;

    public MatchDTO(Long id, TeamDTO homeTeam, TeamDTO awayTeam, Integer homeTeamGoals, Integer awayTeamGoals, Integer result, Integer homeTeamPenaltyGoals, Integer awayTeamPenaltyGoals) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.result = result;
        this.homeTeamPenaltyGoals = homeTeamPenaltyGoals;
        this.awayTeamPenaltyGoals = awayTeamPenaltyGoals;
    }
}

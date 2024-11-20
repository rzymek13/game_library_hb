package com.prtech.game_library_hb.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Data
@Entity
@Table(name="matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;
    private Integer homeTeamGoals;
    private Integer awayTeamGoals;
    private Integer result;
    private Integer homeTeamPenaltyGoals;
    private Integer awayTeamPenaltyGoals;

    @OneToMany(mappedBy = "match")
    private Set<MatchPlayer> matchPlayerSet;

    public Match() {
    }
}



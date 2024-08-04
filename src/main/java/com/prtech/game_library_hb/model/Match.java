package com.prtech.game_library_hb.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name="matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id", insertable = false, updatable = false)
    private Team homeTeam;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_id", insertable = false, updatable = false)
    private Team awayTeam;
    private Integer homeTeamGoals;
    private Integer awayTeamGoals;
    private Integer result;
    private Integer homeTeamPenaltyGoals;
    private Integer awayTeamPenaltyGoals;

    @OneToMany(mappedBy = "match")
    private List<MatchPlayer> matchPlayerList = new ArrayList<>();



    public Match() {
    }
}



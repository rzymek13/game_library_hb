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
    @OneToOne(fetch = FetchType.LAZY)
    private Team homeTeam;
    @OneToOne(fetch = FetchType.LAZY)
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

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", homeTeam=" + homeTeam +
                ", awayTeam=" + awayTeam +
                ", homeTeamGoals=" + homeTeamGoals +
                ", awayTeamGoals=" + awayTeamGoals +
                ", result=" + result +
                ", homeTeamPenaltyGoals=" + homeTeamPenaltyGoals +
                ", awayTeamPenaltyGoals=" + awayTeamPenaltyGoals +
                ", matchPlayerSet=" + matchPlayerSet +
                '}';
    }
}



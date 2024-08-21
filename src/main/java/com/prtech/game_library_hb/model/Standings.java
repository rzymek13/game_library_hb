package com.prtech.game_library_hb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "standings")
public class Standings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_name")
    private Team team;

    private Integer matchesPlayed;
    private Integer points;

    private Integer goalsScored;
    private Integer goalsConceded;

    private Integer wins;
    private Integer winsAfterPenalty;
    private Integer loses;
    private Integer losesAfterPenalty;

    public Standings() {
    }

    public Standings(Long id, Team team, Integer matchesPlayed, Integer points, Integer goalsScored, Integer goalsConceded, Integer wins, Integer winsAfterPenalty, Integer loses, Integer losesAfterPenalty) {
        this.id = id;
        this.team = team;
        this.matchesPlayed = matchesPlayed;
        this.points = points;
        this.goalsScored = goalsScored;
        this.goalsConceded = goalsConceded;
        this.wins = wins;
        this.winsAfterPenalty = winsAfterPenalty;
        this.loses = loses;
        this.losesAfterPenalty = losesAfterPenalty;
    }
}

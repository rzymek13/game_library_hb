package com.prtech.game_library_hb.model;


import jakarta.persistence.*;
import lombok.Data;




@Data
@Entity
@Table(name="matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;
    @ManyToOne()
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;
    private Integer homeTeamGoals;
    private Integer awayTeamGoals;
    private Integer result;
    private Integer homeTeamPenaltyGoals;
    private Integer awayTeamPenaltyGoals;

//        @OneToMany(mappedBy = "matchTeamId")
//    private List<MatchPlayer> matchPlayerList = new ArrayList<>();



    public Match() {
    }
}



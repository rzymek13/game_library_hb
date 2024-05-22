package com.prtech.game_library_hb.match_team.model;


import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
public class MatchTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer matchTeamId;
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Team homeTeamId;
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Team awayTeamId;
//    private Integer homeTeamGoals;
//    private Integer awayTeamGoals;
//    @OneToMany(mappedBy = "matchTeamId")
//    private List<MatchPlayer> matchPlayerList = new ArrayList<>();
    public MatchTeam() {
    }
}

package com.prtech.game_library_hb.match_player.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class MatchPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer matchPlayerId;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "matchTeamId")
//    private MatchTeam matchTeamId;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "playerId")
//    private Player playerId;
//    private Integer goals;
    public MatchPlayer() {
    }
}
package com.prtech.game_library_hb.match_player.model;

import com.prtech.game_library_hb.match_team.model.MatchTeam;
import com.prtech.game_library_hb.player.model.Player;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "match_player")
public class MatchPlayer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer matchPlayerId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matchTeamId")
    private MatchTeam matchTeamId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playerId")
    private Player playerId;
    private Integer goals;
    public MatchPlayer() {
    }
}

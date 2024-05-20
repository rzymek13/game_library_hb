package com.prtech.game_library_hb.player.model;

import com.prtech.game_library_hb.team.model.Team;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "players")
public class Player {

    @Id
    @Column(name = "PLAYER ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playerId;
    @Column(name = "PLAYER NAME")
    private String name;
    @Column(name = "MATCHES PLAYED")
    private Integer matchesPlayed;
    @Column(name = "GOALS SCORED")
    private Integer goalsScored;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team teamId;
}

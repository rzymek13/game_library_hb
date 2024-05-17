package com.prtech.game_library_hb.player.model;

import jakarta.persistence.*;
import lombok.Data;
import org.checkerframework.checker.units.qual.C;

@Data
@Entity
@Table(name = "players")
public class Player {

    @Id
    @Column(name = "PLAYER ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer playerId;
    @Column(name = "PLAYER NAME")
    private String name;
    @Column(name = "MATCHES PLAYED")
    private Integer matchesPlayed;
    @Column(name = "GOALS SCORED")
    private Integer goalsScored;
}

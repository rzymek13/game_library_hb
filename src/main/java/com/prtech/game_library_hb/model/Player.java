package com.prtech.game_library_hb.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer matchesPlayed;
    private Integer goalsScored;

    @ManyToOne()
    @JoinColumn(name = "team_id")
    private Team team;

    public Player() {
    }
}

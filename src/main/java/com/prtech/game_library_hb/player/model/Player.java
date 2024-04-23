package com.prtech.game_library_hb.player.model;

import com.prtech.game_library_hb.team.model.Team;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer matchesPlayed;
    private Integer goalsScored;
    private Team team;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }
}

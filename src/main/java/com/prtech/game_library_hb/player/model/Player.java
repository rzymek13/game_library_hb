package com.prtech.game_library_hb.player.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
//    private Team teamId;
    private String name;
    private Integer mathcesPlayed;
    private Integer goalsScored;
}

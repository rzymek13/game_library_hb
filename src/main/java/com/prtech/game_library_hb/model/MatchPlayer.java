package com.prtech.game_library_hb.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class MatchPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "match_id")
    private Match match;
    @ManyToOne()
    @JoinColumn(name = "player_id")
    private Player player;
    private Integer goals;


    public MatchPlayer() {
    }
}

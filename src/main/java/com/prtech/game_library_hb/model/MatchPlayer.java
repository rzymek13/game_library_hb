package com.prtech.game_library_hb.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="playerMatches")
public class MatchPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    private Integer goals;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Match match;

}

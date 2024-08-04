package com.prtech.game_library_hb.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "team_name")
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Player> players = new ArrayList<>();

    @OneToMany(mappedBy = "homeTeam")
    private List<Match> homeMatches = new ArrayList<>();

    @OneToMany(mappedBy = "awayTeam")
    private List<Match> awayMatches = new ArrayList<>();

    public Team() {
    }
}

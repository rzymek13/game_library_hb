package com.prtech.game_library_hb.player.model;

import com.prtech.game_library_hb.match_player.model.MatchPlayer;
import com.prtech.game_library_hb.team.model.Team;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playerId;
    private String name;
    private Integer matchesPlayed;
    private Integer goalsScored;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamId")
    private Team teamId;
    @OneToMany(mappedBy="playerId")
    private List<MatchPlayer> matchList = new ArrayList<>();
}

package com.prtech.game_library_hb.team.model;

import com.prtech.game_library_hb.match_team.model.MatchTeam;
import com.prtech.game_library_hb.player.model.Player;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="teams")
public class Team implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamId;
    private String name;
    private Integer matches;
    private Integer points;
    private Integer goalScored;
    private Integer goalConceded;
    @OneToMany(mappedBy = "teamId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> playerList = new ArrayList<>();
    @OneToMany(mappedBy = "homeTeamId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MatchTeam> homeMatchList = new ArrayList<>();
    @OneToMany(mappedBy = "awayTeamId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MatchTeam> awayMatchList = new ArrayList<>();
    public Team() {}

}

package com.prtech.game_library_hb.team.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.prtech.game_library_hb.player.model.Player;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamId;
    private String name;

    private Integer matches;

    private Integer points;

    private Integer goalScored;

    private Integer goalConceded;

//    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
////    @JsonIgnoreProperties("team")
//    @JsonIgnore
//    private List<Player> playerList;

    //    @OneToMany(mappedBy = "homeTeamId", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<MatchTeam> homeMatchList;
//    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<MatchTeam> awayMatchList;
    public Team() {
    }

}

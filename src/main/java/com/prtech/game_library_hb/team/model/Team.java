package com.prtech.game_library_hb.team.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

package com.prtech.game_library_hb.player.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.prtech.game_library_hb.team.model.Team;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playerId;
    private String name;
    private Integer matchesPlayed;
    private Integer goalsScored;
    @ManyToOne()
    @JoinColumn(name = "team_id")
    @JsonIgnoreProperties("team")
    
    private Team team;
//    @OneToMany(mappedBy="playerId")
//    private List<MatchPlayer> matchList;


    public Player() {
    }

//    public String getTeam() {
//        return team.getName();
//    }

}

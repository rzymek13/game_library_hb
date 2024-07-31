package com.prtech.game_library_hb.model;

import lombok.Data;

@Data
public class PlayerDTO {
    private Long id;
    private String name;
    private Integer matchesPlayed;
    private Integer goalsScored;
    private TeamDTO team;

    public PlayerDTO(Long id, String name, Integer matchesPlayed, Integer goalsScored, TeamDTO team) {
        this.id = id;
        this.name = name;
        this.matchesPlayed = matchesPlayed;
        this.goalsScored = goalsScored;
        this.team = team;
    }
}

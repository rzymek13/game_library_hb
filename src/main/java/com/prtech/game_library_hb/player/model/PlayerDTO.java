package com.prtech.game_library_hb.player.model;

import com.prtech.game_library_hb.team.model.TeamNameDTO;
import lombok.Data;

@Data
public class PlayerDTO {
    private Long id;
    private String name;
    private Integer matchesPlayed;
    private Integer goalsScored;
    private TeamNameDTO team;

    public PlayerDTO(Long id, String name, Integer matchesPlayed, Integer goalsScored, TeamNameDTO team) {
        this.id = id;
        this.name = name;
        this.matchesPlayed = matchesPlayed;
        this.goalsScored = goalsScored;
        this.team = team;
    }
}

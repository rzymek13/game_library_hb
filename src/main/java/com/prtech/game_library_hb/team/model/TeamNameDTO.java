package com.prtech.game_library_hb.team.model;

import lombok.Data;

@Data
public class TeamNameDTO {

    private String name;

    public TeamNameDTO(String name) {
        this.name = name;
    }
}

package com.prtech.game_library_hb.model;

import lombok.Data;

@Data
public class TeamDTO {

    private String name;

    public TeamDTO(String name) {
        this.name = name;
    }
}

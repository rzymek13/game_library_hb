package com.prtech.game_library_hb.model;

import lombok.Data;

@Data
public class TeamDTO {
//    private Long id;
    private String name;

    public TeamDTO(String name) {
        this.name = name;
    }

//    public TeamDTO(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }
}

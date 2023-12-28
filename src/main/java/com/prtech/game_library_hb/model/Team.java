package com.prtech.game_library_hb.model;

import lombok.Data;

@Data
public class Team {
    private Integer id;
    private String name;
    private Integer mathces;
    private Integer points;
    private Integer goalsScored;
    private Integer goalsConceded;
}

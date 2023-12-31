package com.prtech.game_library_hb.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer matches;
    private Integer points;
    private Integer goalsScored;
    private Integer goalsConceded;
}

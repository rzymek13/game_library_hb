package com.prtech.game_library_hb.team.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamId;
    private String name;
    private Integer matches;
    private Integer points;
    public Team() {}

}

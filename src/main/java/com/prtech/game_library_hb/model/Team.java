package com.prtech.game_library_hb.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "team_name")
    private String name;

    public Team() {
    }
}

package com.prtech.game_library_hb.standings.model;

import com.prtech.game_library_hb.team.model.Team;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "standings")
public class Standings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Team team;
    private Integer wins;
    private Integer loses;


}

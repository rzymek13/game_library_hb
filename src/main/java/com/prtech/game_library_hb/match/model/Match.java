package com.prtech.game_library_hb.match.model;

import com.prtech.game_library_hb.team.model.Team;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer matchId;
    private Team homeTeam;
    private Team awayTeam;
    private Integer homeTeamScore;
    private Integer awayTeamScore;
}

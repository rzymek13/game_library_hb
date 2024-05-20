package com.prtech.game_library_hb.match_team.model;

import com.prtech.game_library_hb.match_player.model.MatchPlayer;
import com.prtech.game_library_hb.team.model.Team;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "match_team")
public class MatchTeam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer matchTeamId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Team homeTeamId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Team awayTeamId;
    private Integer homeTeamGoals;
    private Integer awayTeamGoals;
    @OneToMany(mappedBy = "matchTeamId")
    private List<MatchPlayer> matchPlayerList = new ArrayList<>();
    public MatchTeam() {
    }
}

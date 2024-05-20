package com.prtech.game_library_hb.team.model;

import com.prtech.game_library_hb.player.model.Player;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="teams")
public class Team implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamId;
    private String name;
    private Integer matches;
    private Integer points;
    @OneToMany(mappedBy = "teamId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> playerList = new ArrayList<Player>();
    public Team() {}

}

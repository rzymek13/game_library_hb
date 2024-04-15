package com.prtech.game_library_hb.match.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer matchId;
//    private
}

package com.prtech.game_library_hb.player.repository;

import com.prtech.game_library_hb.player.model.Player;
import com.prtech.game_library_hb.team.model.Team;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository {
    List<Player> findAll();
//    Optional<Player> findById(Integer id);
//    Team save(Player entity);
//    void deleteById(Integer id);
}

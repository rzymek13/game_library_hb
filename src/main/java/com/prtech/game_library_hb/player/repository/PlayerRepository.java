package com.prtech.game_library_hb.player.repository;

import com.prtech.game_library_hb.player.model.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository {
    List<Player> findAll();
    Optional<Player> findById(Integer id);
    Player save(Player entity);
//    void saveAll(Integer id,List<Player> players);
    void deleteById(Integer id);
    void deleteAll();
}

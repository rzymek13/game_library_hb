package com.prtech.game_library_hb.repository;

import com.prtech.game_library_hb.model.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository {
    List<Player> findAll();
    Optional<Player> findById(Long id);
    Player save(Player entity);
    List<Player> findByTeamId(Long teamId);
    void deleteById(Long id);
    void deleteAll();
}
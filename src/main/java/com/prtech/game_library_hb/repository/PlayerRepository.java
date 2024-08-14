package com.prtech.game_library_hb.repository;

import com.prtech.game_library_hb.model.Player;
import java.util.List;

public interface PlayerRepository {
    List<Player> findAll();
    Player save(Player entity);
    void deleteById(Long id);
    void deleteAll();
}

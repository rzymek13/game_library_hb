package com.prtech.game_library_hb.repository;

import com.prtech.game_library_hb.model.MatchPlayer;

import java.util.List;
import java.util.Optional;

public interface MatchPlayerRepository {
    List<MatchPlayer> findAll();
    Optional<MatchPlayer> findById(Long id);
    MatchPlayer save(MatchPlayer entity);
    MatchPlayer saveAndFlush(MatchPlayer entity);
    void deleteById(Long id);
    void deleteAll();
}


package com.prtech.game_library_hb.repository;

import com.prtech.game_library_hb.model.Match;

import java.util.List;
import java.util.Optional;

public interface MatchRepository {
    List<Match> findAll();
    Optional<Match> findById(Long id);
    Match save(Match entity);
    void deleteById(Long id);
    void deleteAll();
}

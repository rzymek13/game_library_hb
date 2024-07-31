package com.prtech.game_library_hb.repository;

import com.prtech.game_library_hb.model.Team;

import java.util.List;
import java.util.Optional;

public interface TeamRepository {
    List<Team> findAll();

    Optional<Team> findById(Long id);

    Team save(Team entity);

    void deleteById(Long id);

    void deleteAll();
}

package com.prtech.game_library_hb.repository;

import com.prtech.game_library_hb.model.Team;

import java.util.List;

public interface TeamRepository {
    List<Team> findAll();

    Team save(Team entity);

    void deleteById(Long id);

    void deleteAll();
}

package com.prtech.game_library_hb.repository;

import com.prtech.game_library_hb.model.Standings;

import java.util.List;

public interface StandingsRepository {
    List<Standings> findAll();
    Standings save(Standings entity);
    void deleteById(Long id);
}

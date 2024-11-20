package com.prtech.game_library_hb.repository;

import com.prtech.game_library_hb.controller.dto.MatchPlayerDto;
import com.prtech.game_library_hb.model.MatchPlayer;

import java.util.List;

public interface MatchPlayerRepository {
    List<MatchPlayer> findAll();
    MatchPlayer save(MatchPlayer entity);
}

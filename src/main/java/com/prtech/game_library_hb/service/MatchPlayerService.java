package com.prtech.game_library_hb.service;

import com.prtech.game_library_hb.model.MatchPlayer;
import com.prtech.game_library_hb.repository.MatchPlayerRepository;

import java.util.List;

public class MatchPlayerService {
    private MatchPlayerRepository matchPlayerRepository;

    public MatchPlayerService(MatchPlayerRepository matchPlayerRepository) {
        this.matchPlayerRepository = matchPlayerRepository;
    }
    public List<MatchPlayer> findAll() {
        return matchPlayerRepository.findAll();
    }
}

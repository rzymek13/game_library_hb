package com.prtech.game_library_hb.service;

import com.prtech.game_library_hb.controller.dto.MatchMapper;
import com.prtech.game_library_hb.controller.dto.MatchPlayerDto;
import com.prtech.game_library_hb.model.MatchPlayer;
import com.prtech.game_library_hb.repository.MatchPlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MatchPlayerService {
    private MatchPlayerRepository matchPlayerRepository;

    public MatchPlayerService(MatchPlayerRepository matchPlayerRepository) {
        this.matchPlayerRepository = matchPlayerRepository;
    }
    public List<MatchPlayer> findAll() {
        return matchPlayerRepository.findAll();
    }
    public Set<MatchPlayerDto> findAllByMatchId(Long id) {
        return matchPlayerRepository.findAll().stream()
               .filter(matchPlayer -> matchPlayer.getMatch().getId().equals(id))
               .map(MatchMapper::mapPlayerMatchToDto)
               .collect(Collectors.toSet());
    }
    public MatchPlayer save(MatchPlayer matchPlayer) {
        return matchPlayerRepository.save(matchPlayer);
    }




}

package com.prtech.game_library_hb.repository;

import com.prtech.game_library_hb.controller.dto.MatchDto;
import com.prtech.game_library_hb.model.Match;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchRepository {
    List<Match> findAll();
    Match save(Match entity);
    void deleteById(Long id);
    void deleteAll();

}

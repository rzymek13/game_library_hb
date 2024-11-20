package com.prtech.game_library_hb.repository;

import com.prtech.game_library_hb.model.MatchPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlMatchPlayerRepository extends MatchPlayerRepository, JpaRepository<MatchPlayer,Long> {
}

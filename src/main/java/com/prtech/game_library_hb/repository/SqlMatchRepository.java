package com.prtech.game_library_hb.repository;

import com.prtech.game_library_hb.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlMatchRepository extends MatchRepository, JpaRepository<Match,Long> {
}

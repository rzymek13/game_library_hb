package com.prtech.game_library_hb.repository;

import com.prtech.game_library_hb.model.Standings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlStandingsRepository extends StandingsRepository, JpaRepository<Standings, Long> {
}

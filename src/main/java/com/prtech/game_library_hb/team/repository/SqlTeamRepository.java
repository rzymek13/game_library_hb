package com.prtech.game_library_hb.team.repository;

import com.prtech.game_library_hb.team.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlTeamRepository extends TeamRepository, JpaRepository<Team, Integer> {
}

package com.prtech.game_library_hb.team.repository;

import com.prtech.game_library_hb.team.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;



public interface TeamRepository {
    List<Team> findAll();
    Optional<Team> findById(Long id);
    Team save(Team entity);
    void deleteById(Long id);
    void deleteAll();
}

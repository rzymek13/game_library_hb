package com.prtech.game_library_hb.repository;

import com.prtech.game_library_hb.model.Team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface TeamRepository extends JpaRepository<Team, Integer> {

}

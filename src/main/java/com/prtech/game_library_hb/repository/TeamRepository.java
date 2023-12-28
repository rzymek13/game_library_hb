package com.prtech.game_library_hb.repository;

import com.prtech.game_library_hb.model.Team;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class TeamRepository {
    private List<Team> teams = new ArrayList<>();

    public TeamRepository() {

    }
    public List<Team> findAll() {
        return teams;
    }
    public Team findById(Integer id) {
        return teams.stream().filter(team -> team.equals(id)).findFirst().orElseThrow();
    }
}

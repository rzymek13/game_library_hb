package com.prtech.game_library_hb.controller;

import com.prtech.game_library_hb.repository.TeamRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/teams")
public class TeamController {
    private final TeamRepository teamRepository;

    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

//    public List<Team> findAll() {
//        return teamRepository.findAll();
//    }
}

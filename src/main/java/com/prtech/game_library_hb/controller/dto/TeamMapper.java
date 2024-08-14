package com.prtech.game_library_hb.controller.dto;

import com.prtech.game_library_hb.model.Team;

public class TeamMapper {
    public static Team mapDtoToTeam(TeamNameDto string) {
        Team team = new Team();
        team.setName(string.name());
        return team;
    }
    public static TeamNameDto mapToTeamDto (Team team) {
        return new TeamNameDto(team.getName());
    }
}

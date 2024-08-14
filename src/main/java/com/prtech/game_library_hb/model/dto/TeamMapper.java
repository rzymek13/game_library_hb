package com.prtech.game_library_hb.model.dto;

import com.prtech.game_library_hb.model.Team;

public class TeamMapper {
    public static Team mapDtoToTeam(TeamNameDTO teamNameDTO) {
        Team team = new Team();
        team.setName(teamNameDTO.name());
        return team;
    }
    public static TeamNameDTO mapToTeamDto (Team team) {
        return new TeamNameDTO(team.getName());
    }
}

package com.prtech.game_library_hb.controller.dto;

import com.prtech.game_library_hb.model.Player;


public class PlayerMapper {

    public static PlayerDto mapPlayerToDto(Player player) {
        return new PlayerDto(player.getId(),
                player.getName(),
                player.getTeam().getName()
                );
    }
    public static Player mapDtoToPlayer(PlayerDto playerDto) {
        Player player = new Player();
        player.setName(playerDto.name());
        return player;

    }
}

package com.prtech.game_library_hb.model.dto;

public record CreateMatchPlayerDTO(
        PlayerNameDTO player,
        Integer goals
) {
}

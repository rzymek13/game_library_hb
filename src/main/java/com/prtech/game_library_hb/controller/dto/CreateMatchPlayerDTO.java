package com.prtech.game_library_hb.controller.dto;

public record CreateMatchPlayerDTO(
        PlayerDto player,
        Integer goals
) {
}

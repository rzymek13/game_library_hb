package com.prtech.game_library_hb.controller.dto;

public record MatchPlayerDto (Long id,
                               String playerName,
                               Integer goals,
                                Integer matchId)
{
}

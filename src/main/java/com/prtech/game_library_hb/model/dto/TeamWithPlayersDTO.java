package com.prtech.game_library_hb.model.dto;

import java.util.List;

public record TeamWithPlayersDTO(Long id, String name, List<PlayerNameDTO> listOfPlayers) {
}

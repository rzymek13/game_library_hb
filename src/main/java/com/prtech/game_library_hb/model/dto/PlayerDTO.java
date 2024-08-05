package com.prtech.game_library_hb.model.dto;

import lombok.Data;


public record PlayerDTO (Long id, String name, TeamNameDTO teamName) {
}

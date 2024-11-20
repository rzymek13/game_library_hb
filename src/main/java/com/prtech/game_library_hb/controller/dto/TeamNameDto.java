package com.prtech.game_library_hb.controller.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TeamNameDto(
        @NotNull
        @Size(min = 4, max = 200, message = "About Me must be between 10 and 200 characters")
        String name) {
}

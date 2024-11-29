package com.prtech.game_library_hb.controller;

import com.prtech.game_library_hb.model.Player;
import com.prtech.game_library_hb.controller.dto.PlayerDto;
import com.prtech.game_library_hb.controller.dto.PlayerMapper;
import com.prtech.game_library_hb.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.prtech.game_library_hb.controller.dto.PlayerMapper.mapPlayerToDto;


@Slf4j
@CrossOrigin
@RestController
public class PlayerController {
    private final PlayerService playerService;


    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping(value = "/handball/players")
    public List<PlayerDto> getAllPlayers() {
        return playerService.getAllPlayers().stream()
                .map(PlayerMapper::mapPlayerToDto)
                .toList();
    }

    @GetMapping("/handball/players/{id}")
    PlayerDto readPlayer(@PathVariable Long id) {
        log.info("Player with id: " + id);
        return mapPlayerToDto(playerService.getById(id));
    }
    //TODO : refactor
    @GetMapping("/handball/playersTeam/{id}")
    List<PlayerDto> getPlayersByTeamId(@PathVariable Long id) {
        log.info("Players in team with id: " + id);
        return playerService.getAllPlayers().stream()
                .filter(player -> player.getTeam().getId().equals(id))
                .map(PlayerMapper::mapPlayerToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/handball/players")
    public Player createPlayer(@RequestBody PlayerDto playerDto) {
        return playerService.savePlayer(playerDto);
    }

    @PostMapping("/handball/players/addAll/{teamId}")
    public List<Player> addAllPlayers(@PathVariable Long teamId, @RequestBody List<PlayerDto> listOfPlayers) {
        return playerService.addAllPlayers(teamId, listOfPlayers);
    }


    @DeleteMapping("/handball/players/{id}")
    ResponseEntity<?> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayerById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/handballplayers/deleteAll")
    ResponseEntity<?> deleteAllPlayers() {
        playerService.deleteAllPlayers();
        return ResponseEntity.noContent().build();
    }
}

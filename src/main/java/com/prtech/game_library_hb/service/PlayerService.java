package com.prtech.game_library_hb.service;

import com.prtech.game_library_hb.model.Player;
import com.prtech.game_library_hb.model.Team;
import com.prtech.game_library_hb.controller.dto.PlayerDto;
import com.prtech.game_library_hb.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class PlayerService {
    private PlayerRepository playerRepository;
    private TeamService teamService;

    public PlayerService(PlayerRepository playerRepository, TeamService teamService) {
        this.playerRepository = playerRepository;
        this.teamService = teamService;
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getById(Long id) {
        return playerRepository.findAll().stream()
                .filter(player -> player.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("nie znaleziono takiego zawonika" + id));
    }
    public Set<Player> getByTeamId(Long teamId) {
        return playerRepository.findAll().stream()
                .filter(player -> player.getTeam().getId() == teamId)
                .collect(Collectors.toSet());

    }
    public Player getPlayerByName(String name) {
        return playerRepository.findAll().stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("nie znaleziono takiego zawonika" + name));
    }

    public Player savePlayer(PlayerDto playerDto) {
        Team team = teamService.getTeamByName(playerDto.teamName()); // or handle the case when team is not found

        Player player = new Player();
        player.setName(playerDto.name());
        player.setTeam(team);
        return playerRepository.save(player);
    }

    public List<Player> addAllPlayers(Long teamId, List<PlayerDto> listOfPlayers) {
        Team team = teamService.getTeamById(teamId);

        return listOfPlayers.stream().map(playerNameDTO -> {
            Player player = new Player();
            player.setName(playerNameDTO.name());
            player.setTeam(team);
            return playerRepository.save(player);
        }).collect(Collectors.toList());
    }

    public void deletePlayerById(Long id) {
        playerRepository.deleteById(id);
    }

    public void deleteAllPlayers() {
        playerRepository.deleteAll();
    }
}

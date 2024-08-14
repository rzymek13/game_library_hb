package com.prtech.game_library_hb.service;

import com.prtech.game_library_hb.model.Player;
import com.prtech.game_library_hb.model.Team;
import com.prtech.game_library_hb.model.dto.PlayerDto;
import com.prtech.game_library_hb.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
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
        return playerRepository.findAll().stream().filter(player -> player.getId() == id).findFirst().get();
    }

    public Player savePlayer(PlayerDto playerDto) {
        Team team = teamService.getAllTeams().stream()
                .filter(t -> t.getName().equals(playerDto.teamName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Team nie może być nullem")); // or handle the case when team is not found

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

package com.prtech.game_library_hb.controller;

import com.prtech.game_library_hb.controller.dto.PlayerDto;
import com.prtech.game_library_hb.model.Player;
import com.prtech.game_library_hb.model.Standings;
import com.prtech.game_library_hb.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@Slf4j
public class ViewController {

    private final TeamService teamService;
    private final PlayerService playerService;
    private final MatchService matchService;
    private final MatchPlayerService matchPlayerService;
    private final StandingsService standingsService;

    public ViewController(TeamService teamService, PlayerService playerService, MatchService matchService, MatchPlayerService matchPlayerService, StandingsService standingsService) {
        this.teamService = teamService;
        this.playerService = playerService;
        this.matchService = matchService;
        this.matchPlayerService = matchPlayerService;
        this.standingsService = standingsService;
    }

    @GetMapping({"/", "/home"})
    public String indexView(Model model) {
        return "index";
    }

    @GetMapping("/team/{id}")
    public String teamView(@PathVariable Long id, Model model) {
        model.addAttribute("team", teamService.getTeamById(id));
        model.addAttribute("players", playerService.getByTeamId(id));
        
        // Ensure standings are up to date before fetching
        standingsService.createStandings(); 
        Standings standings = standingsService.getStandingsByTeamId(id);
        
        // If standings not found (e.g. team has no standings entry yet), create a dummy one with 0 stats
        if (standings == null) {
            standings = new Standings();
            standings.setPoints(0);
            standings.setGoalsScored(0);
            standings.setGoalsConceded(0);
        }
        model.addAttribute("standings", standings);

        return "team";
    }

    @PostMapping("/team/{id}/addPlayer")
    public String addPlayer(@PathVariable Long id, @RequestParam String name) {
        playerService.savePlayer(new PlayerDto(null, name, teamService.getTeamById(id).getName()));
        return String.format("redirect:/team/%d", id);
    }

    @GetMapping("/player/{id}/delete")
    public String deletePlayer(@PathVariable Long id) {
        Long teamId = playerService.getById(id).getTeam().getId();
        playerService.deletePlayerById(id);
        log.info("Player with id: " + id + " has been deleted");
        return String.format("redirect:/team/%d", teamId);
    }

    @GetMapping("/scorers")
    public String scorersView(Model model) {
        model.addAttribute("scorers", matchPlayerService.getAllScorers());
        return "scorers";
    }
    @GetMapping("/standings")
    public String standingsView(Model model) {
        model.addAttribute("standings", standingsService.createStandings());
        model.addAttribute("teams", teamService.getAllTeams());
        model.addAttribute("teambr", playerService.getByTeamId(1L));
//        model.addAttribute("DAP SENIOR", playerService.getByTeamId(2L));
//        model.addAttribute("EKOSERWIS DAMY RADE INOWROCLAW", playerService.getByTeamId(3L));
//        model.addAttribute("AZS WLOCLAWEK", playerService.getByTeamId(4L));
//        model.addAttribute("UKS ALFA 99 STRZELNO", playerService.getByTeamId(5L));
//        model.addAttribute("MKS GRUDZIADZ II", playerService.getByTeamId(6L));
//        model.addAttribute("UKW II BYDGOSZCZ", playerService.getByTeamId(7L));
        return "standings";
    }

    @GetMapping("/addmatch")
    public String addMatchView(Model model) {
        model.addAttribute("teams", teamService.getAllTeams());
        return "addmatch";
    }

    @GetMapping("/results")
    public String resultsView(Model model) {
        model.addAttribute("matches", matchService.readAllMatches());
        return "results";
    }

    @GetMapping("/api/team/{id}/players")
    @ResponseBody
    public ResponseEntity<List<Player>> getTeamPlayers(@PathVariable Long id) {
        List<Player> players = playerService.getByTeamId(id);
        return ResponseEntity.ok(players);
    }
}

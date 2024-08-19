package com.prtech.game_library_hb.service;

import com.prtech.game_library_hb.controller.dto.MatchDto;
import com.prtech.game_library_hb.controller.dto.MatchMapper;
import com.prtech.game_library_hb.controller.dto.MatchPlayerDto;
import com.prtech.game_library_hb.controller.dto.ReadMatchDto;
import com.prtech.game_library_hb.model.Match;
import com.prtech.game_library_hb.model.MatchPlayer;
import com.prtech.game_library_hb.model.Player;
import com.prtech.game_library_hb.model.Team;
import com.prtech.game_library_hb.repository.MatchPlayerRepository;
import com.prtech.game_library_hb.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final TeamService teamService;
    private final MatchPlayerRepository matchPlayerRepository;
    private final PlayerService playerService;
    private static final Scanner SCANNER;
    private static Long matchIdFromFile;
    private static final File FILE_WITH_MATCH_ID = new File("src/main/java/com/prtech/game_library_hb/utill/last_match_id");

    static {
        try {
            SCANNER = new Scanner(FILE_WITH_MATCH_ID);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found");
        }
    }

    public MatchService(MatchRepository matchRepository, TeamService teamService, MatchPlayerRepository matchPlayerRepository, PlayerService playerService) {
        this.matchRepository = matchRepository;
        this.teamService = teamService;
        this.matchPlayerRepository = matchPlayerRepository;
        this.playerService = playerService;
    }

    public List<ReadMatchDto> getAllMatches() {
        return MatchMapper.mapMatchesToDtos(matchRepository.findAll());
    }

    //    private Long latestMatchId() {
//        return matchRepository.findAll().stream()
//                .map(Match::getId)
//                .max(Comparator.naturalOrder())
//                .orElse(0L);
//    }
    public static Long readLatestMatchId() {
        while (SCANNER.hasNext()) {
            matchIdFromFile = SCANNER.nextLong();
        }
        return matchIdFromFile;
    }

    public static void incrementMatchId() {
        try (FileWriter fw = new FileWriter(FILE_WITH_MATCH_ID, false);) {
            fw.write(String.valueOf(readLatestMatchId() + 1));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Match saveMatch(MatchDto matchDto) {
        Team homeTeam = teamService.getAllTeams().stream()
                .filter(team -> team.getName().equals(matchDto.homeTeam()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("HomeTeam nie może być nullem"));

        Team awayTeam = teamService.getAllTeams().stream()
                .filter(team -> team.getName().equals(matchDto.awayTeam()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("AwayTeam nie może być nullem"));
        Match match = new Match();
        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
        match.setHomeTeamGoals(matchDto.homeTeamGoals());
        match.setAwayTeamGoals(matchDto.awayTeamGoals());
        match.setResult(matchDto.result());
        match.setHomeTeamPenaltyGoals(matchDto.homeTeamPenaltyGoals());
        match.setAwayTeamPenaltyGoals(matchDto.awayTeamPenaltyGoals());

        Set<MatchPlayerDto> matchPlayersDtos = matchDto.matchPlayers();
        for (MatchPlayerDto matchPlayerDto : matchPlayersDtos) {
            MatchPlayer matchPlayer = new MatchPlayer();
            Player player = playerService.getAllPlayers().stream()
                    .filter(player1 -> player1.getName().equals(matchPlayerDto.playerName()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Player nie może być nullem"));
            matchPlayer.setPlayer(player);
            matchPlayer.setGoals(matchPlayerDto.goals());
            matchPlayer.setMatchId(readLatestMatchId());
            matchPlayerRepository.save(matchPlayer);
            //ustaw id w pliku
            incrementMatchId();
        }
        return matchRepository.save(match);
    }

    public void deleteAllMatches() {
        matchRepository.deleteAll();

    }

}

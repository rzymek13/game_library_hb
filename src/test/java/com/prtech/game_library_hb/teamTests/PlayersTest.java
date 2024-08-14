package com.prtech.game_library_hb.teamTests;

import com.prtech.game_library_hb.controller.PlayerController;
import com.prtech.game_library_hb.model.Player;
import com.prtech.game_library_hb.model.dto.PlayerDto;
import com.prtech.game_library_hb.model.dto.TeamNameDTO;
import com.prtech.game_library_hb.repository.PlayerRepository;
import com.prtech.game_library_hb.service.PlayerService;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static io.restassured.path.json.JsonPath.from;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class PlayersTest {
    @LocalServerPort
    private int port;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
    }

//    @Test
//    void testPostGetAndDeletePlayer() {
//        //Given
//        String response = RestAssured.given().port(port).auth().none()
//                .when()
//                .contentType("application/json")
//                .body("{\"name\" : \"testPlayer1\"}")
//                .post("/players")
//                .asString();
//
//        log.info(response);
//        int playerToDelete = from(response).getInt("id");
//        System.out.println(playerToDelete);
//
//        //When
//        RestAssured.given().port(port).auth().none()
//                .when()
//                .contentType("application/json")
//                .delete("/players/" + playerToDelete)
//                .then()
//        //Then
//                .statusCode(204);
//
//
//    }
@Mock
private PlayerService playerService;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerController playerController;

    @Test
    public void testCreatePlayerWithExistingNameButDifferentId() {
        // Given

        playerService.savePlayer(new PlayerDto(null, "testPlayer1", "test" ));


        // When

//        System.out.println(playerController.createPlayer());
        // Then

    }
}

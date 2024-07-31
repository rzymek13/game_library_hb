package com.prtech.game_library_hb.teamTests;

import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.path.json.JsonPath.from;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class PlayersTest {
    @LocalServerPort
    private int port;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    void testPostGetAndDeletePlayer() {
        //Given
        String response = RestAssured.given().port(port).auth().none()
                .when()
                .contentType("application/json")
                .body("{\"name\" : \"testPlayer1\"}")
                .post("/players/1")
                .asString();

        log.info(response);
        int playerToDelete = from(response).getInt("id");
        System.out.println(playerToDelete);

        //When
        RestAssured.given().port(port).auth().none()
                .when()
                .contentType("application/json")
                .delete("/players/" + playerToDelete)
                .then()
        //Then
                .statusCode(204);


    }
}

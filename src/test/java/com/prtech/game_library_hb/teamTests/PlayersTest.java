package com.prtech.game_library_hb.teamTests;

import com.prtech.game_library_hb.teamTests.utilities.TestManager;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
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
        TestManager.copyDatabaseFile();
        TestManager.setDatabaseProperty();
        RestAssured.baseURI = "http://localhost";
    }
    @Test
    void getAllPlayers() {
        //When
        RestAssured.given().port(port).auth().none()
                .when()
                .get("/handball/players")
                .then()
        //Then
               .statusCode(200);
    }

    @Test
    void testPostGetAndDeletePlayer() {
        //Given
        String response = RestAssured.given().port(port).auth().none()
                .when()
                .contentType("application/json")
                .body("{\"name\" : \"testPlayer1\", \"teamName\" : \"MKS BRODNICA\"}")
                .post("/handball/players")
                .asString();

        log.info(response);
        int playerToDelete = from(response).getInt("id");
        System.out.println(playerToDelete);

        //When
        RestAssured.given().port(port).auth().none()
                .when()
                .contentType("application/json")
                .delete("/handball/players/" + playerToDelete)
                .then()
        //Then
                .statusCode(204);
    }
    @AfterAll
    public static void teardown() {
        TestManager.restoreDatabaseProperty();
        TestManager.deleteCopyOfDatabaseFile();
    }
}

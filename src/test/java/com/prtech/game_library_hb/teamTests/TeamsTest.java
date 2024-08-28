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
import static org.hamcrest.Matchers.equalTo;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class TeamsTest {

    @LocalServerPort
    private int port;

    @BeforeAll
    public static void setup() {
//        TestManager.copyDatabaseFile();
//        TestManager.setDatabaseProperty();
        RestAssured.baseURI = "http://localhost";
    }

//    @Test
//    public void getTeamsTest() {
//        //When
//        RestAssured.given().port(port).auth().none()
//                .when()
//                .get("/handball/teams")
//                .then()
//                //Then
//                .statusCode(200)
//                .body("[0].id", equalTo(1))
//                .body("[0].name", equalTo("MKS BRODNICA"))
//                .body("[1].name", equalTo("DAP SENIOR"))
//                .body("[2].name", equalTo("EKOSERWIS DAMY RADE INOWROCLAW"))
//                .body("[3].name", equalTo("AZS WLOCLAWEK"))
//                .body("[4].name", equalTo("UKS ALFA 99 STRZELNO"))
//                .body("[5].name", equalTo("UKW II BYDGOSZCZ"))
//                .body("[6].name", equalTo("MKS GRUDZIADZ II"))
//                .body("size()", equalTo(7))
//                .log().all();
//    }
//
//    @Test
//    public void testPostGetAndDeleteTeam() {
//        //Given
//        String response = RestAssured.given().port(port).auth().none()
//                .when()
//                .contentType("application/json")
//                .body("{\"name\" : \"test1\"}")
//                .post("/handball/teams")
//                .asString();
//
//        log.info(response);
//        int teamToDelete = from(response).getInt("id");
//
//        //When
//        RestAssured.given().port(port).auth().none()
//                .when()
//                .contentType("application/json")
//                .delete("/handball/teams/" + teamToDelete)
//                .then()
//                //Then
//                .statusCode(204);
//    }

    @AfterAll
    public static void teardown() {
//        TestManager.restoreDatabaseProperty();
//        TestManager.deleteCopyOfDatabaseFile();
    }
}


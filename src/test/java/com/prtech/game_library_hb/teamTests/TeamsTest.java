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
class TeamsTest {

    @LocalServerPort
    private int port;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
    }

//    @Test
//    void testPostGetAndDeleteTeam() {
//        //Given
//        String response = RestAssured.given().port(port).auth().none()
//                .when()
//                .contentType("application/json")
//                .body("{\"name\" : \"test1\"}")
//                .post("/teams")
//                .asString();
//
//        log.info(response);
//        int teamToDelete = from(response).getInt("id");
//
//        //When
//        RestAssured.given().port(port).auth().none()
//                .when()
//                .contentType("application/json")
//                .delete("/teams/" + teamToDelete)
//                .then()
//        //Then
//                .statusCode(204);
//
//
//    }



//        snippets
//                .body("[0].id", equalTo(2))
//                .body("size()", equalTo(7))
//                .log().all();


}


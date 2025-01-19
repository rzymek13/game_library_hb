package com.prtech.game_library_hb.handballTest.rest_assured;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;





public class CreateTeamTest extends ApiTestBase {

    private Integer createdTeamId;
//TODO zaimplementuj podstawianie bazy h2 na testy



    @Test
    void addTeamTest() {
        String newTeamJson = "{ \"name\": \"New Team\" }";

        ValidatableResponse response = RestAssured.given(requestSpec)
                .auth()
                .basic("user", "password")
                .contentType("application/json")
                .body(newTeamJson)
                .when()
                .post("/teams")
                .then()
                .statusCode(200)
                .body("name", equalTo("New Team"))
                .log()
                .all();

        createdTeamId = response.extract().path("id");
        System.out.println(createdTeamId + " id");


        // Verify the team was added

    }

    @AfterEach
    void afterTest() {
        RestAssured.given(requestSpec)
                .auth()
                .basic("user", "password")
                .when()
                .delete("/teams/" + createdTeamId)
                .then()
                .statusCode(204)
                .log().all();
        System.out.println("Team with id: " + createdTeamId + " was deleted");
    }
}
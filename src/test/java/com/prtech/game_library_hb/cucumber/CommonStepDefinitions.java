package com.prtech.game_library_hb.cucumber;

import com.prtech.game_library_hb.controller.dto.TeamNameDto;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.RestAssured.given;

public class CommonStepDefinitions {

    @Autowired
    private TestContext testContext;

    @Given("A team named {string} exists")
    public void a_team_named_exists(String teamName) {
        Response response = given()
                .spec(testContext.getRequestSpec())
                .contentType("application/json")
                .body(new TeamNameDto(teamName))
        .when()
                .post("/handball/teams");
        
        int teamId = response.then().extract().path("id");
        testContext.setPayload(teamId); // Store teamId for later use
    }
}

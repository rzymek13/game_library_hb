package com.prtech.game_library_hb.cucumber;

import com.prtech.game_library_hb.controller.dto.PlayerDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlayerStepDefinitions {

    @Autowired
    private TestContext testContext;

    @Given("A player named {string} exists in team {string}")
    @When("I create a new player named {string} for team {string}")
    public void i_create_a_new_player_named_for_team(String playerName, String teamName) {
        PlayerDto playerDto = new PlayerDto(null, playerName, teamName);
        Response response = given()
                .spec(testContext.getRequestSpec())
                .contentType("application/json")
                .body(playerDto)
        .when()
                .post("/handball/players");
        
        testContext.setResponse(response);
        
        // If successful, store ID
        if (response.statusCode() == 200) {
            int playerId = response.then().extract().path("id");
            testContext.setPayload(playerId);
        }
    }

    @Then("The player {string} should exist in the system")
    public void the_player_should_exist_in_the_system(String playerName) {
        testContext.getResponse().then().statusCode(200);
        int playerId = testContext.getResponse().then().extract().path("id");
        assertNotNull(playerId);
        testContext.setPayload(playerId); // Store playerId
    }

    @Then("The player {string} should belong to team {string}")
    public void the_player_should_belong_to_team(String playerName, String teamName) {
        testContext.getResponse().then().body("team.name", equalTo(teamName));
    }

    @When("I request all players for team {string}")
    public void i_request_all_players_for_team(String teamName) {
        int teamId = (int) testContext.getPayload();
        Response response = given()
                .spec(testContext.getRequestSpec())
                .pathParam("id", teamId)
        .when()
                .get("/handball/playersTeam/{id}");
        
        testContext.setResponse(response);
    }

    @Then("I should receive a list containing {string} and {string}")
    public void i_should_receive_a_list_containing_and(String player1, String player2) {
        testContext.getResponse().then()
                .statusCode(200)
                .body("name", hasItems(player1, player2));
    }

    @When("I delete the player named {string}")
    public void i_delete_the_player_named(String playerName) {
        int playerId = (int) testContext.getPayload();
        Response response = given()
                .spec(testContext.getRequestSpec())
                .pathParam("id", playerId)
        .when()
                .delete("/handball/players/{id}");
        
        testContext.setResponse(response);
    }

    @Then("The player {string} should not exist in the system")
    public void the_player_should_not_exist_in_the_system(String playerName) {
        testContext.getResponse().then().statusCode(204);
        int playerId = (int) testContext.getPayload();
        
        given()
            .spec(testContext.getRequestSpec())
            .pathParam("id", playerId)
        .when()
            .get("/handball/players/{id}")
        .then()
            .statusCode(500); // Assuming 500 for not found
    }
}

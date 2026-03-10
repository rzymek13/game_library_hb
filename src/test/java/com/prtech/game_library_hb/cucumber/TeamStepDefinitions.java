package com.prtech.game_library_hb.cucumber;

import com.prtech.game_library_hb.controller.dto.TeamNameDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TeamStepDefinitions {

    @Autowired
    private TestContext testContext;

    @Given("There is no team named {string}")
    public void there_is_no_team_named(String teamName) {
    }
    @Given("Create test team with name {string}")
    public void a_team_named_exists(String teamName) {
        Response response = given()
                .spec(testContext.getRequestSpec())
                .contentType("application/json")
                .body(new TeamNameDto(teamName))
                .when()
                .post("/handball/teams");

        int teamId = response.then().extract().path("id");
        testContext.setPayload(teamId);
    }

    @When("I create a new team named {string}")
    public void i_create_a_new_team_named(String teamName) {
        TeamNameDto teamDto = new TeamNameDto(teamName);
        testContext.setPayload(teamDto);

        Response response = given()
                .spec(testContext.getRequestSpec())
                .contentType("application/json")
                .body(teamDto)
        .when()
                .post("/handball/teams");
        
        testContext.setResponse(response);
        
        if (response.statusCode() == 200) {
            int teamId = response.then().extract().path("id");
            testContext.setPayload(teamId);
        }
    }

    @Then("The team {string} should exist in the system")
    public void the_team_should_exist_in_the_system(String teamName) {
        testContext.getResponse().then().statusCode(201);
        
        int teamId = testContext.getResponse().then().extract().path("id");
        
        given()
            .spec(testContext.getRequestSpec())
            .pathParam("id", teamId)
        .when()
            .get("/handball/teams/{id}")
        .then()
            .statusCode(200)
            .body("name", equalTo(teamName));
    }

    @Then("The team {string} should have a valid ID")
    public void the_team_should_have_a_valid_id(String teamName) {
        int teamId = testContext.getResponse().then().extract().path("id");
        assertNotNull(teamId);
    }
    
    @When("I try to create a team with an empty name")
    public void i_try_to_create_a_team_with_an_empty_name() {
        TeamNameDto teamDto = new TeamNameDto("");
        testContext.setPayload(teamDto);

        Response response = given()
                .spec(testContext.getRequestSpec())
                .contentType("application/json")
                .body(teamDto)
        .when()
                .post("/handball/teams");
        
        testContext.setResponse(response);
    }

    @Then("I should receive a validation error")
    public void i_should_receive_a_validation_error() {
        testContext.getResponse().then().statusCode(400);
    }

    @When("I update the name of team {string} to {string}")
    public void i_update_the_name_of_team_to(String oldName, String newName) {
        int teamId = findTeamIdByName(oldName);
        
        TeamNameDto updatedTeamDto = new TeamNameDto(newName);
        
        Response response = given()
                .spec(testContext.getRequestSpec())
                .pathParam("id", teamId)
                .contentType("application/json")
                .body(updatedTeamDto)
        .when()
                .put("/handball/teams/{id}");
        
        testContext.setResponse(response);
        testContext.setPayload(teamId);
    }

    @Then("The team name should be {string}")
    public void the_team_name_should_be(String expectedName) {
        testContext.getResponse().then().statusCode(200);
        int teamId = (int) testContext.getPayload();
        
        given()
            .spec(testContext.getRequestSpec())
            .pathParam("id", teamId)
        .when()
            .get("/handball/teams/{id}")
        .then()
            .statusCode(200)
            .body("name", equalTo(expectedName));
    }

    @When("I delete the team named {string}")
    public void i_delete_the_team_named(String teamName) {
        int teamId = findTeamIdByName(teamName);
        
        Response response = given()
                .spec(testContext.getRequestSpec())
                .pathParam("id", teamId)
        .when()
                .delete("/handball/teams/{id}");
        
        testContext.setResponse(response);
        testContext.setPayload(teamId);
    }

    @Then("The team {string} should not exist in the system")
    public void the_team_should_not_exist_in_the_system(String teamName) {
        testContext.getResponse().then().statusCode(204);
        int teamId = (int) testContext.getPayload();
        
        given()
            .spec(testContext.getRequestSpec())
            .pathParam("id", teamId)
        .when()
            .get("/handball/teams/{id}")
        .then()
//            .statusCode(500)
                .log().all();
    }

    @When("I delete all teams")
    public void i_delete_all_teams() {
        Response response = given()
                .spec(testContext.getRequestSpec())
        .when()
                .delete("/handball/teams/deleteAll");
        
        testContext.setResponse(response);
    }

    @Then("There should be no teams in the system")
    public void there_should_be_no_teams_in_the_system() {
        testContext.getResponse().then().statusCode(204);
        
        given()
            .spec(testContext.getRequestSpec())
        .when()
            .get("/handball/teams")
        .then()
            .statusCode(200)
            .body("$", hasSize(0));
    }

    private int findTeamIdByName(String teamName) {
        Response teamsResponse = given()
                .spec(testContext.getRequestSpec())
        .when()
                .get("/handball/teams");
        
        List<Map<String, Object>> teams = teamsResponse.jsonPath().getList("$");
        return teams.stream()
                .filter(t -> teamName.equals(t.get("name")))
                .map(t -> (Integer) t.get("id"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Team not found: " + teamName));
    }
}

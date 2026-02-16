package com.prtech.game_library_hb.cucumber;

import com.prtech.game_library_hb.controller.dto.TeamNameDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TeamStepDefinitions {

    @Autowired
    private TestContext testContext;

    @Given("There is no team named {string}")
    public void there_is_no_team_named(String teamName) {
        // Logic to ensure team doesn't exist (optional for H2 with clean state)
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
    }

    @Then("The team {string} should exist in the system")
    public void the_team_should_exist_in_the_system(String teamName) {
        testContext.getResponse().then().statusCode(200);
        
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
}

package com.prtech.game_library_hb.cucumber;

import com.prtech.game_library_hb.controller.dto.MatchDto;
import com.prtech.game_library_hb.controller.dto.MatchPlayerDto;
import com.prtech.game_library_hb.controller.dto.TeamNameDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MatchStepDefinitions {

    @Autowired
    private TestContext testContext;

    // "A player named {string} exists in team {string}" is now in PlayerStepDefinitions

    @When("I create a match between {string} and {string} with score {int}-{int}")
    public void i_create_a_match_between_and_with_score(String homeTeam, String awayTeam, int homeGoals, int awayGoals) {
        MatchDto matchDto = new MatchDto(
                null,
                new TeamNameDto(homeTeam),
                new TeamNameDto(awayTeam),
                homeGoals,
                awayGoals,
                1, // Result (1 = Home Win)
                0,
                0,
                Set.of() // No scorers yet
        );
        testContext.setPayload(matchDto);
    }

    @When("Player {string} scores {int} goals")
    public void player_scores_goals(String playerName, int goals) {
        MatchDto matchDto = (MatchDto) testContext.getPayload();
        MatchPlayerDto scorer = new MatchPlayerDto(playerName, goals, matchDto.homeTeam().name());
        
        MatchDto updatedMatchDto = new MatchDto(
                matchDto.id(),
                matchDto.homeTeam(),
                matchDto.awayTeam(),
                matchDto.homeTeamGoals(),
                matchDto.awayTeamGoals(),
                matchDto.result(),
                matchDto.homeTeamPenaltyGoals(),
                matchDto.awayTeamPenaltyGoals(),
                Set.of(scorer)
        );
        
        testContext.setPayload(updatedMatchDto);
    }

    @Then("The match should be created successfully")
    public void the_match_should_be_created_successfully() {
        MatchDto matchDto = (MatchDto) testContext.getPayload();
        Response response = given()
                .spec(testContext.getRequestSpec())
                .contentType("application/json")
                .body(matchDto)
        .when()
                .post("/handball/matches");
        
        testContext.setResponse(response);
        testContext.getResponse().then().statusCode(200);
        int matchId = testContext.getResponse().then().extract().path("id");
        assertNotNull(matchId);
        testContext.setPayload(matchId); // Store matchId
    }

    @Then("The home team should have {int} goals")
    public void the_home_team_should_have_goals(int goals) {
        testContext.getResponse().then().body("homeTeamGoals", equalTo(goals));
    }

    @Then("The away team should have {int} goals")
    public void the_away_team_should_have_goals(int goals) {
        testContext.getResponse().then().body("awayTeamGoals", equalTo(goals));
    }

    @Then("The player {string} should be listed as a top scorer with {int} goals")
    public void the_player_should_be_listed_as_a_top_scorer_with_goals(String playerName, int goals) {
        Response response = given()
                .spec(testContext.getRequestSpec())
        .when()
                .get("/handball/scorers");
        
        testContext.setResponse(response);
        testContext.getResponse().then()
                .statusCode(200)
                .body("playerName", hasItem(playerName))
                .body("goals", hasItem(goals));
    }

    @Given("A match between {string} and {string} exists")
    public void a_match_between_and_exists(String homeTeam, String awayTeam) {
        // Create teams
        given().spec(testContext.getRequestSpec()).contentType("application/json").body(new TeamNameDto(homeTeam)).when().post("/handball/teams");
        given().spec(testContext.getRequestSpec()).contentType("application/json").body(new TeamNameDto(awayTeam)).when().post("/handball/teams");
        
        // Create match
        MatchDto matchDto = new MatchDto(
                null,
                new TeamNameDto(homeTeam),
                new TeamNameDto(awayTeam),
                0, 0, 0, 0, 0, Set.of()
        );
        
        Response response = given()
                .spec(testContext.getRequestSpec())
                .contentType("application/json")
                .body(matchDto)
        .when()
                .post("/handball/matches");
        
        int matchId = response.then().extract().path("id");
        testContext.setPayload(matchId); // Store matchId
    }

    @When("I delete the match")
    public void i_delete_the_match() {
        int matchId = (int) testContext.getPayload();
        Response response = given()
                .spec(testContext.getRequestSpec())
                .pathParam("id", matchId)
        .when()
                .delete("/handball/matches/{id}");
        
        testContext.setResponse(response);
    }

    @Then("The match should not exist in the system")
    public void the_match_should_not_exist_in_the_system() {
        testContext.getResponse().then().statusCode(200);
        int matchId = (int) testContext.getPayload();
        
        given()
            .spec(testContext.getRequestSpec())
        .when()
            .get("/handball/matches")
        .then()
            .statusCode(200)
            .body("id", not(hasItem(matchId)));
    }
}

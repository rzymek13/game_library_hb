package com.prtech.game_library_hb.teamTests;


import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class getAllTeamsTest {

    @LocalServerPort
    private int port;

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost";
	}

    @Test
    public void myTest() {
        assertThat(RestAssured.config(), instanceOf(RestAssuredConfig.class));
    }

    @Test
    public void testGetAllTeams() {
//		Response response = RestAssured.
//		given().auth().none()
//				.get("http://localhost:" + port + "/teams");
////				.then()
////				.statusCode(200).
////				.body("$[0].id", equalTo(2));
//		System.out.println(response.body().prettyPrint());

        RestAssured
				.given()
					.port(port)
					.auth().none()
                .when()
                	.get("/teams")
                .then()
                	.statusCode(200)
					.assertThat()
					.body("[0].id", equalTo(2))
                	.log()
                	.all();

    }

}

package com.prtech.game_library_hb.teamTests.rest_assured;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

class TeamTest extends ApiTestBase {

    @Test
    void getAllteamsTest() {

        RestAssured.given(requestSpec)
                .auth()
                .basic("user", "password")
                .when()
                .get("/teams")
                .then()
                .statusCode(200)
                .body("[0].name", equalTo("MKS BRODNICA"))
                .body("[2].name", equalTo("EKOSERWIS DAMY RADE INOWROCLAW"))
                .body("size()", is(7))
                .log().all();
    }

}
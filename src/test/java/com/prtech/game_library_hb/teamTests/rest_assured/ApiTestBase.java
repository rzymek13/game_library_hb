package com.prtech.game_library_hb.teamTests.rest_assured;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ApiTestBase {
    protected static final String BASE_URL = "http://localhost:8090/handball";

    protected RequestSpecification requestSpec;
    protected ResponseSpecification responseSpec;

    public ApiTestBase() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }
}
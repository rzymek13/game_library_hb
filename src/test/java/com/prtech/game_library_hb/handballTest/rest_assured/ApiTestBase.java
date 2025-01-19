package com.prtech.game_library_hb.handballTest.rest_assured;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;




public class ApiTestBase {
    protected static final String BASE_URL = "http://localhost:8090/handball";

    protected RequestSpecification requestSpec;

    public ApiTestBase() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .build();

    }
}
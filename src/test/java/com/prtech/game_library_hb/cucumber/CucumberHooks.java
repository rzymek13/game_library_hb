package com.prtech.game_library_hb.cucumber;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

public class CucumberHooks {

    @LocalServerPort
    private int port;

    @Autowired
    private TestContext testContext;

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

        testContext.setRequestSpec(new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(port)
                .setAuth(RestAssured.basic("user", "password"))
                .log(LogDetail.ALL)
                .build());
    }
}

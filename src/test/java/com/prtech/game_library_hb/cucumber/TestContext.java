package com.prtech.game_library_hb.cucumber;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import io.cucumber.spring.ScenarioScope;

@Component
@ScenarioScope
@Slf4j
@Getter
@Setter
public class TestContext {
    private RequestSpecification requestSpec;
    private Response response;
    private Object payload;

}

package com.prtech.game_library_hb.teamTests.api_test;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    static APIRequestContext request;
    static Playwright playwright;

    @BeforeAll
    public static void setup() {
        playwright = Playwright.create();

        request = playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL("http://localhost:8090/handball/"));
    }

    @AfterAll
    public static void tearDown() {
        if (playwright != null) {
            playwright.close();
        }
    }

    public static void main(String[] args) {
        PlayerTest playerTest = new PlayerTest();
        playerTest.getAllPlayers();
    }
}

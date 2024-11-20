package com.prtech.game_library_hb.teamTests.api_test;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import org.junit.jupiter.api.Test;

public class TeamTest extends BaseTest {

    APIRequestContext request = BaseTest.request;

    @Test
    void getAllTeams() {
        APIResponse response = request.get("teams");
        System.out.println(response.status());
        System.out.println(response.text());
    }
}


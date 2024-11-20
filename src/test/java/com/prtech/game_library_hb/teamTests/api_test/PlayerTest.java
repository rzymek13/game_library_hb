package com.prtech.game_library_hb.teamTests.api_test;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class PlayerTest extends BaseTest {
    APIRequestContext request = BaseTest.request;

    @Test
    void getAllPlayers() {
        APIResponse response = request.get("players");
        assertEquals(200, response.status());
        log.info(String.valueOf(response.status()));
        log.info(response.text());
    }

}

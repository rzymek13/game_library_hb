package com.prtech.game_library_hb.teamTests.ui_test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FirstTest {



    @Test
    void firstTest() {
        try (Playwright playwright = Playwright.create();
             Browser browser = playwright.chromium().launch()) {

            Page page = browser.newPage();
            page.navigate("http://localhost:8090/team/1");
            String title = page.title();
            assertEquals("Teams", title);
        }
    }
}

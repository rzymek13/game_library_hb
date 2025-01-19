package com.prtech.game_library_hb.handballTest.front;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest{


    @Test
    void loginTest() {
        getInstance().get("http://localhost:8090/home");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("user", "password");

        Assertions.assertEquals("http://localhost:8090/home?continue", driver.getCurrentUrl());
    }
}

package com.prtech.game_library_hb.handballTest.front;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    protected WebDriver driver;

    public WebDriver getInstance() {
        if (driver == null)
            return driver = new ChromeDriver();
        return driver;
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

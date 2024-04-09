package com.prtech.game_library_hb.utill;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.xml.xpath.XPath;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
public class PlayerDataHandler {
    private static final String MAIN_URL = "https://rozgrywki.zprp.pl/?Sezon=192&Rozgrywki=10948&Zespoly=1";

    WebDriver driver = new ChromeDriver();



    private void getPlayers() {
        driver.get(MAIN_URL);
    }
    private void getListXPathOfTeams() throws InterruptedException {
        for (int i = 1; i <7 ; i++) {
            String xpath = String.format("//*[@id=\"klubSzczegoly\"]//tr[%d]//a[2]",i);
            driver.findElement(By.xpath(xpath)).click();
            Thread.sleep(2000);
            driver.close();
        }

//        List<WebElement> list = driver.findElements(By.xpath("//table[@id=\"klubSzczegoly\"]//a[2]"));

    }


    public static void main(String[] args) throws InterruptedException {
        PlayerDataHandler playerDataHandler = new PlayerDataHandler();
        playerDataHandler.getPlayers();
        playerDataHandler.getListXPathOfTeams();
    }
}

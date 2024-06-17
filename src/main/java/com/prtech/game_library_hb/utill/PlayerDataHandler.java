package com.prtech.game_library_hb.utill;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;


@Slf4j
public class PlayerDataHandler {
    private static final String MAIN_URL = "https://rozgrywki.zprp.pl/?Sezon=192&Rozgrywki=10948&Zespoly=1";
    private final ObjectMapper objectMapper;
    private final WebDriver driver;


    public PlayerDataHandler() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        objectMapper = new ObjectMapper();
    }

    private void runApp() {
        driver.get(MAIN_URL);
    }

    private void retrieveListsOfPlayers() {
        for (int i = 1; i < getNumberOfTeams() + 1; i++) {
            String xpath = String.format("//*[@id=\"klubSzczegoly\"]//tr[%d]//a[2]", i);
            driver.findElement(By.xpath(xpath)).click();
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            getListOfPlayers();
            driver.close();
            driver.switchTo().window(tabs.get(0));
        }
        driver.quit();
    }

    private void getListOfPlayers() {
        String teamName = driver.findElement(By.xpath("//h3")).getText();
        ArrayList<String> players = new ArrayList<>();
        for (int i = 1; i < getNumberOfPlayers() + 1; i++) {
            String xpath = String.format("//div[@id='ListaZawodnikow']//tr[%d]/td[3]", i);
            String player = driver.findElement(By.xpath(xpath)).getText();
            players.add(player);
        }
        try (FileWriter file = new FileWriter("src/main/resources/data/players/" +System.currentTimeMillis() + teamName + ".json")) {
            file.write(objectMapper.writeValueAsString(players));
            log.info(teamName + players);
        } catch (IOException e) {
            log.info("Error occurred while saving player data for team: {}", teamName, e);
        }
    }

    private int getNumberOfPlayers() {
        String text = driver.findElement(By.xpath("//div[@id='ListaZawodnikow']//tr[last()]/td[1]")).getText();
        String sub = text.substring(0, text.length() - 1);
        return Integer.parseInt(sub);
    }

    private int getNumberOfTeams() {
        String text = driver.findElement(By.xpath("//table[@id='klubSzczegoly']//tr[last()]/td[1]")).getText();
        String sub = text.substring(0, text.length() - 1);
        return Integer.parseInt(sub);
    }


    public static void main(String[] args) throws InterruptedException {
        PlayerDataHandler playerDataHandler = new PlayerDataHandler();
        playerDataHandler.runApp();
        playerDataHandler.retrieveListsOfPlayers();
    }
}

package com.prtech.game_library_hb.framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = Configuration.class.getClassLoader().getResourceAsStream("application-test.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find test.properties");
            } else {
                properties.load(input);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getApiBaseUrl() {
        return properties.getProperty("api.base.url", "http://localhost:8090");
    }

    public static String getApiUsername() {
        return properties.getProperty("api.username", "user");
    }

    public static String getApiPassword() {
        return properties.getProperty("api.password", "password");
    }
}

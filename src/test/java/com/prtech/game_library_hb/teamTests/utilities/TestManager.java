package com.prtech.game_library_hb.teamTests.utilities;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

public class TestManager {
    private static final File DATABASE_FILE = new File("src/main/resources/db/testDB.mv.db");
    private static final File DATABASE_COPY_FILE = new File("src/main/resources/db/testDB_COPY.mv.db");
    private static final String PROPERTIES_FILE = "src/main/resources/application.properties";

    public static void copyDatabaseFile() {
        Path copied = Paths.get(DATABASE_COPY_FILE.getAbsolutePath());
        Path originalPath = Paths.get(DATABASE_FILE.getAbsolutePath());
        try {
            Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("złe dane plików");

        }
    }

    public static void deleteCopyOfDatabaseFile() {
        try {
            Files.deleteIfExists(DATABASE_COPY_FILE.toPath());
        } catch (IOException e) {
            throw new RuntimeException("failed to delete copy of database file");
        }
    }

    public static void setDatabaseProperty() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(PROPERTIES_FILE));
            props.setProperty("spring.datasource.url", "jdbc:h2:file:." + "/src/main/resources/db/testDB_COPY");
            props.store(new FileWriter(PROPERTIES_FILE), "zapisano");
        } catch (IOException e) {
            throw new RuntimeException("Failed to set database property");
        }
    }

    public static void restoreDatabaseProperty() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(PROPERTIES_FILE));
            props.setProperty("spring.datasource.url", "jdbc:h2:file:." + "/src/main/resources/db/testDB");
            props.store(new FileWriter(PROPERTIES_FILE), "zapisano");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

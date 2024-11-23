package ru.goth.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigLoaderTest {

    @Test
    public void getDriverName() {
        final String expected = "org.postgresql.Driver";

        ConfigLoader configLoader = new ConfigLoader();
        String actual = configLoader.getDriverName();

        assertEquals(expected, actual);
    }

    @Test
    public void getDbUrl() {
        final String expected = "jdbc:postgresql://localhost:5432/book_store";

        ConfigLoader configLoader = new ConfigLoader();
        String actual = configLoader.getDbUrl();

        assertEquals(expected, actual);
    }

    @Test
    public void getDbUsername() {
        final String expected = "postgres";

        ConfigLoader configLoader = new ConfigLoader();
        String actual = configLoader.getDbUsername();

        assertEquals(expected, actual);
    }

    @Test
    public void getDbPassword() {
        final String expected = "Bionicle871";

        ConfigLoader configLoader = new ConfigLoader();
        String actual = configLoader.getDbPassword();

        assertEquals(expected, actual);
    }
}
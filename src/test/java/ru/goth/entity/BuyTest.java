package ru.goth.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuyTest {

    @Test
    void getSetId() {
        final long expected = 1L;
        Buy buy = new Buy();
        buy.setId(expected);
        long actual = buy.getId();

        assertEquals(expected, actual);
    }

    @Test
    void getSetDescription() {
        final String expected = "Oleg";
        Buy buy = new Buy();
        buy.setDescription(expected);
        String actual = buy.getDescription();

        assertEquals(expected, actual);
    }

    @Test
    void getSetClient() {
        final String expected = "Oleg";
        Buy buy = new Buy();
        buy.setClient(expected);
        String actual = buy.getClient();

        assertEquals(expected, actual);
    }

    @Test
    void testToString() {
        Buy buy = new Buy();

        final String expected = "Buy{id=0, description='null', client='null'}";
        String actual = buy.toString();
        assertEquals(expected, actual);
    }
}
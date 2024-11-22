package ru.goth.entity.dto;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

class BuyDTOTest {

    @Test
    void getSetDescription() {
        final String expected = "Oleg";
        BuyDTO buy = new BuyDTO();
        buy.setDescription(expected);
        String actual = buy.getDescription();

        assertEquals(expected, actual);
    }

    @Test
    void getSetClient() {
        final String expected = "Oleg";
        BuyDTO buy = new BuyDTO();
        buy.setClient(expected);
        String actual = buy.getClient();

        assertEquals(expected, actual);
    }
}

package ru.goth.entity.dto;

import org.junit.jupiter.api.Test;
import ru.goth.entity.Buy;

import static org.junit.jupiter.api.Assertions.*;

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

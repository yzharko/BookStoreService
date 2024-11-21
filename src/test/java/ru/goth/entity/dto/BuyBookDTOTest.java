package ru.goth.entity.dto;

import org.junit.jupiter.api.Test;
import ru.goth.entity.Book;
import ru.goth.entity.Buy;
import ru.goth.entity.BuyBook;

import static org.junit.jupiter.api.Assertions.*;

class BuyBookDTOTest {

    @Test
    void getSetBuy() {
        final Buy expected = new Buy();
        BuyBookDTO buyBook = new BuyBookDTO();
        buyBook.setBuy(expected);
        Buy actual = buyBook.getBuy();
        assertEquals(expected, actual);
    }

    @Test
    void getSetBook() {
        final Book expected = new Book();
        BuyBookDTO buyBook = new BuyBookDTO();
        buyBook.setBook(expected);
        Book actual = buyBook.getBook();
        assertEquals(expected, actual);
    }

    @Test
    void getSetAmount() {
        final int expected = 1;
        BuyBookDTO buyBook = new BuyBookDTO();
        buyBook.setAmount(expected);
        int actual = buyBook.getAmount();

        assertEquals(expected, actual);
    }
}

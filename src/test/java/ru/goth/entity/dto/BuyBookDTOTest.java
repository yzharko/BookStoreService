package ru.goth.entity.dto;

import org.junit.Test;
import ru.goth.entity.Book;
import ru.goth.entity.Buy;

import static org.junit.Assert.assertEquals;

public class BuyBookDTOTest {

    @Test
    public void getSetBuy() {
        final Buy expected = new Buy();
        BuyBookDTO buyBook = new BuyBookDTO();
        buyBook.setBuy(expected);
        Buy actual = buyBook.getBuy();
        assertEquals(expected, actual);
    }

    @Test
    public void getSetBook() {
        final Book expected = new Book();
        BuyBookDTO buyBook = new BuyBookDTO();
        buyBook.setBook(expected);
        Book actual = buyBook.getBook();
        assertEquals(expected, actual);
    }

    @Test
    public void getSetAmount() {
        final int expected = 1;
        BuyBookDTO buyBook = new BuyBookDTO();
        buyBook.setAmount(expected);
        int actual = buyBook.getAmount();

        assertEquals(expected, actual);
    }
}

package ru.goth.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuyBookTest {

    @Test
    void getSetId() {
        final long expected = 1L;
        BuyBook buyBook = new BuyBook();
        buyBook.setId(expected);
        long actual = buyBook.getId();

        assertEquals(expected, actual);
    }

    @Test
    void getSetBuy() {
        final Buy expected = new Buy();
        BuyBook buyBook = new BuyBook();
        buyBook.setBuy(expected);
        Buy actual = buyBook.getBuy();
        assertEquals(expected, actual);
    }

    @Test
    void getSetBook() {
        final Book expected = new Book();
        BuyBook buyBook = new BuyBook();
        buyBook.setBook(expected);
        Book actual = buyBook.getBook();
        assertEquals(expected, actual);
    }

    @Test
    void getSetAmount() {
        final int expected = 1;
        BuyBook buyBook = new BuyBook();
        buyBook.setAmount(expected);
        int actual = buyBook.getAmount();

        assertEquals(expected, actual);
    }
}
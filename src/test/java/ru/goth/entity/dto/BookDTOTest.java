package ru.goth.entity.dto;

import org.junit.jupiter.api.Test;
import ru.goth.entity.Author;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookDTOTest {

    @Test
    void getSetTitle() {
        final String expected = "Oleg";
        BookDTO book = new BookDTO();
        book.setTitle(expected);
        String actual = book.getTitle();

        assertEquals(expected, actual);
    }

    @Test
    void getSetAuthor() {
        final Author expected = new Author();
        BookDTO book = new BookDTO();
        book.setAuthor(expected);
        Author actual = book.getAuthor();
        assertEquals(expected, actual);
    }

    @Test
    void getSetGenre() {
        final String expected = "Oleg";
        BookDTO book = new BookDTO();
        book.setGenre(expected);
        String actual = book.getGenre();

        assertEquals(expected, actual);
    }

    @Test
    void getSetPrice() {
        final float expected = 1.0F;
        BookDTO book = new BookDTO();
        book.setPrice(expected);
        float actual = book.getPrice();

        assertEquals(expected, actual);
    }

    @Test
    void getSetAmount() {
        final int expected = 1;
        BookDTO book = new BookDTO();
        book.setAmount(expected);
        int actual = book.getAmount();

        assertEquals(expected, actual);
    }
}

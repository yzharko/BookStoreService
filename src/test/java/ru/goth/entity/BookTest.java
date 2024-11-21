package ru.goth.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookTest {

    @Test
    void getSetId() {
        final long expected = 1L;
        Book book = new Book();
        book.setId(expected);
        long actual = book.getId();

        assertEquals(expected, actual);
    }

    @Test
    void getSetTitle() {
        final String expected = "Oleg";
        Book book = new Book();
        book.setTitle(expected);
        String actual = book.getTitle();

        assertEquals(expected, actual);
    }

    @Test
    void getSetAuthor() {
        final Author expected = new Author();
        Book book = new Book();
        book.setAuthor(expected);
        Author actual = book.getAuthor();
        assertEquals(expected, actual);
    }

    @Test
    void getSetPrice() {
        final float expected = 1.0F;
        Book book = new Book();
        book.setPrice(expected);
        float actual = book.getPrice();

        assertEquals(expected, actual);
    }

    @Test
    void getSetGenre() {
        final String expected = "Oleg";
        Book book = new Book();
        book.setGenre(expected);
        String actual = book.getGenre();

        assertEquals(expected, actual);
    }

    @Test
    void getSetAmount() {
        final int expected = 1;
        Book book = new Book();
        book.setAmount(expected);
        int actual = book.getAmount();

        assertEquals(expected, actual);
    }

    @Test
    void testToString() {
        Book book = new Book();
        Author author = new Author();
        book.setAuthor(author);

        final String expected = "Book{id=0, title='null', author=Author{id=0, name='null'}, genre='null', price=0.0, amount=0, buyBooks=null}";
        String actual = book.toString();
        assertEquals(expected, actual);
    }
}
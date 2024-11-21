package ru.goth.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorTest {

    @Test
    void getSetId() {
        final long expected = 1L;
        Author author = new Author();
        author.setId(expected);
        long actual = author.getId();

        assertEquals(expected, actual);
    }

    @Test
    void getSetName() {
        final String expected = "Oleg";
        Author author = new Author();
        author.setName(expected);
        String actual = author.getName();

        assertEquals(expected, actual);
    }

    @Test
    void testToString() {
        final long id = 1L;
        final String name = "Oleg";
        Author author = new Author(id, name);

        final String expected = "Author{id=1, name='Oleg'}";
        String actual = author.toString();
        assertEquals(expected, actual);
    }
}
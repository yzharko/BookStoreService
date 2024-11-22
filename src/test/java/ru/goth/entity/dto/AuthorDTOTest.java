package ru.goth.entity.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AuthorDTOTest {

    @Test
    public void getSetName() {
        final String expected = "Oleg";
        AuthorDTO author = new AuthorDTO();
        author.setName(expected);
        String actual = author.getName();

        assertEquals(expected, actual);
    }

}

package ru.goth.entity.dto;

import org.junit.jupiter.api.Test;
import ru.goth.entity.Author;

import static org.junit.jupiter.api.Assertions.*;

class AuthorDTOTest {

    @Test
    void getSetName() {
        final String expected = "Oleg";
        AuthorDTO author = new AuthorDTO();
        author.setName(expected);
        String actual = author.getName();

        assertEquals(expected, actual);
    }

}

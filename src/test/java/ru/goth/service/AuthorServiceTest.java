package ru.goth.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.goth.entity.Author;
import ru.goth.entity.dto.AuthorDTO;
import ru.goth.repository.AuthorDAO;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class AuthorServiceTest {
    private AuthorDAO mockAuthorDAO;
    private AuthorService mockAuthorService;
    private final AuthorDTO mockAuthorDTO = new AuthorDTO();

    @Before
    public void setup() {
        mockAuthorDAO = mock(AuthorDAO.class);
        mockAuthorService = new AuthorService(mockAuthorDAO);
    }

    @Test
    public void getById() {
        final long id = 1L;
        final String name = "G.O.M.";
        mockAuthorDTO.setName(name);

        Mockito.when(mockAuthorDAO.getAuthor(id)).thenReturn(new Author(name));
        assertEquals(mockAuthorDTO.getName(), mockAuthorService.getById(id).getName());
    }

    @Test
    public void setByDTO() {
        final String name = "G.O.M.";
        mockAuthorDTO.setName(name);

        Mockito.doNothing().when(mockAuthorDAO).setAuthor(mockAuthorDTO.getName());
        mockAuthorService.setByDTO(mockAuthorDTO);
    }

    @Test
    public void update() {
        final long id = 1L;
        final String name = "G.O.M.";

        mockAuthorDTO.setName(name);

        Mockito.when(mockAuthorDAO.getAuthor(id)).thenReturn(new Author(name));
        Mockito.doNothing().when(mockAuthorDAO).updateAuthor(id, name);

        mockAuthorService.update(id, mockAuthorDTO);
    }

    @Test
    public void deleteByDTO() {
        final String name = "G.O.M.";
        mockAuthorDTO.setName(name);

        Mockito.doNothing().when(mockAuthorDAO).deleteAuthor(mockAuthorDTO.getName());

        mockAuthorService.deleteByDTO(mockAuthorDTO);
    }
}
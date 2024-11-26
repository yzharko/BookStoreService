package ru.goth.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.goth.entity.Author;
import ru.goth.entity.dto.AuthorDTO;
import ru.goth.repository.AuthorDAO;

import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AuthorServiceTest {
    private AuthorDAO mockAuthorDAO;
    private AuthorService mockAuthorService;
    private final AuthorDTO mockAuthorDTO = new AuthorDTO();

    @Before
    public void setup() {
        mockAuthorDAO = mock(AuthorDAO.class);
        try {
            mockAuthorService = new AuthorService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        mockAuthorService.setByDTO(mockAuthorDTO);

        verify(mockAuthorDAO, times(1)).setAuthor(mockAuthorDTO.getName());
    }

    @Test
    public void update() {
        final long id = 1L;
        final String name = "G.O.M.";

        mockAuthorDTO.setName(name);

        Mockito.when(mockAuthorDAO.getAuthor(id)).thenReturn(new Author(name));

        mockAuthorService.update(id, mockAuthorDTO);
        verify(mockAuthorDAO, times(1)).updateAuthor(id, name);
    }

    @Test
    public void deleteByDTO() {
        final String name = "G.O.M.";
        mockAuthorDTO.setName(name);

        mockAuthorService.deleteByDTO(mockAuthorDTO);
        verify(mockAuthorDAO, times(1)).deleteAuthor(mockAuthorDTO.getName());
    }
}
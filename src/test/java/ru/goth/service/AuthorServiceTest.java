package ru.goth.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.goth.entity.dto.AuthorDTO;

import static org.junit.jupiter.api.Assertions.*;

class AuthorServiceTest {

    @Test
    void getById() {
        AuthorService mockAuthorService = Mockito.mock(AuthorService.class);

        final long id = 1L;
        final String name = "G.O.M.";

        AuthorDTO mockAuthorDTO = new AuthorDTO();
        mockAuthorDTO.setName(name);

        Mockito.when(mockAuthorService.getById(id)).thenReturn(mockAuthorDTO);
        assertEquals(mockAuthorDTO, mockAuthorService.getById(id));
    }

    @Test
    void setByDTO() {
        AuthorService mockAuthorService = Mockito.mock(AuthorService.class);

        final String name = "G.O.M.";
        AuthorDTO mockAuthorDTO = new AuthorDTO();
        mockAuthorDTO.setName(name);

        Mockito.doNothing().when(mockAuthorService).setByDTO(mockAuthorDTO);

        mockAuthorService.setByDTO(mockAuthorDTO);
        Mockito.verify(mockAuthorService).setByDTO(mockAuthorDTO);
    }

    @Test
    void update() {
        AuthorService mockAuthorService = Mockito.mock(AuthorService.class);

        final long id = 1L;
        final String name = "G.O.M.";

        AuthorDTO mockAuthorDTO = new AuthorDTO();
        mockAuthorDTO.setName(name);

        Mockito.doNothing().when(mockAuthorService).update(id, mockAuthorDTO);

        mockAuthorService.update(id, mockAuthorDTO);
        Mockito.verify(mockAuthorService).update(id, mockAuthorDTO);
    }

    @Test
    void deleteByDTO() {
        AuthorService mockAuthorService = Mockito.mock(AuthorService.class);

        final String name = "G.O.M.";
        AuthorDTO mockAuthorDTO = new AuthorDTO();
        mockAuthorDTO.setName(name);

        Mockito.doNothing().when(mockAuthorService).deleteByDTO(mockAuthorDTO);

        mockAuthorService.deleteByDTO(mockAuthorDTO);
        Mockito.verify(mockAuthorService).deleteByDTO(mockAuthorDTO);
    }
}
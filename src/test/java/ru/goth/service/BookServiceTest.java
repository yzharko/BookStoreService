package ru.goth.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.goth.entity.dto.BookDTO;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    @Test
    void getById() {
        BookService mockBookService = Mockito.mock(BookService.class);

        final long id = 1L;
        final String title = "Occult encyclopedia";

        BookDTO mockBookDTO = new BookDTO();
        mockBookDTO.setTitle(title);

        Mockito.when(mockBookService.getById(id)).thenReturn(mockBookDTO);
        assertEquals(mockBookDTO, mockBookService.getById(id));
    }

    @Test
    void setByDTO() {
        BookService mockBookService = Mockito.mock(BookService.class);

        final int id = 1;
        final String title = "Occult encyclopedia";

        BookDTO mockBookDTO = new BookDTO();
        mockBookDTO.setTitle(title);

        Mockito.when(mockBookService.setByDTO(mockBookDTO)).thenReturn(id);

        assertEquals(id, mockBookService.setByDTO(mockBookDTO));
    }

    @Test
    void update() {
        BookService mockBookService = Mockito.mock(BookService.class);

        final long id = 1L;
        final String title = "Occult encyclopedia";

        BookDTO mockBookDTO = new BookDTO();
        mockBookDTO.setTitle(title);

        Mockito.doNothing().when(mockBookService).update(id, mockBookDTO);

        mockBookService.update(id, mockBookDTO);
        Mockito.verify(mockBookService).update(id, mockBookDTO);
    }

    @Test
    void deleteByDTO() {
        BookService mockBookService = Mockito.mock(BookService.class);

        final String title = "Occult encyclopedia";

        BookDTO mockBookDTO = new BookDTO();
        mockBookDTO.setTitle(title);

        Mockito.doNothing().when(mockBookService).deleteByDTO(mockBookDTO);

        mockBookService.deleteByDTO(mockBookDTO);
        Mockito.verify(mockBookService).deleteByDTO(mockBookDTO);
    }
}